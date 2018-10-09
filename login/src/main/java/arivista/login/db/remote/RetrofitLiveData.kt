package arivista.login.db.remote

import android.arch.lifecycle.LiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitLiveData<T>(private val call: Call<T>) : LiveData<T>(), Callback<T> {

    override fun onActive() {
        if (!call.isCanceled && !call.isExecuted) call.enqueue(this)
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        //not implemented
        Log.e("api failure", t?.localizedMessage)
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        value = response?.body()
        Log.e("api response", response?.raw().toString())
        Log.e("api response", value.toString())

    }

    fun cancel() = if (!call.isCanceled) call.cancel() else Unit
}