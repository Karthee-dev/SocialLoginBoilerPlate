package arivista.login.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window

import arivista.login.utils.AuthData
import arivista.login.utils.SocialUser

abstract class SimpleAuthActivity : AppCompatActivity() {
    protected abstract val authData: AuthData

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
    }

    protected fun handCancel() {
        authData.callback?.onCancel()
        finish()
    }

    protected fun handleError(error: Throwable) {
        authData.callback?.onError(error)
        finish()
    }

    protected fun handleSuccess(user: SocialUser) {
        authData.callback?.onSuccess(user)
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        authData.clearCallback()
    }
}
