package com.example.mutiron2.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mutiron2.Event;
import com.example.mutiron2.adapter.MyAdapter;
import com.example.mutiron2.R;
import com.example.mutiron2.model.ViewFeedViewModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewFeedActivity extends AppCompatActivity {

    static int RESULT_REQUEST_PERMISSION = 2;
    static int ADD_EVENT_ACTIVITY_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feed);

        List<String> permissions = new ArrayList<>();  // array de permissoes
        permissions.add(Manifest.permission.CAMERA);

        RecyclerView rvFeed = findViewById(R.id.rvFeed);
        rvFeed.setHasFixedSize(true); // define que tem tamanho fixo

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); //define como linear a forma de organização
        rvFeed.setLayoutManager(layoutManager);

        ViewFeedViewModel viewFeedViewModel = new ViewModelProvider(this).get(ViewFeedViewModel.class);
        LiveData<List<Event>> events = viewFeedViewModel.getEvents();
        events.observe(this, new Observer<List<Event>>() { //novo observador de lista de produtos
            @Override
            public void onChanged(List<Event> events) { //quando uma nova lista vier
                MyAdapter myAdapter = new MyAdapter(ViewFeedActivity.this, events); //vai ser avisada
                rvFeed.setAdapter(myAdapter); //atualiza a interface com a nova lista vinda do servidor
            }
        });


        Button btnAddEventFeed = findViewById(R.id.btnAddEventFeed);
        btnAddEventFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewFeedActivity.this, CadastrarEventoActivity.class);
                startActivityForResult(i, ADD_EVENT_ACTIVITY_RESULT);
            }
        });

        ImageView imvPhotoFeed = findViewById(R.id.imvPhotoFeed);
        imvPhotoFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewFeedActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

    }


   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_EVENT_ACTIVITY_RESULT){
            if(resultCode == Activity.RESULT_OK){
                ViewFeedViewModel viewFeedViewModel = new ViewModelProvider(this).get(ViewFeedViewModel.class);
                viewFeedViewModel.refresh();
            }
        }
    }

    private void checkForPermissions(List<String> permissions){  // função para verificar as permissoes
        List<String> permissionsNotGranted = new ArrayList<>(); //cria um nova lista de string

        for (String permission : permissions){
            if (!hasPermission(permission)){ //verifica se tem permissão ou não
                permissionsNotGranted.add(permission);  // se não tiver permissao, adicionar na lista
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (permissionsNotGranted.size() > 0){
                requestPermissions(permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]), RESULT_REQUEST_PERMISSION);
            }
        }
    }

    private boolean hasPermission(String permission){  // função para verificar se tem permissao atraves de um boolean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return ActivityCompat.checkSelfPermission(ViewFeedActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
            //retorna True
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {  //
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        final List<String> permissionsRejected = new ArrayList<>();
        if (requestCode == RESULT_REQUEST_PERMISSION) {
            for (String permission : permissions){
                if (!hasPermission(permission)){
                    permissionsRejected.add(permission);
                }
            }
        }
        if (permissionsRejected.size() > 0){//faz o tratamento caso alguma rejeição
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))){
                    new AlertDialog.Builder(ViewFeedActivity.this).
                            setMessage("Para usar essa app é preciso conceder essas permissões").
                            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), RESULT_REQUEST_PERMISSION);
                                }
                            }).create().show();
                }
            }
        }

    }
}