package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mutiron2.R;
import com.example.mutiron2.model.CadastrarEventoViewModel;
import com.example.mutiron2.util.Config;
import com.example.mutiron2.util.HttpRequest;
import com.example.mutiron2.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastrarEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);

        CadastrarEventoViewModel cadastrarEventoViewModel = new ViewModelProvider(this).get(CadastrarEventoViewModel.class);
        String currentPhotoPath = cadastrarEventoViewModel.getCurrentPhotoPath();
        if (!currentPhotoPath.isEmpty()) {
            ImageView imvPhoto = findViewById(R.id.imvPhotoAdd);
            Bitmap bitmap = Util.getBitmap(currentPhotoPath, imvPhoto.getWidth(), imvPhoto.getHeight());
            imvPhoto.setImageBitmap(bitmap);
        }

        final String criador = Config.getLogin(this);


        Button btnAddEventAdd = findViewById(R.id.btnAddEventAdd);
        btnAddEventAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etTitle = findViewById(R.id.etTitleAdd);
                String title = etTitle.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(CadastrarEventoActivity.this, "O Campo Título não foi preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                EditText etLocation = findViewById(R.id.etLocationAdd);
                String location = etLocation.getText().toString();
                if(location.isEmpty()){
                    Toast.makeText(CadastrarEventoActivity.this, "O Campo Localização não foi preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                EditText etDescription = findViewById(R.id.etDescriptionAdd);
                String description = etDescription.getText().toString();
                if(description.isEmpty()){
                    Toast.makeText(CadastrarEventoActivity.this, "O Campo Descrição não foi preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                EditText etDate = findViewById(R.id.etDateAdd);
                String date = etDescription.getText().toString();
                if(date.isEmpty()){
                    Toast.makeText(CadastrarEventoActivity.this, "O Campo Data não foi preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }


                String currentPhotoPath = cadastrarEventoViewModel.getCurrentPhotoPath();
                if(currentPhotoPath.isEmpty()){
                    Toast.makeText(CadastrarEventoActivity.this, "O Campo FOTO não foi preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() { //tem que mudar esse endereço ai
                        HttpRequest httpRequest = new HttpRequest("https://mutiron.herokuapp.com/mobile/insereEventoMobile", "POST", "UTF-8");

                        httpRequest.addParam("title", title);
                        httpRequest.addParam("location", location);
                        httpRequest.addParam("date", date);
                        httpRequest.addParam("description", description);
                        httpRequest.addParam("criador", criador); // mudei agora caio
                        // httpRequest.addFile("img", new File(currentPhotoPath));

                        try {
                            InputStream is = httpRequest.execute();
                            String result = Util.inputStream2String(is, "UTF-8");
                            httpRequest.finish();

                            Log.d("HTTP_REQUEST_RESULT", result);

                            JSONObject jsonObject = new JSONObject(result); //objeto tipo Json
                            int success = jsonObject.getInt("success");
                            runOnUiThread(new Runnable() { //thread da interface para evitar de matar a aplicação
                                @Override
                                public void run() {
                                    if(success == 1){ //se o objeto foi adicionado com sucesso
                                        Log.d("teste 1", "deu success");
                                        Toast.makeText(CadastrarEventoActivity.this, "Evento foi adicionado com sucesso", Toast.LENGTH_LONG).show();
                                        setResult(RESULT_OK);
                                        finish();
                                    }
                                    else{
                                        Log.d("teste 1", "não deu success");
                                        Toast.makeText(CadastrarEventoActivity.this, "Evento não foi adicionado com sucesso", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Log.d("teste 2", "não entrou essa");

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

                /*
                //pega os dados: camera, data, localização, Título e Descrição
                //verifica se ta tudo ok
                //verifica se nenhum campo ta faltando
                //mandar pro banco de dados
                Intent i = new Intent(CadastrarEventoActivity.this, ViewFeedActivity.class);
                startActivity(i); */
            }
        });

    }
}