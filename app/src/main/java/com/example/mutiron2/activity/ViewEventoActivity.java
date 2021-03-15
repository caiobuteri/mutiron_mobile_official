package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mutiron2.R;

public class ViewEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_evento);

        Button btnParticipateView = findViewById(R.id.btnParticipateView);
        btnParticipateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mandar pra lista de participando no profile dessa pessoa
                //mandar um toast falando que ja ta participando
                Intent i = new Intent(ViewEventoActivity.this, ViewFeedActivity.class);
                startActivity(i);
            }
        });

        Button cbLikedView = findViewById(R.id.cbLikedView);
        cbLikedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mandar pra lista de Curtidos no profile dessa pessoa
                //mandar um toast falando que ja ta curtindo
                Intent i = new Intent(ViewEventoActivity.this, ViewFeedActivity.class);
                startActivity(i);
            }
        });
    }
}