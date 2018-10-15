package arivista.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import arivista.login.db.remote.UserRepository
import arivista.login.model.AddressModel

class MainViewModel : ViewModel() {

    private val userRepository: UserRepository = UserRepository()

    var name = ObservableField<String>()

    val user: LiveData<AddressModel>
        get() = userRepository.addressModel

    init {
        user.observeForever { t: AddressModel? ->
            Log.e("MainActivity", t?.email)
            name.set(t?.email)
        }
    }

//    fun clearUserData() {
//        userRepository.clearUserCached()
//    }


}
