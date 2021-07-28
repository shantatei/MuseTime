package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PlaylistActivity extends AppCompatActivity {
RecyclerView favList;
SongAdapter songAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        favList = findViewById(R.id.recycleView);

        songAdapter = new SongAdapter(MainActivity.favList);
        favList.setAdapter(songAdapter);
        favList.setLayoutManager(new LinearLayoutManager(this
        ));

    }

    public void removeAll(View view) {
        MainActivity.favList.clear();
        songAdapter.notifyDataSetChanged();

    }
}