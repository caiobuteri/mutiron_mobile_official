package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mutiron2.Event;
import com.example.mutiron2.R;
import com.example.mutiron2.model.ViewEventoViewModel;
import com.example.mutiron2.util.Config;
import com.example.mutiron2.util.HttpRequest;
import com.example.mutiron2.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_evento);

        Intent i = getIntent();
        String eid = i.getStringExtra("eid");


        final String login_usuario = Config.getLogin(this);
        final Integer id = Config.getUserId(this); //falta colocar que quando cadastra o evento pega o login da pessoa
        Integer criador; // preciso mudar o getEvent() adicionar pra pegar o criador

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
        criador = event.getValue().getCriador(); //falta colocar que quando cadastra o evento pega o login da pessoa


        if(criador == id){


            Button btnParticipateView = findViewById(R.id.btnParticipateView);
            btnParticipateView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mandar pra lista de participando no profile dessa pessoa
                    //mandar um toast falando que ja ta participando
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            HttpRequest httpRequest = new HttpRequest("mutiron.herokuapp.com/mobile/retornaEventoDetalhes", "POST", "UTF-8");
                            httpRequest.addParam("p_eid", eid);

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
                                            Toast.makeText(ViewEventoActivity.this, "Evento foi Participado com sucesso", Toast.LENGTH_LONG).show();
                                            setResult(RESULT_OK);
                                            finish();
                                        }
                                        else{
                                            Log.d("teste 1", "não deu success");
                                            Toast.makeText(ViewEventoActivity.this, "Evento não foi Participado com sucesso", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                                Log.d("teste 2", "não entrou essa");

                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Intent i = new Intent(ViewEventoActivity.this, ViewFeedActivity.class);
                    startActivity(i);
                }
            });

            Button cbLikedView = findViewById(R.id.cbLikedView);
            cbLikedView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mandar pra lista de participando no profile dessa pessoa
                    //mandar um toast falando que ja ta participando
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            HttpRequest httpRequest = new HttpRequest("productifes.herokuapp.com/add_Liked_list", "POST", "UTF-8");
                            httpRequest.addParam("L_eid", eid);

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
                                            Toast.makeText(ViewEventoActivity.this, "Evento foi Curtido com sucesso", Toast.LENGTH_LONG).show();
                                            setResult(RESULT_OK);
                                            finish();
                                        }
                                        else{
                                            Log.d("teste 1", "não deu success");
                                            Toast.makeText(ViewEventoActivity.this, "Evento não foi Não foi com sucesso", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                                Log.d("teste 2", "não entrou essa");

                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Intent i = new Intent(ViewEventoActivity.this, ViewFeedActivity.class);
                    startActivity(i);
                }
            });
        }


    }
}