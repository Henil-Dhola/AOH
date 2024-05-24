package com.example.application_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView btn=(TextView) findViewById(R.id.no_con);
        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isConnected();
            }
        });
    }
    private void isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activenetwork = connectivityManager.getActiveNetworkInfo();

        if ((null != activenetwork)) {

            if ((activenetwork.getType() == ConnectivityManager.TYPE_MOBILE) || (activenetwork.getType() == ConnectivityManager.TYPE_WIFI)) {
                if(mAuth.getCurrentUser()!=null){
                    startActivity(new Intent(MainActivity3.this,MainActivity2.class));
                    finish();
                }else {
                    Toast.makeText(this, "Please Login First ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity3.this,Login.class));
                    finish();
                }

            }
        }else {
            Toast.makeText(MainActivity3.this, "No internet Connection", Toast.LENGTH_SHORT).show();
        }
    }
}