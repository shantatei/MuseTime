package com.example.p03music1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PlaySongActivity extends AppCompatActivity {
    //Initialize variables here:

    private String title = "";
    private String artiste = "";
    private String filelink = "";
    private String drawable;
    private int currentIndex = -1;
    // making a new variable called songlist
    //songlist will be the arraylist that
    //is powering the 'display song based on index'
    ArrayList<Song> songlist = new ArrayList<Song>();
    //static means another class can simply access the variable through the class
    // thus no need to create an instant
    //creating a an arraylist called favlist to add songs into this array list
    static ArrayList<Song> favList = new ArrayList<Song>();

    //creating historylist
    ArrayList<Song> beforeCurrent = new ArrayList<Song>();

    //creating queuelist
    ArrayList<Song> afterCurrent = new ArrayList<Song>();

    //making song variable
    Song song;

    private MediaPlayer player = new MediaPlayer();
    private ImageButton btnPlayPause = null;

    SharedPreferences sharedPreferences;

 //  static List<Song> shuffleList = new ArrayList<>(songlist);


    //seekbar variables
    TextView playerPosition,playerDuration;
    SeekBar seekbar;
    Handler handler = new Handler();

    //btn variables
    ImageButton repeatBtn;
    ImageButton shuffleBtn;
    ImageButton likedBtn;
    ImageButton backBtn;

    //seekbar Volume Control
    SeekBar seekBarVolume;
    AudioManager audioManager;


    Boolean repeatFlag = false;
    Boolean shuffleFlag = false;
    Boolean likedFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        sharedPreferences = getSharedPreferences("playList",MODE_PRIVATE);
        String albums = sharedPreferences.getString("list","");
        if (!albums.equals(""))
        {
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
            Gson gson = new Gson();
            favList = gson.fromJson(albums,token.getType());
        }

        btnPlayPause = findViewById(R.id.btnPlayPause);
        playerPosition=findViewById(R.id.player_position);
        playerDuration=findViewById(R.id.player_duration);
        seekbar = findViewById(R.id.seekBar);
        seekBarVolume =findViewById(R.id.seekBarVolume);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        //get Max Volume
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        //get Current Volume
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBarVolume.setMax(maxVolume);

        seekBarVolume.setProgress(currentVolume);

        //seekbar to control vol
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //seekbar to control song
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               //Check condition
                if(fromUser){
                    //When drag the seekbar
                    //Set progress on seekbar
                    player.seekTo(progress);
                }
                //Set current position on text view
                playerPosition.setText(convertFormat(player.getCurrentPosition()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (player != null && player.isPlaying()) {
                    player.seekTo(seekBar.getProgress());
                }
            }
        });

        repeatBtn = findViewById(R.id.repeatBtn);
        shuffleBtn = findViewById(R.id.shuffleBtn);
        likedBtn = findViewById(R.id.likedBtn);
        backBtn = findViewById(R.id.songBackBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //getting data from other activities
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("index");
        //Creating GSON Object
        Gson gson = new Gson();
        //Converting String Data into instances of the arraylist
        TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>() {
        };
        songlist = gson.fromJson(songData.getString("songs"), token.getType());
        Log.d("poly", "The index of the array is " + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(filelink);
        //Get Duration of mediaplayer
        int duration = player.getDuration();
        // Using method to Convert millisecond to minute and second
        String sDuration = convertFormat(duration);
        //Set duration on text view
        playerDuration.setText(sDuration);
    }


    //Converting millisecond to minute and second method
    //returning string of 'Time'
    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration){
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration)-TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    Runnable p_bar = new Runnable() {
        @Override
        public void run() {
            if (player != null && player.isPlaying()) {
                seekbar.setProgress(player.getCurrentPosition());
            }
            handler.postDelayed(this, 1000);
        }


    };



    //playSong Method

    public void playSong(String songURL) {

        try {
            player.reset();
            player.setDataSource(songURL);
            player.prepare();
            player.start();
            gracefullyStopWhenMusicEnds();
            btnPlayPause.setBackgroundResource(R.drawable.pause_icon_white);
            // btnPlayPause.setText("PAUSE");
            setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    //Displaying Song Base on Index Method

    private void displaySongBasedOnIndex(int selectedIndex) {

        song = songlist.get(selectedIndex);
        title = song.getTitle();
        artiste = song.getArtiste();
        filelink = song.getFileLink();
        drawable = song.getDrawable();

        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);

        TextView txtArtist = findViewById(R.id.txtArtist);
        txtArtist.setText(artiste);

        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        Picasso.with(this).load(drawable).into(iCoverArt);


        //adding beforecurrentsong to beforeCurrent list
        for (int i = 0;i <selectedIndex ; i++) {
            beforeCurrent.add(songlist.get(i));
        }

        //adding aftercurrentsong to afterCurrent list
        for (int i = selectedIndex +1 ;i<songlist.size();i++) {
            afterCurrent.add(songlist.get(i));
        }

        Log.d("poly", "The title of the song is " + title);
        Log.d("poly", "The artist of the song is " + artiste);
        Log.d("poly", "The url of  the song is " + filelink);


    }

   //method for song to stop once music ends

    private void gracefullyStopWhenMusicEnds() {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (repeatFlag) {
                    playOrPauseMusic(null);
                } else {
                    btnPlayPause.setBackgroundResource(R.drawable.play_icon_white);
                    //    btnPlayPause.setText("PLAY");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (player != null) {
            handler.removeCallbacks(p_bar);
            player.stop();
            player.release();
            player = null;
        }
    }

    //Play and Pause music Method

    public void playOrPauseMusic(View view) {

        if (player.isPlaying()) {
            player.pause();
            btnPlayPause.setBackgroundResource(R.drawable.play_icon_white);
            //   btnPlayPause.setText("PLAY");
        } else {
            player.start();
            seekbar.setMax(player.getDuration());
            handler.removeCallbacks(p_bar);
            handler.postDelayed(p_bar, 1000);
            btnPlayPause.setBackgroundResource(R.drawable.pause_icon_white);
            //    btnPlayPause.setText("PAUSE");
        }
    }

    //playPrevSongMethod

    public void playPrev(View view) {
        player.stop();

        afterCurrent.add(song);
        //adding the last song of beforeCurrent to displaySong
        displaySongBasedOnIndex(songlist.indexOf(beforeCurrent.remove(beforeCurrent.size()-1)));

        playSong(filelink);
//        currentIndex = getPrevSong(currentIndex);
//        Log.d("poly", "After playPrevious, the index is now :" + currentIndex);
//        displaySongBasedOnIndex(currentIndex);
//        playSong(filelink);
    }

    //playNextSong Method

    public void playNext(View view) {

        player.stop();

        //adding current song to beforeCurrent
        beforeCurrent.add(song);


        //afterCurrent.remove(0) returns the first song in the index
        //to songlist
        displaySongBasedOnIndex(songlist.indexOf(afterCurrent.remove(0)));


        playSong(filelink);
        /*currentIndex = getNextSong(currentIndex);
        Log.d("poly", "After playNext, the index is now :" + currentIndex);
        displaySongBasedOnIndex(currentIndex);
        playSong(filelink);*/
    }

    //Repeat Button Method

    public void repeatSong(View view) {

        if (repeatFlag) {
            repeatBtn.setBackgroundResource(R.drawable.repeat_button_off);
        } else {
            repeatBtn.setBackgroundResource(R.drawable.repeat_button_on);
        }
        repeatFlag = !repeatFlag;
    }

    //shuffle song Method

    public void shuffleSong(View view) {

        //creating shuffle list
        ArrayList<Song> shuffleList = new ArrayList<Song>();

        //adding all of the songs in songlist to shuffle list
        shuffleList.addAll(songlist);

        //removing current song from shufflelist
        shuffleList.remove(song);


        if (shuffleFlag) {
            shuffleBtn.setBackgroundResource(R.drawable.shuffle_button_off);
            afterCurrent.clear();
            beforeCurrent.clear();

            for (int i = 0; i <songlist.indexOf(song) ; i++) {
                beforeCurrent.add(songlist.get(i));
            }

            for (int i = songlist.indexOf(song)+1 ;i <songlist.size(); i++) {
                afterCurrent.add(songlist.get(i));
            }


            for (int i = 0; i < afterCurrent.size(); i++) {
                Log.d("poly", afterCurrent.get(i).getTitle());
            }


        } else {
            shuffleBtn.setBackgroundResource(R.drawable.shuffle_button_on);
           Collections.shuffle(shuffleList);
           afterCurrent.clear();
           beforeCurrent.clear();
           afterCurrent.addAll(shuffleList);

        }
        shuffleFlag = !shuffleFlag;
    }

    //getPrevSong Method

    public int getPrevSong(int currentSongIndex) {
        if (currentSongIndex <= 0) {
            return currentSongIndex;
        } else {
            return currentSongIndex - 1;
        }
    }

    //getNextSong Method

    public int getNextSong(int currentSongIndex) {
        if (currentSongIndex >=songlist.size() - 1) {
            return currentSongIndex;
        } else {
            return currentSongIndex + 1;
        }
    }

    //addtoLikedPlaylist Method

    public void addtoLiked(View view) {
        if (likedFlag) {
            likedBtn.setBackgroundResource(R.drawable.liked_icon_off);
        }else {
            likedBtn.setBackgroundResource(R.drawable.liked_icon_on);
            Song song = songlist.get(currentIndex);
            favList.add(song);
            Gson gson = new Gson();
            //converting favlist to string
            String json = gson.toJson(favList);
            Log.d("gson", json);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("list",json);
            editor.apply();
        }
        likedFlag = !likedFlag;
    }

}


