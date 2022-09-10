package com.chati.tuto.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User  {

    @SerializedName("userid")
    @Expose(serialize = false, deserialize = true)
    private int userid;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    public int getUserId(){
        return userid;
    }

    public User(int userid, String name, String surname, String email, String password) {
        this.userid = userid;
        this.name=name;
        this.surName=surname;
        this.email=email;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
