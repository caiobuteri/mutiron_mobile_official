package com.example.mutiron2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mutiron2.Event;
import com.example.mutiron2.R;
import com.example.mutiron2.activity.ViewFeedActivity;
import com.example.mutiron2.model.MyViewHolder;


import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    Context context;
    List<Event> events;

    public MyAdapter(Context context, List<Event> products) {
        this.context = context;
        this.events = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.activity_my_event, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Event event = this.events.get(position);

        TextView tvTitleList = holder.itemView.findViewById(R.id.tvTitleList);
        tvTitleList.setText(event.getTitle1()); //seta no Text View o nome na lista dentro

        TextView tvDescriptionList = holder.itemView.findViewById(R.id.tvDescriptionList);
        tvDescriptionList.setText(event.getDescription());

        ImageView imageView = holder.itemView.findViewById(R.id.imvPhotoList);
        imageView.setImageBitmap(event.getPhoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() { //quando clicar
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewFeedActivity.class); //mover de tela
                i.putExtra("eid", event.getEid()); //devo adicionar um id pro evento
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }
}
