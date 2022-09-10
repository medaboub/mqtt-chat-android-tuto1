package com.chati.tuto.ui.register;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.chati.tuto.api.APIService;
import com.chati.tuto.api.ServiceGenerator;
import com.chati.tuto.models.User;
import com.chati.tuto.network.ApiRegisterResponse;
import com.chati.tuto.network.MqttChatResponse;
import com.chati.tuto.ui.main.MainActivity;
import com.telifoun.mqttchat.core.clbs.CallbackListener;
import com.telifoun.mqttchat.gui.MqttChat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDataSource {

    private MutableLiveData<Boolean> progressDialog;
    private MutableLiveData<ApiRegisterResponse> registerResponse;


    public RegisterDataSource() {
        this.progressDialog=new MutableLiveData<Boolean>();
        this.registerResponse=new MutableLiveData<ApiRegisterResponse>();
    }


    public void register(User user){
        APIService apiService= ServiceGenerator.createService(APIService.class);
        getProgressDialog().postValue(true);
        apiService.register(user).enqueue(new Callback<ApiRegisterResponse>() {
            @Override
            public void onResponse(Call<ApiRegisterResponse> call, Response<ApiRegisterResponse> response) {
                getProgressDialog().postValue(false);
                getRegisterResponse().postValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiRegisterResponse> call, Throwable t) {
                getProgressDialog().postValue(false);
                getRegisterResponse().postValue(new ApiRegisterResponse(false,t.getMessage()));
            }
        });

    }


    public MutableLiveData<Boolean> getProgressDialog() {
        return progressDialog;
    }

    public MutableLiveData<ApiRegisterResponse> getRegisterResponse() {
        return registerResponse;
    }
}
