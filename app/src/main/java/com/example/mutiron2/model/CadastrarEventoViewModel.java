package com.example.mutiron2.model;

import androidx.lifecycle.ViewModel;

public class CadastrarEventoViewModel extends ViewModel {
    String currentPhotoPath = "";


    public String getCurrentPhotoPath() {
        return currentPhotoPath;
    }

    public void setCurrentPhotoPath(String currentPhotoPath) {
        this.currentPhotoPath = currentPhotoPath;
    }
}
