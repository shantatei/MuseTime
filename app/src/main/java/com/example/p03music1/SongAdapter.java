package com.example.p03music1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class SongAdapter extends RecyclerView.Adapter<MyView> implements Filterable {

    //array list will go into the list 'songs'
    List<Song>songs;
    List<Song>songsFiltered;
    Context context;

    public SongAdapter(List<Song> songs) {
        this.songs = songs;
        this.songsFiltered = songs;
    }

    @NonNull
    @NotNull
    @Override
    public MyView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //handle for item_song
        context = parent.getContext();

        //codes to render item_song
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_song,parent,false);
        MyView viewHolder = new MyView(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyView holder, int position) {


        Song song = songsFiltered.get(position);
        TextView artist = holder.ArtistTxt;
        artist.setText(song.getArtiste());
        TextView title = holder.titleTxt;
        title.setText(song.getTitle());
        Picasso.with(context).load(song.getDrawable()).into(holder.image);
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaySongActivity.favList.remove(position);
                SharedPreferences sharedPreferences = context.getSharedPreferences("playList",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("list");
                editor.apply();
                notifyDataSetChanged();

            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // creating Gson object
                Gson gson = new Gson();
                // converting song array list to string
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
        return songsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();
                if (charString.isEmpty()){
                    songsFiltered = songs;
                }else {
                    List<Song> filteredList = new ArrayList<Song>();
                    for (int i = 0; i < songs.size(); i++) {
                        if (songs.get(i).getTitle().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(songs.get(i));
                        }
                    }
                    songsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = songsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            songsFiltered = (List<Song>) results.values;
            notifyDataSetChanged();
            }
        };
    }
}
