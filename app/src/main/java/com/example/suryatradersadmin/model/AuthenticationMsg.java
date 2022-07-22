package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationMsg {

    @Expose
    @SerializedName("msg")
    String authenticationMsg;

    @Expose
    @SerializedName("admin_id")
    String admin_id;

    public String getAuthenticationMsg() {
        return authenticationMsg;
    }

    public void setAuthenticationMsg(String authenticationMsg) {
        this.authenticationMsg = authenticationMsg;
    }

    public String getAdmin_id_id() {
        return admin_id;
    }

    public void setAdmin_id_id(String customer_id) {
        this.admin_id = admin_id;
    }
}
