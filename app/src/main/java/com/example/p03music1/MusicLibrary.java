package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MusicLibrary extends AppCompatActivity {


    public static ArrayList<Song> mainlist = new ArrayList<Song>();
    RecyclerView mainSongView;
    private ImageButton playlistbutton;
    private ImageButton homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_library);

        mainSongView = findViewById(R.id.mainSongView);
        playlistbutton = findViewById(R.id.playlistbtn);
        homebutton = findViewById(R.id.homebtn);



        String url = "https://musiclibrary-3ed8.restdb.io/rest/song?apikey=bdade1537241c45949573e53666a11b6bcf91";
        //Request.Method.GET means getting data from the web basically
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                // the code below basically converts the string to the various instances in the array
                TypeToken<ArrayList<Song>>token = new TypeToken<ArrayList<Song>>(){};
                //response is 'String' , token.getType converts it to proper song instances
                mainlist = gson.fromJson(response,token.getType());
                //mainlist is powering the adapter
                SongMainAdapter adapter = new SongMainAdapter(mainlist);
                //mainsongview (recycler view) is using the adapter and the adapter is powered by the mainlist
                mainSongView.setAdapter(adapter);
                mainSongView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                SearchView searchView = findViewById(R.id.mainSearchView);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("temasek",error.toString());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);


        playlistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlaylistActivity();
            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });



    }


    public void openPlaylistActivity() {
        Intent intent = new Intent(this, MainPlaylist.class);
        startActivity(intent);
    }


    public void openHomeActivity() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}