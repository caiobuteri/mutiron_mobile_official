package com.example.mutiron2;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    List<MyEvent> eventos = new ArrayList<>();

    public MainActivityViewModel() {
        MyEvent evento1 = new MyEvent();
        evento1.title = "Gato";
        evento1.description = "Um felino capaz de saltar muito alto.";
        evento1.photo = R.mipmap.gatinho;

        MyEvent evento2 = new MyEvent();
        evento2.title = "Guepardo";
        evento2.description = "Um felino veloz e muito perigoso.";
        evento2.photo = R.mipmap.gato;

        MyEvent evento3 = new MyEvent();
        evento3.title = "Karol Conka";
        evento3.description = "Integrante do BBB21 que conquistou a raiva de muitas pessoas, as vezes também é conhecida por cobra.";
        evento3.photo = R.mipmap.karolconka;

        MyEvent evento4 = new MyEvent();
        evento4.title = "Passarinho";
        evento4.description = "Uma ave muito bonita que gosta de voar bem alto.";
        evento4.photo = R.mipmap.passarinho;

        MyEvent evento5 = new MyEvent();
        evento5.title = "Tartaruga";
        evento5.description = "Um animal muito lento e paciente. Se está com pressa, não ande atrás dele.";
        evento5.photo = R.mipmap.tartaruga;

        eventos.add(evento1);
        eventos.add(evento2);
        eventos.add(evento3);
        eventos.add(evento4);
        eventos.add(evento5);
    }

    public List<MyEvent> getEventos(){ return eventos; }
}
