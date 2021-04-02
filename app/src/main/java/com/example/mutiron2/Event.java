package com.example.mutiron2;

import android.graphics.Bitmap;

import java.util.Date;

public class Event {
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

    public Event(String id_evento, String title, String descricao){
        this.eid = id_evento;
        this.title = title;
        this.description = descricao;
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
