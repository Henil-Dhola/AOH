package com.example.application_animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    TextView sign_up,forget, sign_up_btn;
    EditText editTextemail,editTextpassword;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sign_up = (TextView) findViewById(R.id.goto_signup);
        forget = (TextView) findViewById(R.id.forgot_pass);
        editTextemail =(EditText) findViewById(R.id.email_id);
        editTextpassword =(EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        sign_up_btn = findViewById(R.id.sign_up_btn);
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){

            startActivity(new Intent(Login.this,MainActivity2.class));
            finish();
        }
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Registration.class));
                finish();
            }
        });
         forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Forget_password.class));

            }
        });
    }

    private void userLogin() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        if(email.isEmpty()){
            editTextemail.setError("Email Id is required");
            editTextemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please Enter valid Email ID");
            editTextemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextpassword.setError("Password is required ");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length() < 6 ){
            editTextpassword.setError("Min length should be 6 characters required");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length() > 16){
            editTextpassword.setError("Max length should be 16 characters required");
            editTextpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                                progressBar.setVisibility(View.GONE);

                                startActivity(new Intent(Login.this,MainActivity2.class));
                                finishAffinity();

                        }else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Failed to login! Please check your details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}