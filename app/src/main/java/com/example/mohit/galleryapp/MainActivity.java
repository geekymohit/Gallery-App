package com.example.mohit.galleryapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String [] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listview);

        final ArrayList<File> mySongs = findSongs(Environment.getExternalStorageDirectory());
        items = new String[mySongs.size()];
        for (int i=0 ; i<mySongs.size() ; i++)
        {

            items[i]=mySongs.get(i).getName().toString().replace(".png","").replace(".jpg","");

        }
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.photos,R.id.textview, items);
        listView.setAdapter(arrayadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("position",position).putExtra("songs",mySongs);
                startActivity(intent);

            }
        });
    }

    public ArrayList<File> findSongs (File root)
    {
        checkpermission();;

        ArrayList<File> arrayList = new ArrayList<File>();
        File[] files = root.listFiles();
        for (File singleFile : files)
        {
            if (singleFile.isDirectory() && ! singleFile.isHidden())
            {
                arrayList.addAll(findSongs(singleFile));
            }
            else
            {
                if (singleFile.getName().endsWith(".jpg") || singleFile.getName().endsWith(".png"))
                {
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

    private void checkpermission()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 123);

            }
        }
    }
}

