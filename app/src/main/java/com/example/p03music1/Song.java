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


}
