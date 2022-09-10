package com.chati.tuto.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.chati.tuto.R;
import com.chati.tuto.databinding.ActivityRegisterBinding;
import com.chati.tuto.models.User;
import com.chati.tuto.network.ApiRegisterResponse;
import com.chati.tuto.ui.login.LoginActivity;
import com.chati.tuto.ui.main.MainActivity;
import com.telifoun.mqttchat.core.clbs.CallbackListener;
import com.telifoun.mqttchat.gui.MqttChat;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private RegisterViewModel mRegisterViewModel;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_register);

        mRegisterViewModel= new ViewModelProvider(this).get(RegisterViewModel.class);
        mRegisterViewModel.getProgressDialog().observe(RegisterActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    mProgressDialog= ProgressDialog.show(RegisterActivity.this,"",
                            "Please Wait",true);
                }else{
                    mProgressDialog.dismiss();
                }
            }
        });
        mRegisterViewModel.getRegisterResponse().observe(RegisterActivity.this, new Observer<ApiRegisterResponse>() {
            @Override
            public void onChanged(ApiRegisterResponse apiRegisterResponse) {
              if(apiRegisterResponse.getOk()){
                  Toast.makeText(RegisterActivity.this,"Register Success ", Toast.LENGTH_LONG).show();
                  Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                  startActivity(i);
              }else{
                Toast.makeText(RegisterActivity.this, apiRegisterResponse.getError(), Toast.LENGTH_LONG).show();
              }
            }
        });
        binding.registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mRegisterViewModel.register(new User(0,
                        binding.name.getText().toString(),
                        binding.surname.getText().toString(),
                        binding.email.getText().toString(),
                        binding.password.getText().toString()));

            }
        });
    }


}