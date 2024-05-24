package com.example.application_animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class webView extends AppCompatActivity {

    //WebView webView;
    ImageView fullimg;
    TextView fullpublish,fulltitle,fulldesc,fullauthor;

    ProgressBar progressBar;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        fullimg=findViewById(R.id.full_img);

        fullpublish=findViewById(R.id.full_publish);
        fulltitle=findViewById(R.id.full_title);
        fullauthor=findViewById(R.id.full_author);
        fulldesc=findViewById(R.id.full_desc);

        Glide.with(webView.this).load(getIntent().getStringExtra("image")).into(fullimg);
        fullpublish.setText(getIntent().getStringExtra("publish"));
        fulltitle.setText(getIntent().getStringExtra("title"));
        fulldesc.setText(getIntent().getStringExtra("description"));
        fullauthor.setText(getIntent().getStringExtra("author"));

//
//        progressBar=(ProgressBar) findViewById(R.id.progress);
//
//        webView=findViewById(R.id.webview);
//
//        Intent intent=getIntent();
//        String url=intent.getStringExtra("url");
//
//
//        webView.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//
//                progressBar.setVisibility(View.VISIBLE);
//                progressBar.setProgress(newProgress);
//                if(newProgress==100){
//                    progressBar.setVisibility(View.GONE);
//                }
//                super.onProgressChanged(view, newProgress);
//            }
//        });
//
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.loadUrl(url);
    }


}