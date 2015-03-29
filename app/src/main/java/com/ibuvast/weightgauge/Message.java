package com.ibuvast.weightgauge;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by calistus on 3/21/2015.
 */
public class Message {
    public static void message(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
