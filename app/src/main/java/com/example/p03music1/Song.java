package com.example.p03music1;

public class Song {

    private String id;
    private String title;
    private String artiste;
    private String fileLink;
    private double songlength;
    private int drawable;

    public Song(String id, String title, String artiste, String fileLink, double songlength, int drawable) {
        this.id = id;
        this.title = title;
        this.artiste = artiste;
        this.fileLink = fileLink;
        this.songlength = songlength;
        this.drawable = drawable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtiste() {
        return artiste;
    }

    public String getFileLink() {
        return fileLink;
    }

    public double getSonglength() {
        return songlength;
    }

    public int getDrawable() {
        return drawable;
    }
}
