package arivista.login.utils

interface AuthCallback {
    fun onSuccess(socialUser: SocialUser)

    fun onError(error: Throwable)

    fun onCancel()
}
