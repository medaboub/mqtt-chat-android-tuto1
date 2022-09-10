package com.chati.tuto.api;

import com.chati.tuto.Config;
import com.chati.tuto.models.User;
import com.chati.tuto.network.ApiLoginResponse;
import com.chati.tuto.network.ApiRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @POST(Config.BASE_URL + "users")
    Call<ApiRegisterResponse> register(@Body User newUser);

    @GET(Config.BASE_URL +"login")
    Call<ApiLoginResponse> login(@Query("email") String email,
                                 @Query("password") String password);
}
