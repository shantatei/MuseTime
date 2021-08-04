package com.example.p03music1;

import android.content.Context;

public class Song {

    private String id;
    private String title;
    private String artiste;
    private String filelink;
    private double songlength;
    private String drawable;

    public Song(String id, String title, String artiste, String fileLink, double songlength, String drawable) {
        this.id = id;
        this.title = title;
        this.artiste = artiste;
        this.filelink = fileLink;
        this.songlength = songlength;
        this.drawable = drawable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() { return title;
    }

    public String getArtiste() {
        return artiste;
    }

    public String getFileLink() {
        return filelink;
    }

    public double getSonglength() {
        return songlength;
    }

    public String getDrawable() {
        return drawable;
    }

    public static int getImageIdFromDrawable(Context context, String imageName)
    {
        int imageID = context.getResources().getIdentifier(imageName,"drawable", context.getPackageName());

        return imageID;
    }

    /*public static int getImageIdFromDrawable(Context context, int imageName)
    {
        int imageID = context.getResources().getIdentifier(String.valueOf(imageName),"drawable", context.getPackageName());

        return imageID;
    }*/
}
