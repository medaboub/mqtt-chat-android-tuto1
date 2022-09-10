package com.chati.tuto.ui.login;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.chati.tuto.api.APIService;
import com.chati.tuto.api.ServiceGenerator;
import com.chati.tuto.models.User;
import com.chati.tuto.network.ApiLoginResponse;
import com.chati.tuto.network.ApiRegisterResponse;
import com.chati.tuto.network.MqttChatResponse;
import com.telifoun.mqttchat.core.clbs.CallbackListener;
import com.telifoun.mqttchat.gui.MqttChat;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDataSource {

    private MutableLiveData<Boolean> progressDialog;
    private MutableLiveData<ApiLoginResponse> loginResponse;
    private MutableLiveData<MqttChatResponse> mqttChatResponse;

    public LoginDataSource() {
        this.progressDialog=new MutableLiveData<Boolean>();
        this.loginResponse=new MutableLiveData<ApiLoginResponse>();
        this.mqttChatResponse=new MutableLiveData<MqttChatResponse>();
    }


    public void login(String email,String password){
        APIService apiService= ServiceGenerator.createService(APIService.class);
        getProgressDialog().postValue(true);
        apiService.login(email,password).enqueue(new Callback<ApiLoginResponse>() {
            @Override
            public void onResponse(Call<ApiLoginResponse> call, Response<ApiLoginResponse> response) {
                getProgressDialog().postValue(false);
                if(response.isSuccessful()) {
                    getLoginResponse().postValue(response.body());
                }else{
                    try {
                        getLoginResponse().postValue(new ApiLoginResponse(false,response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiLoginResponse> call, Throwable t) {
                getProgressDialog().postValue(false);
                getLoginResponse().postValue(new ApiLoginResponse(false,t.getMessage()));
            }
        });
    }

    public void connect(Context ctx, int userid){
        getProgressDialog().postValue(true);
        MqttChat.getInstance().logIn(ctx, userid, new CallbackListener() {
            @Override
            public void onSuccess(Object o) {
                MqttChat.getInstance().Connect(new CallbackListener() {
                    @Override
                    public void onSuccess(Object o) {
                        getProgressDialog().postValue(false);
                        mqttChatResponse.postValue(new MqttChatResponse());
                    }
                    @Override
                    public void onError(String error) {
                        getProgressDialog().postValue(false);
                        mqttChatResponse.postValue(new MqttChatResponse(error));
                    }
                });
            }
            @Override
            public void onError(String s) {
            }
        });
    }

    public MutableLiveData<Boolean> getProgressDialog() {
        return progressDialog;
    }

    public MutableLiveData<ApiLoginResponse> getLoginResponse() {
        return loginResponse;
    }

    public MutableLiveData<MqttChatResponse> getMqttChatResponse() {
        return mqttChatResponse;
    }
}
