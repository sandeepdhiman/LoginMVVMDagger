package com.gis.daggerdemo.request;

import com.gis.daggerdemo.model.LoginUser;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("")
    Call<LoginUser> userLogin(@Body JsonObject jsonObject);
}
