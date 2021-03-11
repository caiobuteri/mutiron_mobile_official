package com.example.mutiron2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.mutiron2.MyAdapter;
import com.example.mutiron2.MyEvent;
import com.example.mutiron2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ViewFeedActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;

    MyAdapter myAdapter;

    List<MyEvent> itens = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        myAdapter = new MyAdapter(this, itens);

        RecyclerView rvItens = findViewById(R.id.rvItems);
        rvItens.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        rvItens.setAdapter(myAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration); */
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ITEM_REQUEST){
            if (resultCode == Activity.RESULT_OK){
                Uri selectedPhotoLocation = data.getData();
                String title = data.getStringExtra("title");
                String description = data.getStringExtra("description");

                MyItem newItem = new MyItem();
                newItem.photo = selectedPhotoLocation;
                newItem.title = title;
                newItem.description = description;

                itens.add(newItem);

                myAdapter.notifyItemInserted(itens.size() - 1);
            }
        }
    }*/


}