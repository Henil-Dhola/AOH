package com.example.application_animation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;
    String Title;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference reference=firebaseDatabase.getReference("user").child(mAuth.getUid());
   // static ProgressBar progressBar;

    public MyAdapter(ArrayList<Model> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Model modelClass=mList.get(position);
        holder.title.setText(modelClass.getTitle());
        holder.content.setText(modelClass.getDescription());
        Glide.with(context).load(modelClass.getUrlToImage()).into(holder.image);
       // holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.anim_one));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,webView.class);
//                intent.putExtra("url",mList.get(position).getUrl());
//                context.startActivity(intent);
                intent.putExtra("image",mList.get(position).getUrlToImage());
                intent.putExtra("title",mList.get(position).getTitle());
                intent.putExtra("description",mList.get(position).getDescription());
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Title=modelClass.getTitle();
                FirebaseAuth mAuth=FirebaseAuth.getInstance();
                DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("news");
                reference.child(mAuth.getUid()).orderByChild("title").equalTo(Title).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            snapshot1.getRef().removeValue();
                        }
                        Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,content;
        ImageView image,delete;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.mainhead);
            content=itemView.findViewById(R.id.content);
            image = itemView.findViewById(R.id.imageView);
            cardView=itemView.findViewById(R.id.cardview);
            delete=itemView.findViewById(R.id.delete_news);
            //progressBar=itemView.findViewById(R.id.progressbar);
        }
    }


}
