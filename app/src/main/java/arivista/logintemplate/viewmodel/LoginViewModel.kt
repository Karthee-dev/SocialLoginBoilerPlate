package arivista.logintemplate.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import arivista.logintemplate.model.User


/**
 * Created by Karthee on 26/09/18.
 */


class LoginViewModel : BaseObservable() {
    private val user: User


    private val successMessage = "Login was successful"
    private val errorMessage = "Email or Password not valid"

    var toastMessage = ObservableField<String>()

//    var toastMessage = ObservableField("test")


    init {
        user = User("", "")
    }

    fun afterEmailTextChanged(s: CharSequence) {
        user.email = s.toString()
    }

    fun afterPasswordTextChanged(s: CharSequence) {
        user.password = s.toString()
    }

    fun onLoginClicked() {
        if (user.isInputDataValid)
            toastMessage = ObservableField(successMessage)
        else
            toastMessage = ObservableField(errorMessage)
    }
}
