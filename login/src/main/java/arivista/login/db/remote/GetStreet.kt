package arivista.login.db.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GetStreet {
    @GET("Street")
    fun address(@Query("pincode") pincode: String? = null,
                @Query("str") searchString: String,
                @Query("key") key: String):
            Observable<List<String>>

}
