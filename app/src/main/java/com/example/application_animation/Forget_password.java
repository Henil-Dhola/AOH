package com.example.application_animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_password extends AppCompatActivity {
    TextView btnforget,btnlogin;
    EditText resetedit;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        btnlogin=findViewById(R.id.login);
        btnforget = (TextView) findViewById(R.id.forgot);
        resetedit = (EditText) findViewById(R.id.email_id);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forget_password.this,Login.class));
                finish();
            }
        });
        btnforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = resetedit.getText().toString().trim();
        if(email.isEmpty()){
            resetedit.setError("Email is Required");
            resetedit.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            resetedit.setError("Please Enter valid email");
            resetedit.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(Forget_password.this,Login.class));
                            finishAffinity();
                            Toast.makeText(Forget_password.this, "Check your email", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }else {

                            Toast.makeText(Forget_password.this, "Try again!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}