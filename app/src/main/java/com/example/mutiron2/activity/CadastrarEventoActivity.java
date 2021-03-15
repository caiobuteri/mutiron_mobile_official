package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mutiron2.R;

public class CadastrarEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);

        Button btnAddEventAdd = findViewById(R.id.btnAddEventAdd);
        btnAddEventAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pega os dados: camera, data, localização, Título e Descrição
                //verifica se ta tudo ok
                //verifica se nenhum campo ta faltando
                //mandar pro banco de dados
                Intent i = new Intent(CadastrarEventoActivity.this, ViewFeedActivity.class);
                startActivity(i);
            }
        });

    }
}