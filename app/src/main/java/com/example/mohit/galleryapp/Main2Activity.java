package com.example.mohit.galleryapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        ArrayList<File> myPhotos = (ArrayList)intent.getParcelableArrayListExtra("songs");
        ImageView imageView=(ImageView)findViewById(R.id.imageview);
        Uri uri = Uri.parse(myPhotos.get(position).toString());
        imageView.setImageURI(uri);
    }
}
