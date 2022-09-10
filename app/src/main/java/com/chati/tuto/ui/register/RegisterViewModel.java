package com.chati.tuto.ui.register;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.chati.tuto.models.User;
import com.chati.tuto.network.ApiRegisterResponse;
import com.chati.tuto.ui.main.MainActivity;
import com.chati.tuto.ui.register.RegisterDataSource;
import com.telifoun.mqttchat.core.clbs.CallbackListener;
import com.telifoun.mqttchat.gui.MqttChat;

public class RegisterViewModel extends ViewModel {

    private RegisterDataSource mDataSource;
    private LiveData<Boolean> progressDialog;
    private LiveData<ApiRegisterResponse> registerResponse;

    public RegisterViewModel(){
        mDataSource =new RegisterDataSource();
    }

    public void register(User user){
      mDataSource.register(user);
    }


    public LiveData<Boolean> getProgressDialog() {
        if(progressDialog==null){
          progressDialog= mDataSource.getProgressDialog();
        }
        return progressDialog;
    }



    public LiveData<ApiRegisterResponse> getRegisterResponse() {
        if(registerResponse==null){
          registerResponse=mDataSource.getRegisterResponse();
        }
        return registerResponse;
    }


}
