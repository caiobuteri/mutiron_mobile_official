package com.example.mutiron2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import java.util.Date;

public class Event extends AppCompatActivity {
    public String eid;
    public String title;
    public String description;
    public Date data;
    public String localizacao;
    public Bitmap photo;


    public String getTitle1() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getData() {
        return data;
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
