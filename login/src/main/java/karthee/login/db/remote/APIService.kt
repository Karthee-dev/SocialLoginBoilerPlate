package karthee.login.db.remote


import io.reactivex.Observable
import karthee.login.model.LoginRequest
import karthee.login.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface APIService {

    @POST("login")   // mocked login service
    fun login(@Body request: LoginRequest): Observable<LoginResponse>

    companion object {

        val BASE_URL = "http://demo6183250.mockable.io/"    // mocked login service
    }
}
