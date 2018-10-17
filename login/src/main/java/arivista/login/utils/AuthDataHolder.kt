package arivista.login.utils

class AuthDataHolder private constructor() {

    var facebookAuthData: AuthData? = null
    var googleAuthData: AuthData? = null
    var twitterAuthData: AuthData? = null
    var instagramAuthData: AuthData? = null

    companion object {
        val instance = AuthDataHolder()
    }
}
