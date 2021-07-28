package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SongCollection songCollection = new SongCollection();
    ArrayList<String> songs = new ArrayList<>();
    ListView musiclist;
    SearchView search;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        songs.add(songCollection.songs[0].getTitle());
        songs.add(songCollection.songs[1].getTitle());
        songs.add(songCollection.songs[2].getTitle());

        search = findViewById(R.id.search);
        search.setOnQueryTextListener(this);
        musiclist =findViewById(R.id.musiclist);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1,songs);
        musiclist.setAdapter(adapter);

        musiclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView text = (TextView) view;
                String title = text.getText().toString();
                int pos = songCollection.searchSongByTitle(title);
                Intent intent = new Intent(parent.getContext(),PlaySongActivity.class);
                intent.putExtra("index",pos);
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
     // Log.d("poly",newText);
        adapter.getFilter().filter(newText);
        return false;
    }
}