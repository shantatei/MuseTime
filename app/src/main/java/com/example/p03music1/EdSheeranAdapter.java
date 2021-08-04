package com.example.p03music1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EdSheeranAdapter extends RecyclerView.Adapter<MyView>{
    List<Song> songs;
    Context context;
    SongCollection songCollection = new SongCollection();
    public EdSheeranAdapter(List<Song> songs) {
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
        //picasso external library
        Picasso.with(context).load(song.getDrawable()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = songCollection.searchSongById(song.getId()); //creating a variable called pos as 'position' will give the arraylist index
                // creating Gson object
                Gson gson = new Gson();
                // converting song array list in song collection to string
                String sendingsonglist = gson.toJson(songs);
                Intent intent = new Intent(context,PlaySongActivity.class);
                intent.putExtra("index",position);
                intent.putExtra("songs",sendingsonglist);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return songs.size();
    }

}

