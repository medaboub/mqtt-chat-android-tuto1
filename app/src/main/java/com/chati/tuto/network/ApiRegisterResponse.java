package com.chati.tuto.network;

import com.chati.tuto.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiRegisterResponse {

    public ApiRegisterResponse(Boolean ok, String error) {
        this.ok = ok;
        this.error = error;
    }

    @SerializedName("ok")
    @Expose
    private Boolean ok;


    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("response")
    @Expose
    private String response;

    public Boolean getOk() {
        return ok;
    }

    public String getError() {
        return error;
    }

    public String getResponse() {
        return response;
    }
}
