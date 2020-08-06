package com.gis.daggerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gis.daggerdemo.databinding.ActivityMainBinding;
import com.gis.daggerdemo.di.AppComponent;
import com.gis.daggerdemo.di.DaggerAppComponent;
import com.gis.daggerdemo.model.LoginUser;
import com.gis.daggerdemo.viewmodel.LoginViewModel;
import com.gis.daggerdemo.viewmodel.ViewModelFactory;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    private ActivityMainBinding binding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setLifecycleOwner(this);

        ((BaseApplication) getApplication()).getAppComponent().inject(this);


        loginViewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getUserMutableLiveData().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
              if (loginUser!=null){
                  Log.v("TAG",""+loginUser.getCode());
                  Toast.makeText(MainActivity.this,"if",Toast.LENGTH_LONG).show();
              }else {
                  Toast.makeText(MainActivity.this,"else",Toast.LENGTH_LONG).show();
              }
            }
        });
    }

}
