package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class CadastrarUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        final String creator = Config.getLogin(this);

        Button btnCadastrarCadastro = findViewById(R.id.btnCadastrarCadastro);
        btnCadastrarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etNewLogin =  findViewById(R.id.etUserCadastro);
                final String newLogin = etNewLogin.getText().toString();
                if(newLogin.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de login não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etNewPassword =  findViewById(R.id.etPasswordCadastro);
                final String newPassword = etNewPassword.getText().toString();
                if(newPassword.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de senha não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etNewPasswordCheck =  findViewById(R.id.etPasswordAgainCadastro);
                String newPasswordCheck = etNewPasswordCheck.getText().toString();
                if(newPasswordCheck.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de checagem de senha não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                /*EditText etName =  findViewById(R.id.etNameCadastro);
                String newName = etNewPasswordCheck.getText().toString();
                if(newName.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de Nome não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }*/

                if(!newPassword.equals(newPasswordCheck)) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Senha não confere", Toast.LENGTH_LONG).show();
                    return;
                }




                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {//alterar o endereço daqui tambem
                        HttpRequest httpRequest = new HttpRequest("https://mutiron.herokuapp.com/mobile/cadastroMobile", "POST", "UTF-8");
                        httpRequest.addParam("login", newLogin);
                        httpRequest.addParam("password", newPassword);

                        httpRequest.addParam("criador", creator); //manda pro servidor o quem foi o criador

                        try {
                            InputStream is = httpRequest.execute();
                            String result = Util.inputStream2String(is, "UTF-8");
                            httpRequest.finish();

                            JSONObject jsonObject = new JSONObject(result);
                            final int success = jsonObject.getInt("success");
                            if(success == 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CadastrarUsuarioActivity.this, "Novo usuario registrado com sucesso", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                });
                            }
                            else {
                                final String error = jsonObject.getString("error");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CadastrarUsuarioActivity.this, error, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                /*
                //pega os dados
                //verifica se ta tudo ok
                Intent i = new Intent(CadastrarUsuarioActivity.this, ViewFeedActivity.class);
                startActivity(i); */
            }
        });
    }
}