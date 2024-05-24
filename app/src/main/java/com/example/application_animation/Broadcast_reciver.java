package com.example.application_animation;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Broadcast_reciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String status=Check_Internet.getNetwork(context);
        if(status.equals("connected")){

           // Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
        }else if (status.equals("disconnected")){
            Intent intent1=new Intent(context,MainActivity3.class);
            context.startActivity(intent1);
            ((Activity)context).finish();
           // Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
        }
    }

}
