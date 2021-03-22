package com.example.mutiron2.model;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mutiron2.Event;
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

public class ProfileViewModel extends ViewModel {

    MutableLiveData<List<Event>> eventsP; //lista de eventos participando
    MutableLiveData<List<Event>> eventsL; //lista de eventos curtindo



    void loadEventsP(){ //entrar em contato com servidor e pedir a lista de eventos participando(a partir de um thread nova)
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<Event> eventsList = new ArrayList<>(); //endereço do banco pra acessar os eventos que esta participando
                HttpRequest httpRequest = new HttpRequest("https://productifes.herokuapp.com/get_all_participatesEvents.php", "GET", "UTF-8");
                try { //tratamento de execeção
                    InputStream is = httpRequest.execute();//executa essa requisição
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result); //objeto tipo Json
                    int success = jsonObject.getInt("success");
                    if(success == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("events");
                        for(int i = 0; i < jsonArray.length(); i++){ //inicio da construção de uma lista de produtos
                            JSONObject jEvent = jsonArray.getJSONObject(i); //identifica um produto com um JsonObject

                            String eid = jEvent.getString("eid"); //Pega o pid desse produto do json
                            String title = jEvent.getString("title"); //pega o nome desse produto do json
                            String img = jEvent.getString("img");

                            Bitmap photo = Util.base642Bitmap(img);

                            Event event = new Event(eid, title, photo); //cria um novo produto
                            eventsList.add(event); //adiciona esse produto nessa lista de produtos
                        }
                        eventsP.postValue(eventsList); //atualiza essa nova lista de produtos no mutable liveData
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void loadEventsL(){ //entrar em contato com servidor e pedir a lista de eventos participando(a partir de um thread nova)
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<Event> eventsList = new ArrayList<>(); //enderenço do banco pra acessar os eventos curtidos
                HttpRequest httpRequest = new HttpRequest("https://productifes.herokuapp.com/get_all_LikedEvents.php", "GET", "UTF-8");
                try { //tratamento de execeção
                    InputStream is = httpRequest.execute();//executa essa requisição
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result); //objeto tipo Json
                    int success = jsonObject.getInt("success");
                    if(success == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("events");
                        for(int i = 0; i < jsonArray.length(); i++){ //inicio da construção de uma lista de produtos
                            JSONObject jEvent = jsonArray.getJSONObject(i); //identifica um produto com um JsonObject

                            String eid = jEvent.getString("eid"); //Pega o pid desse produto do json
                            String title = jEvent.getString("title"); //pega o nome desse produto do json
                            String img = jEvent.getString("img");

                            Bitmap photo = Util.getBitmap(img, 100, 100);

                            Event event = new Event(eid, title, photo); //cria um novo produto
                            eventsList.add(event); //adiciona esse produto nessa lista de produtos
                        }
                        eventsL.postValue(eventsList); //atualiza essa nova lista de produtos no mutable liveData
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LiveData<List<Event>> getEventsP() {
        if(eventsP == null){
            eventsP = new MutableLiveData<List<Event>>();
            loadEventsP();
        }
        return eventsP;
    }

    public LiveData<List<Event>> getEventsL() {
        if(eventsL == null){
            eventsL = new MutableLiveData<List<Event>>();
            loadEventsL();
        }
        return eventsL;
    }
}
