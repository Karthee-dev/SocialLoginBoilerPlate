package arivista.logintemplate.model

import android.text.TextUtils
import android.util.Patterns



/**
 * Created by Karthee on 26/09/18.
 */
class User(var email: String, var password: String) {

    val isInputDataValid: Boolean
        get() = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5
}
