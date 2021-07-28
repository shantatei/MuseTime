package com.example.p03music1;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class MyView extends RecyclerView.ViewHolder {

    public TextView titleTxt;
    public TextView ArtistTxt;
    public ImageView image;
    public ImageButton removeBtn;

    public MyView(@NonNull @NotNull View itemView) {
        super(itemView);

        titleTxt = itemView.findViewById(R.id.titleTxt);
        ArtistTxt = itemView.findViewById(R.id.ArtistTxt);
        image = itemView.findViewById(R.id.image);
        removeBtn = itemView.findViewById(R.id.removeBtn);
    }
}
