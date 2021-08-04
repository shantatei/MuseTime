package com.example.p03music1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class keshiAdapter extends RecyclerView.Adapter<MyView>{
    List<Song> songs;
    Context context;
    SongCollection songCollection = new SongCollection();
    public keshiAdapter(List<Song> songs) {
        this.songs = songs;
    }

    @NonNull
    @NotNull
    @Override
    public MyView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_song_main,parent,false);
        MyView viewHolder = new MyView(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyView holder,  int position) {


        Song song = songs.get(position);
        TextView artist = holder.ArtistTxt;
        artist.setText(song.getArtiste());
        TextView title = holder.titleTxt;
        title.setText(song.getTitle());
        Picasso.with(context).load(song.getDrawable()).into(holder.image); //picasso external library
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = songCollection.searchSongById(song.getId()); //creating a variable called pos as 'position' will give the arraylist index
                Intent intent = new Intent(context,PlaySongActivity.class);
                intent.putExtra("index",pos);
           /*     intent.putExtra("id",song.getId());
                intent.putExtra("title",song.getTitle());
                intent.putExtra("artiste",song.getArtiste());
                intent.putExtra("filelink",song.getFileLink());
                intent.putExtra("drawable",song.getDrawable());*/
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return songs.size();
    }

}
