package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mutiron2.MyAdapter;
import com.example.mutiron2.MyEvent;
import com.example.mutiron2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ViewFeedActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddEventFeed = findViewById(R.id.btnAddEventFeed);
        btnAddEventFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewFeedActivity.this, CadastrarEventoActivity.class);
                startActivity(i);
            }
        });

        ImageView imvPhotoFeed = findViewById(R.id.imvPhotoFeed);
        imvPhotoFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewFeedActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

    }

}