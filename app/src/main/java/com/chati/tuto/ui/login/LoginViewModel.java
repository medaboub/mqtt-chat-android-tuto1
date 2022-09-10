package com.chati.tuto.ui.login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chati.tuto.models.User;
import com.chati.tuto.network.ApiLoginResponse;
import com.chati.tuto.network.ApiRegisterResponse;
import com.chati.tuto.network.MqttChatResponse;
import com.chati.tuto.ui.register.RegisterDataSource;

public class LoginViewModel extends ViewModel{

    private LoginDataSource mDataSource;
    private LiveData<Boolean> progressDialog;
    private LiveData<ApiLoginResponse> loginResponse;
    private LiveData<MqttChatResponse> mqttChatResponse;

    public LoginViewModel(){
        mDataSource =new LoginDataSource();
    }


    public void login(String email,String password){
        mDataSource.login(email,password);
    }

    public void connectToChat(Context ctx, int userid){
        mDataSource.connect(ctx,userid);
    }

    public LiveData<Boolean> getProgressDialog() {
        if(progressDialog==null){
            progressDialog= mDataSource.getProgressDialog();
        }
        return progressDialog;
    }



    public LiveData<ApiLoginResponse> getLoginResponse() {
        if(loginResponse==null){
            loginResponse=mDataSource.getLoginResponse();
        }
        return loginResponse;
    }

    public LiveData<MqttChatResponse> getMqttChatResponse() {
        if(mqttChatResponse==null){
            mqttChatResponse=mDataSource.getMqttChatResponse();
        }
        return mqttChatResponse;
    }
}
