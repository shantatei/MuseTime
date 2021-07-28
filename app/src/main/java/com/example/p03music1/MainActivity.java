package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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
        searchbutton = findViewById(R.id.searchbtn);
        playlistbutton = findViewById(R.id.playlistbtn);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });
    }
        public void openSearchActivity(){
        Intent intent = new Intent(this,SearchActivity.class);
       startActivity(intent);
        }
        public void openPlaylistActivity(){
        Intent intent = new Intent(this,PlaylistActivity.class);
        startActivity(intent);
    }

    public void sendDataToActivity(int index){
        Intent intent = new Intent(this,PlaySongActivity.class);
        intent.putExtra("index",index);
        startActivity(intent);
    }

    public void handleSelection(View view) {

        int buttonId=view.getId();
        String resourceID = getResources().getResourceEntryName(buttonId);
        Log.d("poly", "The id of the imagebutton is " +resourceID);
        int currentArrayIndex = songCollection.searchSongById(resourceID);
        Log.d("poly","The array index is " + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);

    }

    public void addToFavourite(View view) {
        String resourceID = getResources().getResourceEntryName(view.getId());
        //String songID = view.getContentDescription().toString();
        Song song = songCollection.getCurrentSong(songCollection.searchSongById(resourceID.substring(0,5)));
        favList.add(song);
        //Toast.makeText(this, "button is clicked", Toast.LENGTH_SHORT).show();
    }

    public void gotoFavouriteActivity(View view) {
        Intent intent = new Intent(this,PlaylistActivity.class);
        startActivity(intent);
    }
}