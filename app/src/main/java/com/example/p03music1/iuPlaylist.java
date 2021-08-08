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

public class iuPlaylist extends AppCompatActivity {
    public static ArrayList<Song> IUlist = new ArrayList<Song>();
    RecyclerView IUview;
    private ImageButton backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iu_playlist);
        IUview= findViewById(R.id.IUview);
        backBtn= findViewById(R.id.backBtn);
        String url = "https://musiclibrary-3ed8.restdb.io/rest/Iu-Playlist?apikey=bdade1537241c45949573e53666a11b6bcf91";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Gson gson = new Gson();
                //creating a token for an ArrayList
                TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
                //Converting the String (response) to an Arraylist
                IUlist=gson.fromJson(response,token.getType());
                //IUlist is powering the adapter
                ArtistAdapter adapter = new ArtistAdapter(IUlist);
                //IUview(recycler view) is using the adapter
                IUview.setAdapter(adapter);
                IUview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("temasek",error.toString());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}