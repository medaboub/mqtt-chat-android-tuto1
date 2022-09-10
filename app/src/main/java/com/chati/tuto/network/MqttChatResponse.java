package com.chati.tuto.network;

import com.chati.tuto.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MqttChatResponse {

    public MqttChatResponse(){
        this.ok = Boolean.TRUE;
    }
    public MqttChatResponse(String error) {
        this.ok = Boolean.FALSE;
        this.error = error;
    }

    private Boolean ok;

    private String error;

    public Boolean getOk() {
        return ok;
    }

    public String getError() {
        return error;
    }


}
