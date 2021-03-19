package com.example.mutiron2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import java.util.Date;

public class Event extends AppCompatActivity {
    public String eid;
    public String title;
    public String description;
    public Date date;
    public String localizacao;
    public Bitmap photo;

    public Event(String eid, String title, Bitmap photo){
        this.eid = eid;
        this.title = title;
        this.photo = photo;
    }

    public Event(String title, String description, Date date, String localizacao, Bitmap photo){
        this.title = title;
        this.description = description;
        this.date = date;
        this.localizacao = localizacao;
        this.photo = photo;
    }



    public String getTitle1() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public String getEid() {
        return eid;
    }

    public Bitmap getPhoto() {
        return photo;
    }
}
