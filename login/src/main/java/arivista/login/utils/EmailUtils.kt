package arivista.login.utils

import java.util.regex.Pattern

object EmailUtils {
    fun isEmailValid(email: String): Boolean {
        val pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
}
