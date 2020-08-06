package com.gis.daggerdemo.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gis.daggerdemo.Repository;
import com.gis.daggerdemo.model.LoginUser;
import com.gis.daggerdemo.utility.AppConstants;
import com.gis.daggerdemo.utility.Util;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import static com.gis.daggerdemo.BaseApplication.getContext;

public class LoginViewModel extends ViewModel {
    private Repository repository;
    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    @Inject
    LoginViewModel(Repository repository) {
        this.repository = repository;
    }

    private MutableLiveData<LoginUser> userMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<LoginUser> getUserMutableLiveData() {

        return userMutableLiveData;

    }

    public void onClick(View view) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("User_ID", EmailAddress.getValue());
        jsonObject.addProperty("Password", Password.getValue());
        jsonObject.addProperty(AppConstants.DEVICE_ID, Util.getDeviceId(getContext()));
        jsonObject.addProperty(AppConstants.OPERATOR_NAME,Util.getDeviceNetworkOperatorName(getContext()));
        jsonObject.addProperty(AppConstants.DEVICE_COM_NAME,Util.getDeviceName());
        jsonObject.addProperty(AppConstants.ANDROID_VERSION, Util.getDeviceOSVersion());
        userMutableLiveData.postValue(repository.getLogin(jsonObject).getValue());
    }


}
