package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mutiron2.Event;
import com.example.mutiron2.R;
import com.example.mutiron2.model.ViewEventoViewModel;

import java.text.SimpleDateFormat;

public class ViewEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_evento);

        Intent i = getIntent();
        String eid = i.getStringExtra("eid");

        ViewEventoViewModel viewEventoViewModel = new ViewModelProvider(this, new ViewEventoViewModel.ViewEventoViewModelFactory(eid)).get(ViewEventoViewModel.class);

        LiveData<Event> event = viewEventoViewModel.getEvent(); //observa os detalhes do produtos
        event.observe(this, new Observer<Event>() {
            @Override
            public void onChanged(Event event) {
                ImageView imvEventPhoto = findViewById(R.id.imvPhotoView);
                imvEventPhoto.setImageBitmap(event.getPhoto());

                TextView tvTitle = findViewById(R.id.tvTitleView);
                tvTitle.setText(event.getTitle1());

                TextView tvDate = findViewById(R.id.tvDateView);
                tvDate.setText(new SimpleDateFormat("HH:mm dd/MM/yyyy").format(event.getDate()));
                //tvDate.setText(event.getData());

                TextView tvDescription = findViewById(R.id.tvDescriptionView);
                tvDescription.setText(event.getDescription());

                TextView tvLocation = findViewById(R.id.tvLocationView);
                tvLocation.setText(event.getLocalizacao());
            }
        });


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