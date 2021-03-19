package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mutiron2.Event;
import com.example.mutiron2.R;
import com.example.mutiron2.adapter.MyAdapter;
import com.example.mutiron2.model.ProfileViewModel;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RecyclerView rvProfile = findViewById(R.id.rvProfile);
        rvProfile.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvProfile.setLayoutManager(layoutManager);


        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        LiveData<List<Event>> participatesEvents = profileViewModel.getEventsP();
        LiveData<List<Event>> LikedEvents = profileViewModel.getEventsL();

        Button btnParticipateProfile = findViewById(R.id.btnParticipateProfile);
        btnParticipateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter myAdapter = new MyAdapter(ProfileActivity.this, participatesEvents.getValue());
                rvProfile.setAdapter(myAdapter);
            }
        });

        Button btnLikedProfile = findViewById(R.id.btnLikedProfile);
        btnLikedProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atualiza o rv pra aparece os eventos que o perfil curtiu
                MyAdapter myAdapter = new MyAdapter(ProfileActivity.this, LikedEvents.getValue());
                rvProfile.setAdapter(myAdapter);
            }
        });
    }
}