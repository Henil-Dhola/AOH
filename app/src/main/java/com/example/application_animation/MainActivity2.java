package com.example.application_animation;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;



import android.content.BroadcastReceiver;

import android.content.Intent;
import android.content.IntentFilter;

import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import com.onesignal.OneSignal;


public class MainActivity2 extends AppCompatActivity {
    private static final String ONESIGNAL_APP_ID = "f0b4069a-e232-4fa1-b61a-eace156fb93b";
    TabLayout tabLayout;
    TabItem home, science, health, IT, enter, sport;
    PagerAdapter pagerAdapter;
    Toolbar toolbar;

    //BroadcastReceiver broadcastReceiver=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        broadcastReceiver=new Broadcast_reciver();
//        Internetstatus();


        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        OneSignal.promptForPushNotifications();


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        home = findViewById(R.id.home);
        science = findViewById(R.id.sci);
        health = findViewById(R.id.health);
        IT = findViewById(R.id.IT);
        enter = findViewById(R.id.Ent);
        sport = findViewById(R.id.sport);


        ViewPager viewPager = findViewById(R.id.fcontainer);
        tabLayout = findViewById(R.id.include);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 7);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5 || tab.getPosition() == 6) {
                    pagerAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid = item.getItemId();
//        if(itemid==R.id.logout){
//            FirebaseAuth.getInstance().signOut();
//            finish();
//            startActivity(new Intent(MainActivity2.this,Login.class));
//
//        }
//        if(itemid==R.id.language){
//
//            startActivity(new Intent(MainActivity2.this,Showallnews.class));
//        }
//
        if(itemid==R.id.profile){
            startActivity(new Intent(MainActivity2.this,UserProfile.class));

        }
        if(itemid==R.id.settings){
            startActivity(new Intent(MainActivity2.this,SettingActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }

//    public void Internetstatus(){
//        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(broadcastReceiver);
//    }

}

