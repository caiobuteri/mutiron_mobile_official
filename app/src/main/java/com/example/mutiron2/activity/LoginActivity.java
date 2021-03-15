package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mutiron2.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnEntrarLogin = findViewById(R.id.btnEntrarLogin);
        btnEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pega os dados
                //verifica se ta tudo ok
                Intent i = new Intent(LoginActivity.this, ViewFeedActivity.class);
                startActivity(i);
            }
        });
        Button btnCadastrarLogin = findViewById(R.id.btnCadastrarLogin);
        btnCadastrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,CadastrarUsuarioActivity.class);
                startActivity(i);
            }
        });
    }
}