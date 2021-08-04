package com.example.p03music1;

public class SongCollection {

    public Song songs[]= new Song[15];
    public SongCollection() {

        Song theWayYouLookTonight = new Song("S1001", "The Way You Look Tonight", "Michael Buble",
                "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                4.66, "https://i.scdn.co/image/ab67616d0000b273d10f1546f772a0156bc4b55e");

        Song billyJean = new Song("S1002", "Billy Jean", "Michael Jackson",
                "https://p.scdn.co/mp3-preview/f504e6b8e037771318656394f532dede4f9bcaea?cid=2afe87a64b0042dabf51f37318616965",
                4.9, "https://i.scdn.co/image/ab67616d0000b2734121faee8df82c526cbab2be");

        Song one = new Song("S1003", "one", "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/daa8679253ba20620db6e1db9c88edfcf1f4069f?cid=2afe87a64b0042dabf51f37318616965",
                4.21,"https://i.scdn.co/image/ab67616d0000b273407981084d79d283e24d428e");

        Song Blueming = new Song("S1004", "Blueming","IU","https://p.scdn.co/mp3-preview/51b5694fe01e2f3d10c76ee0895037fec89e1093?cid=2afe87a64b0042dabf51f37318616965",
                3.62,"https://i.scdn.co/image/ab67616d0000b273b658276cd9884ef6fae69033");

        Song shapeofyou = new Song ("S1005", "Shape of You","Ed Sheeran","https://p.scdn.co/mp3-preview/84462d8e1e4d0f9e5ccd06f0da390f65843774a2?cid=2afe87a64b0042dabf51f37318616965",
                3.9,"https://upload.wikimedia.org/wikipedia/en/b/b4/Shape_Of_You_%28Official_Single_Cover%29_by_Ed_Sheeran.png");

        Song psycho = new Song("S1006","Psycho","Red Velvet","https://p.scdn.co/mp3-preview/ef27836e11b679c54eeb74bfc65824872c0b4f00?cid=2afe87a64b0042dabf51f37318616965",
                3.51,"https://i.scdn.co/image/ab67616d0000b273df5022bdf1ac4bf52135c4be");

        Song butter = new Song("S1007","Butter","BTS","https://p.scdn.co/mp3-preview/edf24f427483d886b640c5ed9944f9291e0976fc?cid=2afe87a64b0042dabf51f37318616965",
                2.74,"https://i.scdn.co/image/ab67616d0000b2736bb2b8231817c8d205d07fb2");

        Song southoftheborder = new Song("S1008","South of the border","Ed Sheeran (ft Camilla Cabello & Cardi B)","https://p.scdn.co/mp3-preview/7b43dd0c94b0af0c0401381a683d2f4833180ba3?cid=2afe87a64b0042dabf51f37318616965",
                3.41,"https://i.scdn.co/image/ab67616d0000b2737ed2a6d678a53a5959b2894f");

        Song photograph = new Song("S1009","photograph","Ed Sheeran","https://p.scdn.co/mp3-preview/097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=2afe87a64b0042dabf51f37318616965\n",
                4.25,"https://i.scdn.co/image/ab67616d0000b27313b3e37318a0c247b550bccd");

        Song castleonthehill = new Song("S10010","Castle on the Hill","Ed Sheeran","https://p.scdn.co/mp3-preview/beb4ed48cca5d2a792e877c7efe92d54046eac67?cid=2afe87a64b0042dabf51f37318616965",
                4.35,"https://i.scdn.co/image/ab67616d0000b273ba5db46f4b838ef6027e6f96");

        Song toosoon = new Song("S10011","2 soon","keshi","https://p.scdn.co/mp3-preview/d38bfe1886682a0627aea812b20ea5ccf293e759?cid=2afe87a64b0042dabf51f37318616965",
                3.44,"https://i.scdn.co/image/ab67616d0000b273c3c387d65d710a1909ad410d");

        Song gooddays = new Song("S10012","good days","keshi","https://p.scdn.co/mp3-preview/ac06b206135e537cb044476f49243a3e2f7ae343?cid=2afe87a64b0042dabf51f37318616965",
                3.81,"https://i.scdn.co/image/ab67616d0000b273555074039baa0bce2a82a076");

        Song thereaper = new Song("S10013","the reaper","keshi","https://p.scdn.co/mp3-preview/6f6cd4ce2947ab2931c592d2c52cc307719bf926?cid=2afe87a64b0042dabf51f37318616965",
                3.32,"https://i.scdn.co/image/ab67616d0000b27394237be74edae41560152bce");

        Song magnolia = new Song("S10014","	magnolia","keshi","https://p.scdn.co/mp3-preview/353ca39fb815919dfd5f19504c5e9264fc799961?cid=2afe87a64b0042dabf51f37318616965",
                2.33,"https://i.scdn.co/image/ab67616d0000b2734ceb152f7b9e58f38e8600f9");

        Song likeineedu = new Song("S10015","like I need u", 	"keshi","https://p.scdn.co/mp3-preview/93dc61f66a0502572dbfee6eab1aae02138a69b9?cid=2afe87a64b0042dabf51f37318616965",
                3.04,	"https://i.scdn.co/image/ab67616d0000b273da53f97b4b0f1e14d854f777");



        songs[0]= theWayYouLookTonight;
        songs[1]= billyJean;
        songs[2]= one;
        songs[3]= Blueming;
        songs[4]= shapeofyou;
        songs[5]= psycho;
        songs[6]= butter;
        songs[7]=southoftheborder;
        songs[8]=photograph;
        songs[9]=castleonthehill;
        songs[10]=toosoon;
        songs[11]=gooddays;
        songs[12]=thereaper;
        songs[13]=magnolia;
        songs[14]=likeineedu;

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


    public int searchSongByTitle(String title){
        for (int i = 0; i <songs.length; i++) {

            Song tempSong = songs[i];
            if(tempSong.getTitle().equals(title)){
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


