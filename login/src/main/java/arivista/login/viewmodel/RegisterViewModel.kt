package arivista.login.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import arivista.login.db.remote.UserRepository


class RegisterViewModel : ViewModel() {

    private var userRepository: UserRepository = UserRepository()

    val pincode = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val state = ObservableField<String>()
    val district = ObservableField<String>()
    val village = ObservableField<String>()
    val taluk = ObservableField<String>()
    val street = ObservableField<String>()
    val houseno = ObservableField<String>()

    val streetlist: MutableLiveData<List<String>> = MutableLiveData()
    val villagelist: MutableLiveData<List<String>> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun getAddress(pincode: String) {

        userRepository.getAddress(pincode).observeForever {

            state.set(it?.stateName)
            taluk.set(it?.taluk)
            villagelist.value = it?.villageName
            district.set(it?.districtName)
        }
    }

    fun getStreet(street: String) {

        if (pincode.get() != null) {
            userRepository.getStreet(pincode.get().toString(), street).observeForever {
                streetlist.value = (it)
            }
        }
    }

    fun postAddress() {
        userRepository.postAddress(pincode.get()!!, houseno.get(), taluk.get(),
                village.get(), district.get(), state.get(), street.get()
                , email.get(), password.get()).observeForever {
            isSuccess.value = it
        }
    }


}
