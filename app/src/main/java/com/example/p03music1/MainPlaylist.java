package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainPlaylist extends AppCompatActivity {
    private ImageButton likedbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_playlist);



    }

    public void gotoFavouriteActivity(View view) {
        Intent intent = new Intent(this,PlaylistActivity.class);
        startActivity(intent);

        }
    }
