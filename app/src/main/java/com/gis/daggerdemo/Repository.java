package com.gis.daggerdemo;

import androidx.lifecycle.MutableLiveData;

import com.gis.daggerdemo.model.LoginUser;
import com.gis.daggerdemo.request.ApiInterface;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private ApiInterface apiInterface;
    private MutableLiveData<LoginUser> mutableLiveData = new MutableLiveData();
    @Inject
    public Repository(ApiInterface userService) {
        this.apiInterface = userService;
    }

    public MutableLiveData<LoginUser> getLogin(JsonObject jsonObject){
        Call<LoginUser>call =  apiInterface.userLogin(jsonObject);
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                if (response!=null && response.body()!=null){
                    //mutableLiveData.setCode(response.body().getCode());
                    //mutableLiveData.setMessage(response.body().getMessage());
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
              t.printStackTrace();
            }
        });
        return mutableLiveData;
    }
}
