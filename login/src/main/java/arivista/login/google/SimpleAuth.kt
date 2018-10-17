package arivista.login.google

import arivista.login.utils.AuthCallback
import arivista.login.utils.AuthData
import arivista.login.utils.AuthDataHolder
import arivista.login.utils.Initializer

object SimpleAuth {
  @JvmStatic
  fun connectGoogle(scopes: List<String> = listOf(), listener: AuthCallback) {
    AuthDataHolder.instance.googleAuthData = AuthData(scopes, listener)
    GoogleAuthActivity.start(Initializer.context)
  }

  @JvmStatic
  fun disconnectGoogle() {
    AuthDataHolder.instance.googleAuthData = null
    GoogleAuthActivity.setGoogleDisconnectRequested(Initializer.context,true)
  }

  @JvmStatic
  fun revokeGoogle() {
    GoogleAuthActivity.setGoogleRevokeRequested(Initializer.context,true)
  }
}