package karthee.login.utils;

import karthee.login.db.remote.APIService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static karthee.login.db.remote.APIService.BASE_URL;

public class NetworkUtils {

    public static APIService getAPIService() {
        Retrofit.Builder retrofit = new Retrofit.Builder();

        retrofit
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())

                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.build().create(APIService.class);
    }
}
