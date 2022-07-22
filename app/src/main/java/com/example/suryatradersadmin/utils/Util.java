package com.example.suryatradersadmin.utils;

import android.content.Context;
import android.content.Intent;

public class Util <Data>{


    public static Intent setIntent(Context context, Class destination) {

        Intent intent = new Intent(context, destination);
        context.startActivity(intent);

        return intent ;
    }


    public Intent setIntentExtra(Context context, Class destination, String key, Data data) {
        Intent intent = new Intent(context, destination);
        intent.putExtra(key, (android.os.Parcelable) data);
        context.startActivity(intent);

        return intent ;
    }
}
