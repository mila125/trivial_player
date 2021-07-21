package com.example.folclorchileno;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Formatter;

import static android.icu.text.DisplayContext.LENGTH_SHORT;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    int item = 0;
    MediaPlayer mp = new MediaPlayer();

    private TextView mCurrentTime;
    private TextView mEndTime;
    private ProgressBar mProgress;
    Formatter mFormatter;
    StringBuilder mFormatBuilder;
    private boolean mShowing;
    private boolean mDragging;

    private int progressStatus = 0;
    ProgressBar progressBar;
    TextView textView ;

    Handler handler = new Handler();



    void playMusic(String Song) throws IOException {


        mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(Song,"raw",getPackageName()));
        mp.start();

       // new Thread(new Runnable() {

        //}).start();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final TextView authors = (TextView) findViewById(R.id.authors);
        final TextView songnames = (TextView) findViewById(R.id.songnames);
        final ImageView ImageView2 = (ImageView) findViewById(R.id.imageView2);
        final Button start = (Button) findViewById(R.id.start);
        final Button playpause = (Button) findViewById(R.id.playpause);
        final ImageButton prevbtn = (ImageButton) findViewById(R.id.prev);
        final ImageButton nextbtn = (ImageButton) findViewById(R.id.next);
        final Button salir = (Button) findViewById(R.id.salir);
      //  final ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar);

        String[] cancion_titulos = new String[]{"headashe","lovely","rose","dinamite","mrrr","f","g","h"};
        String[] autores = new String[]{"drake","bila","mustang","fly","e","f","g","h"};
        String[] images = new String[]{"a","b","c","d","e","f","g","h"};
        String[] values = new String[]{"jazz_music_bed_9","pop_music_bed_12","rock_music_bed_11","techno_music_bed_6","headache","late_again","stalker","vice_and_a_rose"};
        ImageView2.setBackgroundResource(getResources().getIdentifier(images[item],"drawable",getPackageName()));

        authors.setText(autores[item]);
        songnames.setText(cancion_titulos[item]);




        playpause.setText("reanudar");



  salir.setOnClickListener(new View.OnClickListener() {

    public void onClick(View v) {


        finish();
        System.exit(0);
    }
});
    playpause.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            if (!mp.isPlaying()) { //si no suena al play
                playpause.setText("detener");
                mp.start();
            } else if (mp.isPlaying()) { //si suena la paro
                playpause.setText("reanudar");
                mp.pause();

            }
        }
    });



    start.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {


            authors.setText(autores[item]);
            songnames.setText(cancion_titulos[item]);
            playpause.setEnabled(true);
            playpause.setText("detener");
            String itemval = values[item];
            start.setEnabled(false);
        //    mProgress.setMax(100);

         //   float progress = ((float) mp.getCurrentPosition() / mp.getDuration()) * 100;
           // float max = ((float) mp.getDuration()) * 100;

        //    mProgress.setProgress((int) progress);

          //  while (progressStatus < max) {


             //   progressStatus += 1;

              //  handler.post(new Runnable() {
                //    public void run() {
                 //       mProgress.setProgress(progressStatus);
                //    }
              //  });
               // try {

                 //   Thread.sleep(200);
              //  } catch (InterruptedException e) {
                //    e.printStackTrace();
               // }


                try {

                    playMusic(itemval);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                start.setText("esta corriendo");
            }
        
    });
    prevbtn.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
            //timer=0;
            ImageView2.setBackgroundResource(getResources().getIdentifier(images[item],"drawable",getPackageName()));
            playpause.setEnabled(false);
            authors.setText(autores[item]);
            songnames.setText(cancion_titulos[item]);
            playpause.setText("reanudar");
            start.setText("iniciar");

            float progress = ((float) mp.getCurrentPosition() / mp.getDuration()) * 100;
            float max = ((float) mp.getDuration()) * 100;
            mProgress.setProgress(0);



            mp.stop();
            start.setEnabled(true);
            --item;
            if(item<0)
            {
                item=7;
            }
        }
    });

    nextbtn.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {




            //timer=0;
            authors.setText(autores[item]);
            songnames.setText(cancion_titulos[item]);
            ImageView2.setBackgroundResource(getResources().getIdentifier(images[item],"drawable",getPackageName()));
            playpause.setText("reanudar");
            start.setText("iniciar");



            mp.stop();
            playpause.setEnabled(false);
            start.setEnabled(true);
            ++item;
            if(item>7)
            {
                item=0;
            }
        }
    });}

    }

