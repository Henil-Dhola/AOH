package com.example.application_animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.Checksum;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity {
    ListView listView;
    String[] setting={"Bookmark","Language","Theme","About","Logout"};
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Dialog dialog;
    Button btnyes,btnno;
   // boolean nightMode;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        listView=findViewById(R.id.list_setting);
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
      //  dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        btnyes=dialog.findViewById(R.id.yes_dialog);
        btnno=dialog.findViewById(R.id.no_dialog);
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "YES", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this, "NO", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //sharedPreferences=getSharedPreferences("MODE", Context.MODE_PRIVATE);
//        nightMode=sharedPreferences.getBoolean("night",false);
//        if(nightMode){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
        loadLocale();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_dropdown_item_1line,setting);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    startActivity(new Intent(SettingActivity.this, Showallnews.class));
                }else if(position == 1){
                   // changelanguage();
                    Toast.makeText(SettingActivity.this, setting[position]+" is Coming soon", Toast.LENGTH_SHORT).show();
                }
                else if (position == 4) {
                     // dialog.show();
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(SettingActivity.this, Login.class));
                    finishAffinity();
                } else {
                    Toast.makeText(SettingActivity.this, setting[position] + " is coming soon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void changelanguage() {
        final String languages[]={" English "," Gujarati "};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    setLocale("en");
                    recreate();
                }else if(which == 1){
                    setLocale("gu");
                    recreate();
                }

            }
        });
        builder.create();
        builder.show();
    }

    private void setLocale(String language) {
        Locale locale=new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("app_lang",language);
        editor.apply();
    }
    private void loadLocale(){
        SharedPreferences preferences=getSharedPreferences("Settings",MODE_PRIVATE);
        String language=preferences.getString("app_lang","");
        setLocale(language);
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingActivity.this);
        alertDialog.setTitle("Choose theme");
        String[] items = {"Light Theme","Dark Theme"};

        int checkedItem ;
        if( AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            checkedItem=1;
        }else {
            checkedItem=0;
        }

        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        editor= sharedPreferences.edit();
                        editor.putBoolean("night",false);
                        break;
                    case 1:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        editor= sharedPreferences.edit();
                        editor.putBoolean("night",true);
                        break;

                }
                editor.apply();

            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                //alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.teal_700));
                alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.teal_700));
            }
        });
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }
}
