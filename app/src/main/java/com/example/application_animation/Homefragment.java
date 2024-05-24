package com.example.application_animation;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Homefragment extends Fragment {


    String api = "00a0685c8e674eda998dfb3406f0a40c";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "in";
    ShimmerFrameLayout shimmerFrameLayout;

    private RecyclerView recyclerViewofhome;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.homefragment, null);
        recyclerViewofhome = v.findViewById(R.id.recyclehome);
        shimmerFrameLayout=v.findViewById(R.id.shimmer);
        modelClassArrayList = new ArrayList<>();
        recyclerViewofhome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelClassArrayList);
        recyclerViewofhome.setAdapter(adapter);

        findNews();


        return v;
    }

    public void findNews() {

        ApiUtilities.getApiInterface().getNews(country, 100, api).enqueue(new Callback<mainNews>() {

            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()) {
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    recyclerViewofhome.setVisibility(View.VISIBLE);
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }


}

