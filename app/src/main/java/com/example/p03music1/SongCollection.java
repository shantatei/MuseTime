package com.example.p03music1;

public class SongCollection {

    public Song songs[]= new Song[3];
    public SongCollection() {

        Song theWayYouLookTonight = new Song("S1001", "The Way You Look Tonight", "Michael Buble",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                4.66, R.drawable.michael_buble_collection);

        Song billyJean = new Song("S1002", "Billy Jean", "Michael Jackson",
                "https://p.scdn.co/mp3-preview/f504e6b8e037771318656394f532dede4f9bcaea?cid=2afe87a64b0042dabf51f37318616965",
                4.9, R.drawable.billie_jean);

        Song one = new Song("S1003", "one", "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/daa8679253ba20620db6e1db9c88edfcf1f4069f?cid=2afe87a64b0042dabf51f37318616965",
                4.21, R.drawable.photograph);

        songs[0]= theWayYouLookTonight;
        songs[1]= billyJean;
        songs[2]= one;
    }

    public int searchSongById(String id){
        for (int i = 0; i <songs.length; i++) {

            Song tempSong = songs[i];
            if(tempSong.getId().equals(id)){
                return i;
            }

        }
        return -1;
    }

    public Song getCurrentSong(int currentSongId){

        return songs[currentSongId];
    }

    public int getNextSong(int currentSongIndex){
        if (currentSongIndex>= songs.length-1){
            return currentSongIndex;
        }
        else {
            return currentSongIndex+1;
        }
    }

    public int getPrevSong(int currentSongIndex){
        if (currentSongIndex<=0){
            return currentSongIndex;
        }
        else {
            return currentSongIndex-1;
        }
    }
}


