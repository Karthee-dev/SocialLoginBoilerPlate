package karthee.login.utils

import karthee.login.db.remote.APIService
import karthee.login.db.remote.APIService.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {
    @JvmStatic
    fun getAPIService(): APIService {
        val retrofit = Retrofit.Builder()

        retrofit
                .baseUrl(BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())

                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.build().create(APIService::class.java)
    }


}
