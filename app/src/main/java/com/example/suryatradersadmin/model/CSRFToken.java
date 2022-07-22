package com.example.suryatradersadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CSRFToken {

    @SerializedName("csrf")
    @Expose
    private String csrf;

    public String getCsrf() {
        return csrf;
    }

    public void setCsrf(String csrf) {
        this.csrf = csrf;
    }

    public CSRFToken(String csrf) {
        this.csrf = csrf;
    }

    public CSRFToken() {
    }


}
