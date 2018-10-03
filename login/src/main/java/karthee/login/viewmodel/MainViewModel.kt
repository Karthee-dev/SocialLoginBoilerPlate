package karthee.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import karthee.login.db.UserRepository
import karthee.login.model.User

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
