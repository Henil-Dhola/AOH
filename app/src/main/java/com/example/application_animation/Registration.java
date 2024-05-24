package com.example.application_animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    private EditText username,editTextnumber,editTextemail,editTextpassword;
    private FirebaseAuth  mAuth;
    private ProgressBar progressBar;
    TextView btn,btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        btn_login=findViewById(R.id.login);
        username = (EditText) findViewById(R.id.name);
        editTextnumber = (EditText) findViewById(R.id.mobile);
        editTextemail = (EditText) findViewById(R.id.email_id);
        editTextpassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        btn = (TextView) findViewById(R.id.sign_up);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,Login.class));
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               registration();

            }
        });
    }

    private void registration() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        String fullname = username.getText().toString().trim();
        String mobile = editTextnumber.getText().toString().trim();

        if(fullname.isEmpty()){
            username.setError("Username is required");
            username.requestFocus();
            return;
        }
        if(fullname.length() >=20){
            username.setError("Not allowed more than 20 character in username");
            username.requestFocus();
            return;
        }
        if(mobile.isEmpty()){
            editTextnumber.setError("Mobile number is required");
            editTextnumber.requestFocus();
            return;
        }

        if(!mobile.matches("[0-9]{10}")){
            editTextnumber.setError("Entered mobile number is must be 10 digits ");
            editTextnumber.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextemail.setError("Email Address is required");
            editTextemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please Provide valid email ");
            editTextemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextpassword.setError("Password is required");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextpassword.setError("Min password length should be 6 characters");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length() > 16){
            editTextpassword.setError("Max password length should be 16 characters");
            editTextpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User(fullname,mobile,email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Registration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(Registration.this,Login.class));
                                                finish();
                                            }else{
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(Registration.this, "Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Registration.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}