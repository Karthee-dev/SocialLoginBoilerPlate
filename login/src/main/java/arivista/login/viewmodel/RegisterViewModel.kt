package arivista.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import arivista.login.db.remote.RetrofitLiveData
import arivista.login.db.remote.UserRepository
import arivista.login.model.AddressModel


class RegisterViewModel : ViewModel() {


    private var userRepository: UserRepository = UserRepository()
    private lateinit var address: LiveData<List<AddressModel>>


    init {

//        address = userRepository.address


    }


    var liveData: RetrofitLiveData<AddressModel>? = null


    val pincode = ObservableField<String>()
    val state = ObservableField<String>()
    val district = ObservableField<String>()
    val village = ObservableField<String>()
    val taluk = ObservableField<String>()
    val street = ObservableField<String>()


    init {

    }

    fun getAddress(pincode: String) {
        Log.e("pincode", pincode)

        userRepository.getAddress(pincode).observeForever {

            state.set(it?.stateName)
            taluk.set(it?.taluk)
            village.set(it?.villageName.toString())
            district.set(it?.districtName)
            Log.e("api", it?.stateName.toString())
        }
    }

    override fun onCleared() {
        liveData?.cancel()
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
