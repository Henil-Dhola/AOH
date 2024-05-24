package com.example.application_animation;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.telephony.ims.ImsMmTelManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Array;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>  {

    Context context;
    ArrayList<ModelClass> mddelClassArrayList;
    Model model;
    String title,title1;
    ImageView save;


    public Adapter(Context context, ArrayList<ModelClass> mddelClassArrayList) {
        this.context = context;
        this.mddelClassArrayList = mddelClassArrayList;

    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,webView.class);
//                intent.putExtra("url",mddelClassArrayList.get(position).getUrl());
//                context.startActivity(intent);
                intent.putExtra("image",mddelClassArrayList.get(position).getUrlToImage());
                intent.putExtra("publish",mddelClassArrayList.get(position).getPublishedAt());
                intent.putExtra("title",mddelClassArrayList.get(position).getTitle());
                intent.putExtra("description",mddelClassArrayList.get(position).getDescription());
                intent.putExtra("author",mddelClassArrayList.get(position).getAuthor());
                context.startActivity(intent);
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = mddelClassArrayList.get(position).getTitle();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plan");
                intent.putExtra(Intent.EXTRA_TEXT,"Share from the Article of Hindustan : " + title + mddelClassArrayList.get(position).getUrl());
                context.startActivity(Intent.createChooser(intent,"Share with : "));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth mAuth=FirebaseAuth.getInstance();
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                model=new Model(mddelClassArrayList.get(position).getTitle(),mddelClassArrayList.get(position).getDescription(),mddelClassArrayList.get(position).getUrlToImage(),mddelClassArrayList.get(position).getUrl());

                firebaseDatabase.getReference().child("news").child(mAuth.getUid()).push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                Toast.makeText(context, "Saved Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        });



//                boolean isFavorite=readState();
//                if(isFavorite){
//                    save.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
//                }else {
//
//                    save.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
//                    isFavorite=true;
//                    saveState(isFavorite);
//                }

            }
        });
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.anim_two));
        holder.time.setText("Published At:- " + mddelClassArrayList.get(position).getPublishedAt());
        holder.author.setText(mddelClassArrayList.get(position).getAuthor());
        holder.heading.setText(mddelClassArrayList.get(position).getTitle());
        holder.content.setText(mddelClassArrayList.get(position).getDescription());
        Glide.with(context).load(mddelClassArrayList.get(position).getUrlToImage()).into(holder.imageView);

    }

//    private void saveState(boolean isFavorite) {
//        SharedPreferences sharedPreferences=this.context.getSharedPreferences("Favourite",Context.MODE_PRIVATE);
//        SharedPreferences.Editor sharepreferenceedit= sharedPreferences.edit();
//        sharepreferenceedit.putBoolean("State",isFavorite);
//        sharepreferenceedit.commit();
//    }
//
//    private boolean readState() {
//        SharedPreferences sharedPreferences=this.context.getSharedPreferences("Favourite",Context.MODE_PRIVATE);
//        return sharedPreferences.getBoolean("State",true);
//    }


    @Override
    public int getItemCount() {

        return mddelClassArrayList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView heading,content,author,time;
        CardView cardView;
        ImageView imageView,share;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading=itemView.findViewById(R.id.mainhead);
            content=itemView.findViewById(R.id.content);
            author=itemView.findViewById(R.id.author);
            time=itemView.findViewById(R.id.time);
            imageView=itemView.findViewById(R.id.imageView);
            cardView=itemView.findViewById(R.id.cardview);
            share=itemView.findViewById(R.id.share);
            save =itemView.findViewById(R.id.save);


        }


    }


}
