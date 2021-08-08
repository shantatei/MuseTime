package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ImageView artist4;

    //making sharedPreferences a static variable
    public  static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        searchbutton = findViewById(R.id.searchbtn);
        playlistbutton = findViewById(R.id.playlistbtn);
        artist1 = findViewById(R.id.artist1);
        artist2 = findViewById(R.id.artist2);
        artist3 = findViewById(R.id.artist3);
        artist4 = findViewById(R.id.artist4);

        //First argument (Name of File)
        //Second argument (Setting the Mode)
        sharedPreferences = getSharedPreferences("playList",MODE_PRIVATE);
        String albums = sharedPreferences.getString("list","");
        if (!albums.equals(""))
        {
            //creating a token for an ArrayList
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
            Gson gson = new Gson();
            //converting String (albums) back to an an Arraylist
            PlaySongActivity.favList = gson.fromJson(albums,token.getType());
        }

        //picasso external library
        ImageView s1004 = findViewById(R.id.S1004);
        Picasso.with(this).load(LoginScreen.mainlist.get(3).getDrawable()).into(s1004);

        ImageView s1005 = findViewById(R.id.S1005);
        Picasso.with(this).load(LoginScreen.mainlist.get(4).getDrawable()).into(s1005);

        ImageView s1006 = findViewById(R.id.S1006);
        Picasso.with(this).load(LoginScreen.mainlist.get(5).getDrawable()).into(s1006);

        ImageView s1007 = findViewById(R.id.S1007);
        Picasso.with(this).load(LoginScreen.mainlist.get(6).getDrawable()).into(s1007);


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

        artist4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChunghaPlaylist();
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

    public void openChunghaPlaylist() {
        Intent intent = new Intent(this, chunghaPlaylist.class);
        startActivity(intent);
    }

    public void sendDataToActivity(int index) {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index", index);
        Gson gson = new Gson();
        String dataToBeSent = gson.toJson(LoginScreen.mainlist);
        intent.putExtra("songs",dataToBeSent);
        startActivity(intent);
    }

    public void handleSelection(View view) {

        int buttonId = view.getId();
        String resourceID = getResources().getResourceEntryName(buttonId);
        Log.d("poly", "The id of the imagebutton is " + resourceID);
        int currentArrayIndex =searchSongById(resourceID);
        Log.d("poly", "The array index is " + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }

    public  int searchSongById(String id){
        for (int i = 0; i <LoginScreen.mainlist.size(); i++) {

            Song tempSong = LoginScreen.mainlist.get(i);
            if(tempSong.getId().equals(id)){
                return i;
            }

        }
        return -1;
    }
}




