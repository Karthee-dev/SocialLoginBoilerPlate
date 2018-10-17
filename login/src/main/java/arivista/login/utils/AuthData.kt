package arivista.login.utils

import java.util.*

class AuthData(scopes: List<String>, callback: AuthCallback) {
    val scopes: List<String>
    internal var callback: AuthCallback? = null
        private set

    init {
        this.scopes = ArrayList(scopes)
        this.callback = callback
    }

    internal fun clearCallback() {
        callback = null
    }
}