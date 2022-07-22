package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthenticationResult implements Serializable {

    @SerializedName("msg")
    @Expose
    private String authenticationMsg ;
    @SerializedName("admin_id")
    @Expose
    private String admin_id ;
    @SerializedName("token")
    @Expose
    private Token token = null;

    public String getAuthenticationMsg() {
        return authenticationMsg;
    }

    public void setAuthenticationMsg(String authenticationMsg) {
        this.authenticationMsg = authenticationMsg;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String customerId) {
        this.admin_id = admin_id;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
