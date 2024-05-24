package com.example.application_animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    TextView name,phone,email,welcome,singout;
    String fullname,mobile,emailid;
    FirebaseAuth auth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name=(TextView) findViewById(R.id.txtname);
        phone=(TextView) findViewById(R.id.txtmobile);
        email=(TextView) findViewById(R.id.txtemail);
        welcome=findViewById(R.id.welcome);
        singout=findViewById(R.id.logout);
        singout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deleteuser();
              //  deletecurrentuser();
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(UserProfile.this,Login.class));
                finishAffinity();
                Toast.makeText(UserProfile.this, "You Sign Out", Toast.LENGTH_SHORT).show();
            }
        });
        progressBar=(ProgressBar) findViewById(R.id.profileprogres);

        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if(user==null){

            Toast.makeText(this, "Ooops...", Toast.LENGTH_SHORT).show();
        }else {
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(user);
        }
        
    }


    private void deletecurrentuser() {

                FirebaseUser user=auth.getCurrentUser();
                user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                           public void onSuccess(Void unused) {
                                Toast.makeText(UserProfile.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                                auth.signOut();
                                startActivity(new Intent(UserProfile.this,Login.class));
                                finishAffinity();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserProfile.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void deleteuser() {
        FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getInstance().getCurrentUser().getUid())
                .setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        auth.getInstance().getCurrentUser().delete();
                    }
                });
    }


    private void showUserProfile(FirebaseUser user) {
        String userID=user.getUid();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user1=snapshot.getValue(User.class);
                if(user1!=null){
                    fullname=user1.fullname;
                    emailid=user1.email;
                    mobile=user1.mobile;
                    welcome.setText("Welcome "+fullname);
                    name.setText("  "+fullname);
                    phone.setText("  "+mobile);
                    email.setText("  "+emailid);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(UserProfile.this, "Something went wrong.!.!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}