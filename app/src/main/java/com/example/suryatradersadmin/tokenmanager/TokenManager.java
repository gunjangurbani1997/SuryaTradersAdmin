package com.example.suryatradersadmin.tokenmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.suryatradersadmin.model.CSRFToken;
import com.example.suryatradersadmin.views.LoginActivity;


public class TokenManager {


        public static SharedPreferences sharedPreferences;
        private static final String TAG="TokenManager ";
        private SharedPreferences.Editor editor,editor1;
        public static com.example.suryatradersadmin.tokenmanager.TokenManager mInstance;
        private int Mode=0;
        public static final String TOKEN="JWT_TOKEN";
        public static final String FIREBASE_TOKEN="FIREBASE_TOKEN";
        public static final String KEY_USER_NAME="username";
        public static final String KEY_JWT_TOKEN="jwt";
        public static final String CSRF_TOKEN="csrf";
        private static final String IS_LOGIN = "IsLoggedIn";
        private Context context;

    public TokenManager(){}

        public TokenManager(Context context){
            Log.d(TAG,"Context "+ context);
            this.context=context;
            sharedPreferences=context.getSharedPreferences(TOKEN,Mode);
        }

        public void createSession(String username,String jwt_token)
        {
            editor=sharedPreferences.edit();
            editor.putString(KEY_JWT_TOKEN,jwt_token);
            editor.putString(KEY_USER_NAME,username);
            editor.putBoolean(IS_LOGIN, true);
            editor.commit();
        }




    public void createFirebaseNotification(String firebaseToken)
    {
        editor.putString(FIREBASE_TOKEN,firebaseToken);
        Log.d(TAG, FIREBASE_TOKEN);
        editor.commit();
    }

    public  String getString(String key, String defValue)
    {
        if (sharedPreferences!=null) {
            return sharedPreferences.getString(KEY_JWT_TOKEN, "abc");
        }
        else
            return "";
    }

    public  String getUserName(String key, String defValue)
    {
        Log.d(TAG,"Hello username "+sharedPreferences.getString(KEY_USER_NAME, "def"));
               return sharedPreferences.getString(KEY_USER_NAME, "def");
    }

    public static synchronized com.example.suryatradersadmin.tokenmanager.TokenManager getInstance() {
        if (mInstance==null)
        {
            mInstance = new com.example.suryatradersadmin.tokenmanager.TokenManager();
         }
        return mInstance;
    }

    public  String getCSRFToken(String key, String defValue)
    {
        Log.d(TAG,"CSRF Token "+sharedPreferences.getString(CSRF_TOKEN, "abc"));
            return sharedPreferences.getString(CSRF_TOKEN, "abc");
    }

    public void createCSRFTokenSession(CSRFToken csrfToken)
    {
        editor=sharedPreferences.edit();
        editor.putString(CSRF_TOKEN,csrfToken.getCsrf());
        Log.d(TAG, CSRF_TOKEN);
        editor.commit();
    }



    public boolean checkLogin(){
        return this.isLoggedIn();
    }

    public  void logoutUser(){

       Boolean loginCheck= checkLogin();

       if (loginCheck)
       {
           // Clearing all data from Shared Preferences
           editor = sharedPreferences.edit();
           editor.clear();
           editor.commit();
           Intent i = new Intent(context, LoginActivity.class);
           // Closing all the Activities
           i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           // Add new Flag to start new Activity
           i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           context.startActivity(i);
       }
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }
}
