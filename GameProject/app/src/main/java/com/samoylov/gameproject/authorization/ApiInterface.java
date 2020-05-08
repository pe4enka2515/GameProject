package com.samoylov.gameproject.authorization;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("reg/")
    Call<User> performRegistration(@Field("name") String name, @Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @POST("log/")
    Call<User> performUserLogin(@Field("username") String username, @Field("password") String password);
}