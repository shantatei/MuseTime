package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton searchbutton;
    private ImageButton playlistbutton;

    SongCollection songCollection = new SongCollection();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchbutton = findViewById(R.id.searchbtn);
        playlistbutton = findViewById(R.id.playlistbtn);
        playlistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlaylistActivity();
            }
        });
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
}