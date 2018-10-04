package arivista.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
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

    init {
        email.set("admin@gmail.com")
        password.set("admin")

        user = userRepository.user

    }

    private lateinit var users: MutableLiveData<List<User>>

    fun getUsers(): LiveData<List<User>> {
        if (!::users.isInitialized) {
            users = MutableLiveData()
            loadUsers()
        }
        return users
    }

    private fun loadUsers() {
        user.value.apply { this!!.name

        }
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
