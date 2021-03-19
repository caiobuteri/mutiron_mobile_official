package com.example.mutiron2.model;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mutiron2.Event;
import com.example.mutiron2.util.Config;
import com.example.mutiron2.util.HttpRequest;
import com.example.mutiron2.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ViewEventoViewModel extends ViewModel {
    MutableLiveData<Event> event;
    String eid;

    public ViewEventoViewModel(String eid) {
        this.eid = eid;
    }

    public MutableLiveData<Event> getEvent() { //pega os detalhes do produto
        if(event == null){
            event = new MutableLiveData<Event>();
            loadEvent();
        }

        return event;
    }
    void loadEvent(){ //carrega os detalhes do produtos a partir do servidor
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest("https://productifes.herokuapp.com/get_product_details.php", "GET", "UTF-8");
                httpRequest.addParam("eid", eid);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result); //objeto tipo Json
                    int success = jsonObject.getInt("success");
                    if(success == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("event");
                        JSONObject jEvent = jsonArray.getJSONObject(0);

                        String title = jEvent.getString("title");
                        String description = jEvent.getString("description");
                        String date = jEvent.getString("date"); //peguei a string "dd/mm/yyyy"
                        Date data = new SimpleDateFormat("dd/MM/yyyy").parse(date); //passar no formato date
                        String location = jEvent.getString("location");


                        String imgBase64 = jEvent.getString("img");
                        String pureBase64Encoded = imgBase64.substring((imgBase64.indexOf(",") + 1));
                        Bitmap img = Util.base642Bitmap(pureBase64Encoded);

                        Event e = new Event(title, description, data, location,img);

                        event.postValue(e);

                    }
                } catch (IOException | JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static public class ViewEventoViewModelFactory implements ViewModelProvider.Factory{ //ensina View Model a aceitar paramentros
        String eid;

        public ViewEventoViewModelFactory(String eid) {
            this.eid = eid;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ViewEventoViewModel(eid); //cria uma instancia de criar um viewmodel com paramentros
        }
    }

}
