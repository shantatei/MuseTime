package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class chunghaPlaylist extends AppCompatActivity {


    public static ArrayList<Song> Chunghalist = new ArrayList<Song>();
    RecyclerView Chunghaview;
    private ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chungha_playlist);
        Chunghaview= findViewById(R.id.chunghaview);
        backbtn = findViewById(R.id.backBtn);
        String url = "https://musiclibrary-3ed8.restdb.io/rest/Chungha-Playlist?apikey=bdade1537241c45949573e53666a11b6bcf91";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Gson gson = new Gson();
                TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
                Chunghalist=gson.fromJson(response,token.getType());
                ArtistAdapter adapter = new ArtistAdapter(Chunghalist);
                Chunghaview.setAdapter(adapter);
                Chunghaview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("temasek",error.toString());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //android built in method to go to previous activity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}