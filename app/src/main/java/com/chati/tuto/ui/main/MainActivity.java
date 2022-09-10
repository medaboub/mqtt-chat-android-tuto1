package com.chati.tuto.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chati.tuto.R;
import com.telifoun.mqttchat.core.ui.PresenceActivityA;
import com.telifoun.mqttchat.gui.ui.fragments.mqttchat.MqttChatFragment;

public class MainActivity extends PresenceActivityA {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.mqttchatFragment,new MqttChatFragment(), "mqttchat").commit();
    }
}