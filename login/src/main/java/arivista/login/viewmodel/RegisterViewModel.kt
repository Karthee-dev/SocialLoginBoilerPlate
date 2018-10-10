package arivista.login.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import arivista.login.db.remote.UserRepository


class RegisterViewModel : ViewModel() {


    private var userRepository: UserRepository = UserRepository()


    val pincode = ObservableField<String>()
    val state = ObservableField<String>()
    val district = ObservableField<String>()
    val village = ObservableField<String>()
    val taluk = ObservableField<String>()
    val street = ObservableField<String>()
    val houseno = ObservableField<String>()

    val streetlist: MutableLiveData<List<String>> = MutableLiveData()


    init {

//        streetlist.set(EMPTY_LIST)
    }

    fun getAddress(pincode: String) {

        userRepository.getAddress(pincode).observeForever {

            state.set(it?.stateName)
            taluk.set(it?.taluk)
            village.set(it?.villageName.toString())
            district.set(it?.districtName)
        }
    }


    fun getStreet(street: String) {
        Log.e("api", "pincode" + pincode.get().toString())

        if (pincode.get() != null) {
            Log.e("api", "pincode in" + pincode.get().toString())

            userRepository.getStreet(pincode.get().toString(), street).observeForever {

                streetlist.value = (it)
                Log.e("api", it?.toString())
            }
        }
    }

    fun postAddress(): Boolean {

        var address = pincode.get() + "," + houseno.get() + "," + taluk.get() + "," + village.get() + "," +
                district.get() + "," + state.get() + "," + street.get()
        Log.e("request", address)
       return userRepository.postAddress(address)
    }


}
