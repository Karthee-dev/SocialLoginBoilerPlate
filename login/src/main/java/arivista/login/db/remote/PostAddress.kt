package arivista.login.db.remote

import arivista.login.model.AddressData
import arivista.login.model.ApiResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PostAddress {
    @POST("Add/Data")
    fun address(@Body address: AddressData, @Query("key") key: String): Observable<ApiResponse>

}
