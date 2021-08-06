package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainPlaylist extends AppCompatActivity {
    private ImageButton likedbutton;
    private ImageButton searchbutton;
    private ImageButton homebutton;
    private ImageView artist1;
    private ImageView artist2;
    private ImageView artist3;
    private ImageView artist4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_playlist);
        searchbutton = findViewById(R.id.searchbtn);
        homebutton = findViewById(R.id.homebtn);
        artist1 = findViewById(R.id.artist1);
        artist2 = findViewById(R.id.artist2);
        artist3 = findViewById(R.id.artist3);
        artist4 = findViewById(R.id.artist4);

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });

        artist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEdSheeranPlaylist();
            }
        });

        artist2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openkeshiPlaylist();
            }
        });

        artist3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIUPlaylist();
            }
        });

        artist4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChunghaPlaylist();
            }
        });

    }

    public void gotoFavouriteActivity(View view) {
        Intent intent = new Intent(this, FavouritePlaylist.class);
        startActivity(intent);
    }


    public void openSearchActivity() {
        Intent intent = new Intent(this, MusicLibrary.class);
        startActivity(intent);
    }

    public void openHomeActivity() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }


    public void openEdSheeranPlaylist() {
        Intent intent = new Intent(this, EdSheeranPlaylist.class);
        startActivity(intent);
    }

    public void openkeshiPlaylist() {
        Intent intent = new Intent(this, keshiplaylist.class);
        startActivity(intent);
    }

    public void openIUPlaylist() {
        Intent intent = new Intent(this, iuPlaylist.class);
        startActivity(intent);
    }

    public void openChunghaPlaylist() {
        Intent intent = new Intent(this, chunghaPlaylist.class);
        startActivity(intent);
    }

}



