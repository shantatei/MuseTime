package com.example.p03music1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<MyView> {

    public SongAdapter(List<Song> songs) {
        this.songs = songs;
    }
    List<Song>songs;
    Context context;
    @NonNull
    @NotNull
    @Override
    public MyView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songView = inflater.inflate(R.layout.item_song,parent,false);
        MyView viewHolder = new MyView(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyView holder, int position) {
        Song song = songs.get(position);
        TextView artist = holder.ArtistTxt;
        artist.setText(song.getArtiste());
        TextView title = holder.titleTxt;
        title.setText(song.getTitle());
        Integer imageId = Song.getImageIdFromDrawable(context, song.getDrawable());
        holder.image.setImageResource(imageId);
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.favList.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
