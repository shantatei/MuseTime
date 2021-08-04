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

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EdSheeranAdapter extends RecyclerView.Adapter<MyView>{ //implements Filterable {
    List<Song> songs;
    List<Song>songsFiltered;
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
        //    Integer imageId = Song.getImageIdFromDrawable(context, song.getDrawable()); old codes
        //     holder.image.setImageResource(imageId);
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

   /* @Override
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
    }*/
}

