package com.chati.tuto.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chati.tuto.R;
import com.chati.tuto.databinding.ActivityLoginBinding;
import com.chati.tuto.databinding.ActivityRegisterBinding;
import com.chati.tuto.models.User;
import com.chati.tuto.network.ApiLoginResponse;
import com.chati.tuto.network.ApiRegisterResponse;
import com.chati.tuto.network.MqttChatResponse;
import com.chati.tuto.ui.main.MainActivity;
import com.chati.tuto.ui.register.RegisterActivity;
import com.chati.tuto.ui.register.RegisterViewModel;
import com.telifoun.mqttchat.core.clbs.CallbackListener;
import com.telifoun.mqttchat.gui.MqttChat;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel mLoginViewModel;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login);

        mLoginViewModel= new ViewModelProvider(this).get(LoginViewModel.class);
        mLoginViewModel.getProgressDialog().observe(LoginActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    mProgressDialog= ProgressDialog.show(LoginActivity.this,"",
                            "Please Wait",true);
                }else{
                    mProgressDialog.dismiss();
                }
            }
        });
        mLoginViewModel.getLoginResponse().observe(LoginActivity.this, new Observer<ApiLoginResponse>() {
            @Override
            public void onChanged(ApiLoginResponse apiLoginResponse) {
                if(apiLoginResponse.getOk()){
                    mLoginViewModel.connectToChat(getApplication(),apiLoginResponse.getResponse().getUserId());
                }else{
                    Toast.makeText(LoginActivity.this,apiLoginResponse.getError(), Toast.LENGTH_LONG).show();
                }
            }
        });
        mLoginViewModel.getMqttChatResponse().observe(LoginActivity.this, new Observer<MqttChatResponse>() {
            @Override
            public void onChanged(MqttChatResponse mqttchatResponse) {
                if(mqttchatResponse.getOk()){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this,mqttchatResponse.getError(), Toast.LENGTH_LONG).show();
                }
            }
        });
        binding.loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               mLoginViewModel.login(binding.email.getText().toString(),
                        binding.password.getText().toString());

            }
        });
        binding.registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
               startActivity(i);
            }
        });

    }

}