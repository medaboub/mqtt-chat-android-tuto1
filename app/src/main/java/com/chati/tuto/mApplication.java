package com.chati.tuto;

import android.app.Application;

import com.telifoun.mqttchat.gui.MqttChat;

public class mApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new MqttChat.getBuilder()
                .context(this.getApplicationContext())
                .appIcon(R.mipmap.ic_launcher)
                .domain("mqtt-chat.com")
                .appId("mqttchat-87226030")
                .appSecret("mqttchat-56vvfbvnpe0uvuid")
                .debugMode(true)
                .build();
    }
}
