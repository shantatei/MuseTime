package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity {
    private ImageButton searchbutton;
    private ImageButton playlistbutton;
    private ImageView artist1;
    private ImageView artist2;
    SongCollection songCollection = new SongCollection();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        searchbutton = findViewById(R.id.searchbtn);
        playlistbutton = findViewById(R.id.playlistbtn);
        artist1= findViewById(R.id.artist1);
        artist2= findViewById(R.id.artist2);

        //picasso external library
        ImageView s1004 = findViewById(R.id.S1004);
        Picasso.with(this).load(songCollection.songs[3].getDrawable()).into(s1004);
        ImageView s1005 = findViewById(R.id.S1005);
        Picasso.with(this).load(songCollection.songs[4].getDrawable()).into(s1005);
        ImageView s1006 = findViewById(R.id.S1006);
        Picasso.with(this).load(songCollection.songs[5].getDrawable()).into(s1006);
        ImageView s1007 = findViewById(R.id.S1007);
        Picasso.with(this).load(songCollection.songs[6].getDrawable()).into(s1007);


        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });
        playlistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlaylistActivity();
            }
        });

        artist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openEdSheeranPlaylist();
            }
        });

        artist2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {openkeshiPlaylist();
        }
    });
}

    public void openSearchActivity() {
        Intent intent = new Intent(this, MusicLibrary.class);
        startActivity(intent);
    }

    public void openPlaylistActivity() {
        Intent intent = new Intent(this, PlaylistActivity.class);
        startActivity(intent);
    }

    public void openEdSheeranPlaylist(){
        Intent intent = new Intent(this,EdSheeranPlaylist.class);
        startActivity(intent);
    }

    public void openkeshiPlaylist(){
        Intent intent = new Intent(this,keshiplaylist.class);
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

