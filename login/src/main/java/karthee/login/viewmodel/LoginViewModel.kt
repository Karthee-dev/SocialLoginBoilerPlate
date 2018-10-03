package karthee.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

import karthee.login.db.UserRepository
import karthee.login.model.User
import karthee.login.utils.EmailUtils

class LoginViewModel : ViewModel() {

    // Create a LiveData
    val user: LiveData<User>

    val email = ObservableField<String>()
    val password = ObservableField<String>()

    val errorEmail = ObservableField<String>()
    val errorPassword = ObservableField<String>()

    internal var userRepository: UserRepository

    init {
        userRepository = UserRepository()
        email.set("admin@gmail.com")
        password.set("admin")

        user = userRepository.user
    }

    fun onBtnLoginClick() {
        if (validateInputs()) {
            userRepository.loginUser(email.get()!!, password.get()!!)
        }
    }


    fun validateInputs(): Boolean {
        var isValid = true

        if (email.get() == null || !EmailUtils.isEmailValid(email.get()!!)) {

            errorEmail.set("Invalid Email")

            isValid = false

        } else {
            errorEmail.set(null)
        }

        if (password.get() == null || password.get()!!.length < 4) {
            errorPassword.set("Password too short")

            isValid = false

        } else {
            errorPassword.set(null)
        }

        return isValid
    }

}
