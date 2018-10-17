package arivista.login.google

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.annotation.NonNull
import android.text.TextUtils
import arivista.login.R
import arivista.login.ui.SimpleAuthActivity
import arivista.login.utils.*
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Scope
import java.util.*

class GoogleAuthActivity : SimpleAuthActivity(), GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private var googleApiClient: GoogleApiClient? = null
    private var retrySignIn: Boolean = false

    override val authData: AuthData
        get() = AuthDataHolder.instance.googleAuthData!!

    private val scopes: ArrayList<Any>
        get() {
            val scopes = ArrayList<Any>()
            for (str in authData.scopes) {
                scopes.add(Scope(str))
            }

            return scopes
        }

    private val accessTokenScope: String
        get() {
            var scopes = "oauth2:id profile email"
            if (authData.scopes.isNotEmpty()) {
                scopes = "oauth2:" + TextUtils.join(" ", authData.scopes)
            }

            return scopes
        }

    private interface AccessTokenListener {
        fun onTokenReady(accessToken: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val clientId = AppUtils.getMetaDataValue(this, getString(R.string.googleWebClientId))

        val gsoBuilder = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestId()
                .requestProfile()
                .requestEmail()
                .requestIdToken(clientId)

        setupScopes(gsoBuilder)

        googleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gsoBuilder.build())
                .build()
    }

    private fun startSignInFlows() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun setupScopes(builder: GoogleSignInOptions.Builder) {
        val scopes = scopes
        if (scopes.size === 1) {
            builder.requestScopes(scopes[0] as Scope?)
        } else if (scopes.size > 1) {
            val restScopes = scopes.subList(1, scopes.size)
            var restScopesArray = arrayOfNulls<Scope>(restScopes.size)
            restScopesArray = scopes.toArray(restScopesArray)
            builder.requestScopes(scopes[0] as Scope?, *restScopesArray)
        }
    }

    override fun onConnectionFailed(@NonNull connectionResult: ConnectionResult) {
        val error = Throwable(connectionResult.errorMessage)
        handleError(error)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != RC_SIGN_IN || resultCode != Activity.RESULT_OK) {
            handCancel()
            return
        }

        val signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

        if (!isGoogleDisconnectRequested(this) && !isGoogleRevokeRequested(this) || retrySignIn) {
            retrySignIn = false
            handleSignInResult(signInResult)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult?) {
        if (result == null) {
            handCancel()
            return
        }

        if (result.isSuccess && result.signInAccount != null) {
            val acct = result.signInAccount
            val user = SocialUser()
            user.userId = acct!!.id
            user.accessToken = acct.idToken
            user.profilePictureUrl = if (acct.photoUrl != null) acct.photoUrl!!.toString() else ""
            user.email = acct.email
            user.fullName = acct.displayName

            getAccessToken(acct, object : AccessTokenListener {
                override fun onTokenReady(accessToken: String) {
                    user.accessToken = accessToken
                    handleSuccess(user)
                }
            })
        } else {
            val errorMsg = result.status.statusMessage
            if (errorMsg == null) {
                handCancel()
            } else {
                val error = Throwable(result.status.statusMessage)
                handleError(error)
            }
        }
    }

    private fun getAccessToken(account: GoogleSignInAccount, listener: AccessTokenListener) {
        val loadingDialog = DialogFactory.createLoadingDialog(this)
        loadingDialog.show()

        AsyncTask.execute {
            try {
                if (account.account == null) {
                    loadingDialog.dismiss()
                    handleError(RuntimeException("Account is null"))
                } else {
                    loadingDialog.dismiss()
                    setGoogleDisconnectRequested(this@GoogleAuthActivity, false)
                    setGoogleRevokeRequested(this@GoogleAuthActivity, false)
                    val token = GoogleAuthUtil.getToken(applicationContext, account.account!!.name, accessTokenScope)
                    listener.onTokenReady(token)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                loadingDialog.dismiss()
                handleError(e)
            }
        }
    }

    override fun onConnected(bundle: Bundle?) {
        val signIn = Runnable {
            retrySignIn = true
            startSignInFlows()
        }

        if (isGoogleDisconnectRequested(this)) {
            handleDisconnectRequest(signIn)
        } else if (isGoogleRevokeRequested(this)) {
            handleRevokeRequest(signIn)
        } else {
            startSignInFlows()
        }
    }

    override fun onConnectionSuspended(i: Int) {
        handleError(Throwable("connection suspended."))
    }

    private fun handleDisconnectRequest(onSignOut: Runnable) {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback {
            onSignOut.run()
            setGoogleDisconnectRequested(this@GoogleAuthActivity, false)
        }
    }

    private fun handleRevokeRequest(onRevoke: Runnable) {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback {
            onRevoke.run()
            setGoogleRevokeRequested(this@GoogleAuthActivity, false)
        }
    }

    companion object {

        private val KEY_IS_GOOGLE_DISCONNECT_REQUESTED = SimpleAuth::class.java.name + "KEY_IS_GOOGLE_DISCONNECT_REQUESTED"
        private val KEY_IS_GOOGLE_REVOKE_REQUESTED = SimpleAuth::class.java.name + "KEY_IS_GOOGLE_REVOKE_REQUESTED"
        private val RC_SIGN_IN = 1000

        fun start(context: Context) {
            val intent = Intent(context, GoogleAuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        internal fun isGoogleDisconnectRequested(context: Context): Boolean {
            return PreferenceUtils.getBoolean(context, KEY_IS_GOOGLE_DISCONNECT_REQUESTED)
        }

        fun setGoogleDisconnectRequested(context: Context, isRequested: Boolean) {
            PreferenceUtils.saveBoolean(context, KEY_IS_GOOGLE_DISCONNECT_REQUESTED, isRequested)
        }

        internal fun isGoogleRevokeRequested(context: Context): Boolean {
            return PreferenceUtils.getBoolean(context, KEY_IS_GOOGLE_REVOKE_REQUESTED)
        }

        fun setGoogleRevokeRequested(context: Context, isRequested: Boolean) {
            PreferenceUtils.saveBoolean(context, KEY_IS_GOOGLE_REVOKE_REQUESTED, isRequested)
        }
    }
}
