package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity {
    private ImageButton searchbutton;
    private ImageButton playlistbutton;
    private ImageView artist1;
    private ImageView artist2;
    private ImageView artist3;
    SongCollection songCollection = new SongCollection();
    ArrayList<Song> mainlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getapi();
        searchbutton = findViewById(R.id.searchbtn);
        playlistbutton = findViewById(R.id.playlistbtn);
        artist1 = findViewById(R.id.artist1);
        artist2 = findViewById(R.id.artist2);
        artist3 = findViewById(R.id.artist3);

        //picasso external library
        ImageView s1004 = findViewById(R.id.S1004);
        Picasso.with(this).load(songCollection.songs[3].getDrawable()).into(s1004);
        //Picasso.with(this).load(mainlist.get(3).getDrawable()).into(s1004);
        ImageView s1005 = findViewById(R.id.S1005);
        Picasso.with(this).load(songCollection.songs[4].getDrawable()).into(s1005);
        //Picasso.with(this).load(mainlist.get(4).getDrawable()).into(s1004);
        ImageView s1006 = findViewById(R.id.S1006);
        Picasso.with(this).load(songCollection.songs[5].getDrawable()).into(s1006);
        //Picasso.with(this).load(mainlist.get(5).getDrawable()).into(s1004);
        ImageView s1007 = findViewById(R.id.S1007);
        Picasso.with(this).load(songCollection.songs[6].getDrawable()).into(s1007);
        //Picasso.with(this).load(mainlist.get(6).getDrawable()).into(s1004);


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
    }

    public void openSearchActivity() {
        Intent intent = new Intent(this, MusicLibrary.class);
        startActivity(intent);
    }

    public void openPlaylistActivity() {
        Intent intent = new Intent(this, MainPlaylist.class);
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

    public void sendDataToActivity(int index) {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        Gson gson = new Gson();
        String dataToBeSent = gson.toJson(mainlist);
        intent.putExtra("songs",dataToBeSent);
        startActivity(intent);
    }

    public void handleSelection(View view) {

        int buttonId = view.getId();
        String resourceID = getResources().getResourceEntryName(buttonId);
        Log.d("poly", "The id of the imagebutton is " + resourceID);
        int currentArrayIndex =songCollection.searchSongById(resourceID);
        Log.d("poly", "The array index is " + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }

    public void getapi(){

        String url = "https://musiclibrary-3ed8.restdb.io/rest/song?apikey=bdade1537241c45949573e53666a11b6bcf91";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>() {};
                mainlist = gson.fromJson(response, token.getType());
                //check if data can be obtained
                for (int i = 0; i < mainlist.size(); i++) {
                    Log.d("poly", mainlist.get(i).getTitle());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}




