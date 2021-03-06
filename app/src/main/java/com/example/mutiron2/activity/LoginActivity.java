package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mutiron2.R;
import com.example.mutiron2.util.Config;
import com.example.mutiron2.util.HttpRequest;
import com.example.mutiron2.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnEntrarLogin = findViewById(R.id.btnEntrarLogin);
        btnEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etLogin = findViewById(R.id.etUserLogin);
                final String login = etLogin.getText().toString();

                EditText etPassword = findViewById(R.id.etPasswordLogin);
                final String password = etPassword.getText().toString();


                Log.d("Teste", "Aqui foi");
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Teste", "Chegou carai");
                        HttpRequest httpRequest = new HttpRequest( "https://mutiron.herokuapp.com/mobile/loginMobile", "POST", "UTF-8");
                        Log.d("login", login);
                        Log.d("password", password);
                        httpRequest.addParam("login", login);
                        httpRequest.addParam("password", password);
                        Log.d("Teste", "Aqui foi 2");

                        try {
                            InputStream is = httpRequest.execute();
                            String result = Util.inputStream2String(is, "UTF-8");
                            httpRequest.finish();
                            Log.d("Teste", "result = " + result );
                            Log.d("Teste", "resultado");
                            JSONObject jsonObject = new JSONObject(result);
                            final int success = jsonObject.getInt("success");
                            final int id = jsonObject.getInt("id");
                            if(success == 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Config.setUserId(LoginActivity.this, id);
                                        Config.setLogin(LoginActivity.this, login);
                                        Config.setPassword(LoginActivity.this, password);
                                        Toast.makeText(LoginActivity.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(LoginActivity.this, ViewFeedActivity.class);
                                        startActivity(i);
                                    }
                                });
                            }
                            else {
                                final String error = jsonObject.getString("error");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
                                    }

                                });
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


               /* //pega os dados
                //verifica se ta tudo ok
                Intent i = new Intent(LoginActivity.this, ViewFeedActivity.class);
                startActivity(i); */
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