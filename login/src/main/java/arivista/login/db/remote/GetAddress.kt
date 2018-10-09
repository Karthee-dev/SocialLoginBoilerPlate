package arivista.login.db.remote

import arivista.login.model.AddressModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Karthee on 09/10/18.
 */
interface GetAddress {
    @GET("Address")
    fun address(@Query("pincode") pincode: String? = null, @Query("key") key: String): Observable<AddressModel>

}