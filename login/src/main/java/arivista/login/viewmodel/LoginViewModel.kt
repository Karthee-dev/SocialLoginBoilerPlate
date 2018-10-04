package arivista.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import arivista.login.db.remote.UserRepository
import arivista.login.model.User
import arivista.login.utils.EmailUtils

class LoginViewModel : ViewModel() {

    // Create a LiveData
    val user: LiveData<User>

    val email = ObservableField<String>()
    val password = ObservableField<String>()

    val errorEmail = ObservableField<String>()
    val errorPassword = ObservableField<String>()

    private var userRepository: UserRepository = UserRepository()
    private lateinit var users: LiveData<List<User>>

    init {
        email.set("admin@gmail.com")
        password.set("admin")
        user = userRepository.user

        var size = arrayOf(getUsers()).size
        Log.e("usersize",size.toString())

    }

    fun getUsers(): LiveData<List<User>> {
        if (!::users.isInitialized) {
            loadUsers()
        }
        return users
    }

    private fun loadUsers() {
        users = userRepository.userlist
        // Do an asynchronous operation to fetch users.
    }

    fun onBtnLoginClick() {
        if (validateInputs()) {
            userRepository.loginUser(email.get()!!, password.get()!!)

        }
    }
    private fun validateInputs(): Boolean {
        var isValid = true

        if (email.get() == null || !EmailUtils.isEmailValid(email.get()!!)) {

            errorEmail.set("Invalid Email")

            isValid = false

        } else {
            errorEmail.set("")
        }

        if (password.get() == null || password.get()!!.length < 4) {
            errorPassword.set("Password too short")

            isValid = false

        } else {
            errorPassword.set("")
        }

        return isValid
    }
}