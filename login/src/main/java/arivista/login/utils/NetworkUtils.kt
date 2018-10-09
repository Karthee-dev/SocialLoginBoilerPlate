package arivista.login.utils

import arivista.login.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {
    @JvmStatic
    fun getAPIService(): Retrofit? {
        val retrofit = Retrofit.Builder()

        retrofit
                .baseUrl(BuildConfig.BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.build()
    }


}
