package arivista.login.google

import arivista.login.MyApplication
import arivista.login.utils.AuthCallback
import arivista.login.utils.AuthData
import arivista.login.utils.AuthDataHolder

object SimpleAuth {
  @JvmStatic
  fun connectGoogle(scopes: List<String> = listOf(), listener: AuthCallback) {
    AuthDataHolder.instance.googleAuthData = AuthData(scopes, listener)
    GoogleAuthActivity.start(MyApplication.context)
  }

  @JvmStatic
  fun disconnectGoogle() {
    AuthDataHolder.instance.googleAuthData = null
    GoogleAuthActivity.setGoogleDisconnectRequested(MyApplication.context,true)
  }

  @JvmStatic
  fun revokeGoogle() {
    GoogleAuthActivity.setGoogleRevokeRequested(MyApplication.context,true)
  }
}