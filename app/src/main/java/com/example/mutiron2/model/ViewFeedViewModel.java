package com.example.mutiron2.model;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mutiron2.Event;
import com.example.mutiron2.R;
import com.example.mutiron2.util.HttpRequest;
import com.example.mutiron2.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewFeedViewModel extends ViewModel {

    MutableLiveData<List<Event>> events;

    public LiveData<List<Event>> getEvents() {
        if(events == null){
            events = new MutableLiveData<List<Event>>();
            loadEvents();
        }
        return events;
    }


    public void refresh(){
        loadEvents();
    }

    void loadEvents(){ //entrar em contato com servidor e pedir a lista (a partir de um thread nova)
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<Event> eventsList = new ArrayList<>();
                HttpRequest httpRequest = new HttpRequest("https://mutiron.herokuapp.com/mobile/retornaEventos", "GET", "UTF-8");
                try { //tratamento de execeção
                    InputStream is = httpRequest.execute();//executa essa requisição
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result); //objeto tipo Json
                    int success = jsonObject.getInt("success");
                    if(success == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("eventos");
                        for(int i = 0; i < jsonArray.length(); i++){ //inicio da construção de uma lista de produtos
                            JSONObject jEvent = jsonArray.getJSONObject(i); //identifica um produto com um JsonObject

                            String id_evento = jEvent.getString("id_evento"); //Pega o pid desse produto do json
                            String title = jEvent.getString("titulo"); //pega o nome desse produto do json
                            String descricao = jEvent.getString("descricao");
                            /*String data = jEvent.getString("img");
                            String localizacao = jEvent.getString("img");
                            String id_criador = jEvent.getString("img");*/

                            // Bitmap photo = Util.getBitmap(img, 100, 100);

                            Event event = new Event(id_evento, title, descricao); //cria um novo produto
                            eventsList.add(event); //adiciona esse produto nessa lista de produtos
                        }
                        events.postValue(eventsList); //atualiza essa nova lista de produtos no mutable liveData
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
