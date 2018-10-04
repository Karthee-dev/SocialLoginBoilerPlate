package arivista.login.db.remote


import arivista.login.model.LoginRequest
import arivista.login.model.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


interface APIService {
    @POST("Signin")
    fun login(@Body request: LoginRequest): Observable<User>

    companion object {

        val BASE_URL = "http://52.90.34.153:92/"
    }
}
