package com.example.application_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.security.cert.Extension;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isConnected();

    }
    private void isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activenetwork = connectivityManager.getActiveNetworkInfo();

        if ((null != activenetwork)) {
            if (activenetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Thread thread=new Thread(){
                    public void run(){
                        try {
                            sleep(3000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {

                            startActivity(new Intent(MainActivity.this,Login.class));
                        }
                        finish();
                    }

                };thread.start();

            }
            if (activenetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                Thread thread=new Thread(){
                    public void run(){
                        try {
                            sleep(3000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            startActivity(new Intent(MainActivity.this,Login.class));

                        }
                        finish();
                    }

                };thread.start();


            }
        }else {

            Thread thread=new Thread(){
                public void run(){
                    try {
                        sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        startActivity(new Intent(MainActivity.this,MainActivity3.class));
                    }
                    finish();
                }

            };thread.start();


        }
    }
}
