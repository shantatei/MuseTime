package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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

public class EdSheeranPlaylist extends AppCompatActivity {

   public static ArrayList<Song> edsheeranlist = new ArrayList<Song>();
    RecyclerView edsheeranview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_sheeran_playlist);
        edsheeranview = findViewById(R.id.edsheeranview);
        String url = "https://musiclibrary-3ed8.restdb.io/rest/Ed-Sheeran-Playlist?apikey=bdade1537241c45949573e53666a11b6bcf91";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Gson gson = new Gson();
                TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
                edsheeranlist=gson.fromJson(response,token.getType());
                EdSheeranAdapter adapter = new EdSheeranAdapter(edsheeranlist);
                edsheeranview.setAdapter(adapter);
                edsheeranview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                /*SearchView searchView = findViewById(R.id.edsheeranview);
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
                });*/


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("temasek",error.toString());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}

