package arivista.login.viewmodel

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
    val streetlist = ObservableField<List<String>>()


    init {

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

                streetlist.set(it)
                Log.e("api", it?.toString())
            }
        }
    }
//    fun getPasswordTextWatcher(): TextWatcher {
//        return object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//                // Do nothing.
//            }
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                Log.e("pincode", s.toString())
//            }
//
//            override fun afterTextChanged(s: Editable) {
//                // Do nothing.
//            }
//        }
//    }

}
