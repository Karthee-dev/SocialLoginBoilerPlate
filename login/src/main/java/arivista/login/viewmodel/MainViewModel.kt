package arivista.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import arivista.login.db.remote.UserRepository
import arivista.login.model.User

class MainViewModel : ViewModel() {

    private val userRepository: UserRepository = UserRepository()

    var name = ObservableField<String>()

    val user: LiveData<User>
        get() = userRepository.user

    init {
        user.observeForever { t: User? ->
            Log.e("MainActivity", t?.name)
            name.set(t?.name)

        }

    }

    fun clearUserData() {
        userRepository.clearUserCached()
    }


}
