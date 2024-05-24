package com.example.application_animation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class ITfragment extends Fragment {
    String api="00a0685c8e674eda998dfb3406f0a40c";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country="in";
    ShimmerFrameLayout shimmerFrameLayout;
    private RecyclerView recyclerViewoftechnology;
    private String Category="technology";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.technologyfragment,null);

        recyclerViewoftechnology=v.findViewById(R.id.recycleIT);
        shimmerFrameLayout=v.findViewById(R.id.shimmer);
        modelClassArrayList=new ArrayList<>();
        recyclerViewoftechnology.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(),modelClassArrayList);
        recyclerViewoftechnology.setAdapter(adapter);
        findNews();
        return v;
    }

    private void findNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country,Category,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if (response.isSuccessful()){
                    shimmerFrameLayout.setVisibility(View.GONE);
                    recyclerViewoftechnology.setVisibility(View.VISIBLE);
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
