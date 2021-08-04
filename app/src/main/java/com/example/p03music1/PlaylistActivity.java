package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;

public class PlaylistActivity extends AppCompatActivity {
RecyclerView favList;
SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        for (int i = 0; i < PlaySongActivity.favList.size(); i++) {
            Log.d("temasek",PlaySongActivity.favList.get(i).getTitle());
        }
        favList = findViewById(R.id.recycleView);
        songAdapter = new SongAdapter(PlaySongActivity.favList);
        favList.setAdapter(songAdapter);
        favList.setLayoutManager(new LinearLayoutManager(this
        ));
        SearchView searchView =findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                songAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    public void removeAll(View view) {
        PlaySongActivity.favList.clear();
        songAdapter.notifyDataSetChanged();

    }
}