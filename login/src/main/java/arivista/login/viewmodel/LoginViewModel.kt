package arivista.login.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import arivista.login.db.remote.UserRepository
import arivista.login.utils.EmailUtils


class LoginViewModel : ViewModel() {

    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()

    var email = ObservableField<String>()
    val password = ObservableField<String>()
    val errorEmail = ObservableField<String>()
    val errorPassword = ObservableField<String>()

    private var userRepository: UserRepository = UserRepository()

    fun onBtnLoginClick() {
        if (validateInputs()) {
            userRepository.addressDao.isUserExists(email.get()!!, password.get()!!).observeForever { it ->
                isSuccess.value = it != 0
            }
        }
    }



    private fun validateInputs(): Boolean {
        var isValid = true

        if (email.get() == null || !EmailUtils.isEmailValid(email.get()!!)) {

            errorEmail.set("Invalid Email")

            isValid = false
            Log.e("email",isValid.toString())

        } else {
            errorEmail.set("")
            Log.e("email invalid",isValid.toString())


        }

        if (password.get() == null || password.get()!!.length < 4) {
            errorPassword.set("Password too short")

            isValid = false

        } else {
            errorPassword.set("")

        }

        return isValid
    }

    fun onEmailChange() {
        validateInputs()
    }
}