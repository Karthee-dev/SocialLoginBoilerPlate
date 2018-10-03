package karthee.login.db.remote


import io.reactivex.Observable
import karthee.login.model.LoginRequest
import karthee.login.model.User
import retrofit2.http.Body
import retrofit2.http.POST


interface APIService {
    @POST("Signin")   // mocked login service
    fun login(@Body request: LoginRequest): Observable<User>

    companion object {

        val BASE_URL = "http://52.90.34.153:92/"    // mocked login service
    }
}
