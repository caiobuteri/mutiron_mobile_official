package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mutiron2.R;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        Button btnCadastrarCadastro = findViewById(R.id.btnCadastrarCadastro);
        btnCadastrarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pega os dados
                //verifica se ta tudo ok
                Intent i = new Intent(CadastrarUsuarioActivity.this, ViewFeedActivity.class);
                startActivity(i);
            }
        });
    }
}