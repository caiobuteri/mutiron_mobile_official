package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mutiron2.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btnParticipateProfile = findViewById(R.id.btnParticipateProfile);
        btnParticipateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atualiza o rv pra aparece os eventos que o perfil esta participando
            }
        });

        Button btnLikedProfile = findViewById(R.id.btnLikedProfile);
        btnLikedProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //atualiza o rv pra aparece os eventos que o perfil curtiu
            }
        });
    }
}