package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton searchbutton;
    private ImageButton playlistbutton;

    SongCollection songCollection = new SongCollection();
    static ArrayList<Song> favList = new ArrayList<Song>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Picasso External Library
        ImageView s1001 = findViewById(R.id.S1001);
        Picasso.with(this).load(songCollection.songs[0].getDrawable()).into(s1001);
        ImageView s1002 = findViewById(R.id.S1002);
        Picasso.with(this).load(songCollection.songs[1].getDrawable()).into(s1002);
        ImageView s1003 = findViewById(R.id.S1003);
        Picasso.with(this).load(songCollection.songs[2].getDrawable()).into(s1003);

        searchbutton = findViewById(R.id.searchbtn);
        playlistbutton = findViewById(R.id.playlistbtn);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });
    }

    public void openSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void openPlaylistActivity() {
        Intent intent = new Intent(this, PlaylistActivity.class);
        startActivity(intent);
    }

    public void sendDataToActivity(int index) {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    public void handleSelection(View view) {

        int buttonId = view.getId();
        String resourceID = getResources().getResourceEntryName(buttonId);
        Log.d("poly", "The id of the imagebutton is " + resourceID);
        int currentArrayIndex = songCollection.searchSongById(resourceID);
        Log.d("poly", "The array index is " + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);

    }
}

/*
    public void addToFavourite(View view) {
        String resourceID = getResources().getResourceEntryName(view.getId());
        //String songID = view.getContentDescription().toString();

        Song song = songCollection.getCurrentSong(songCollection.searchSongById(resourceID.substring(0,5)));
       favList.add(song);

        Gson gson = new Gson();
        String json = gson.toJson(favList);
        Log.d("gson", json);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("list",json);
        editor.apply();

        //Toast.makeText(this, "button is clicked", Toast.LENGTH_SHORT).show();
    }

    public void gotoFavouriteActivity(View view) {
        Intent intent = new Intent(this,PlaylistActivity.class);
        startActivity(intent);
    }
}*/
