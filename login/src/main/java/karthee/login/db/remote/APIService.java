package karthee.login.db.remote;


import io.reactivex.Observable;
import karthee.login.model.LoginRequest;
import karthee.login.model.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface APIService {

    String BASE_URL = "http://demo6183250.mockable.io/";    // mocked login service

    @POST("login")   // mocked login service
    Observable<LoginResponse> login(@Body LoginRequest request);
}
