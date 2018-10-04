package arivista.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import arivista.login.db.remote.UserRepository

import arivista.login.model.User

class MainViewModel : ViewModel() {

    private val userRepository: UserRepository

    val user: LiveData<User>
        get() = userRepository.user

    init {
        userRepository = UserRepository()
    }


    fun clearUserData() {
        userRepository.clearUserCached()
    }
}
