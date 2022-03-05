package com.gavriden.sulzerservice;


import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoInstallationImpeller extends FragmentActivity{

    private View decorView;

    TextView textSubtitles;
    TextView textSubtitlesBottom;
    TextView textTools;
    TextView txtManual;
    ImageButton btnRepeat;
    Button btnNext;
    Button btnPrevious;
    ImageButton btnNextVideo;
    ImageButton btnPreviousVideo;
    ImageButton btnEnd;
    ImageButton btnToStart;
    ImageButton btnHome;
    ImageButton btnTorque;
    ImageButton btnPlay;
    ImageButton btnPause;
    ImageButton btnSubtitles;
    Animation anim;
    DialogFragment dlg;

    private SeekBar seekBar;
    private VideoView videoView;
    ConstraintLayout progressLine;
    ConstraintLayout videoLayout;
    volatile boolean touched = false;
    volatile boolean comments = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        anim = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        //btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
        //btnNext = (Button) findViewById(R.id.btnNext);
        //btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnSubtitles = findViewById(R.id.btnSubtitles);
        btnTorque = findViewById(R.id.btnTorque);
        btnTorque.setVisibility(View.VISIBLE);
        dlg = new TorqueDialogFragment();
        btnPause = findViewById(R.id.btnPause);
        btnPause.setVisibility(View.INVISIBLE);
        seekBar = findViewById(R.id.seekBar);
        videoView = (VideoView) findViewById(R.id.videoView);
        btnPlay = findViewById(R.id.btnPlay);
        btnNextVideo = findViewById(R.id.btnNextVideo);
        btnPreviousVideo = findViewById(R.id.btnPreviousVideo);
        btnEnd = findViewById(R.id.btnEnd);
        btnToStart = findViewById(R.id.btnToStart);
        videoLayout = findViewById(R.id.videoLayout);
        txtManual = findViewById(R.id.txtManual);
        txtManual.setText(R.string.atеach_impeller);
        textTools = findViewById(R.id.textTools);
        textTools.append("\n"+getText(R.string.allen_wrench)+"\n"+getText(R.string.w91411_1)+"\n"+getText(R.string.w91411_2)+
                "\n"+getText(R.string.w91411_3)+"\n"+getText(R.string.w91411_4)+"\n"+getText(R.string.w91411_5)+"\n"+getText(R.string.w91411_6)
                +"\n"+getText(R.string.w91411_7));
        textTools.setVisibility(View.VISIBLE);

        //VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.reassembly_impeller));
        videoView.requestFocus();
        seekBar.setMax(videoView.getDuration());
        videoView.start();

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        if (touched == false) {
                            if (videoView.canPause() && !videoView.isPlaying()) {
                                btnPlay.bringToFront();
                                btnPlay.setVisibility(View.VISIBLE);
                            }else{
                                btnPause.bringToFront();
                                btnPause.setVisibility(View.VISIBLE);
                            }
                            btnPreviousVideo.bringToFront();
                            btnPreviousVideo.setVisibility(View.VISIBLE);
                            seekBar.bringToFront();
                            seekBar.setVisibility(View.VISIBLE);
                            btnNextVideo.bringToFront();
                            btnNextVideo.setVisibility(View.VISIBLE);
                            btnToStart.bringToFront();
                            btnToStart.setVisibility(View.VISIBLE);
                            btnEnd.bringToFront();
                            btnEnd.setVisibility(View.VISIBLE);
                            touched = true;
                        }else if (touched==true){
                            btnPlay.setVisibility(View.INVISIBLE);
                            btnPause.setVisibility(View.INVISIBLE);
                            btnPreviousVideo.setVisibility(View.INVISIBLE);
                            seekBar.setVisibility(View.INVISIBLE);
                            btnNextVideo.setVisibility(View.INVISIBLE);
                            btnToStart.setVisibility(View.INVISIBLE);
                            btnEnd.setVisibility(View.INVISIBLE);
                            touched = false;
                        }

                        break;

                }

                return true;
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    case R.id.btnHome:

                        Intent b = new Intent(VideoInstallationImpeller.this, ServiceActivity.class);
                        startActivity(b);
                        overridePendingTransition(R.anim.left_out, R.anim.alpha);
                        finish();

                        break;

                    case R.id.btnTorque:

                        dlg.show(getSupportFragmentManager(), "dlg");
                        break;

                    case R.id.btnPause:

                        videoView.pause();
                        btnPause.setVisibility(View.INVISIBLE);
                        btnPlay.setVisibility(View.VISIBLE);
                        break;

                    case R.id.btnPlay:

                        videoView.start();
                        seekBar.setProgress(videoView.getCurrentPosition());
                        seekBar.postDelayed(onEverySecond, 50);
                        btnPlay.setVisibility(View.INVISIBLE);
                        btnPause.setVisibility(View.VISIBLE);
                        break;

                    case R.id.btnToStart:

                        videoView.seekTo(0);
                        seekBar.setProgress(videoView.getCurrentPosition());
                        seekBar.postDelayed(onEverySecond, 50);

                        break;

                    case R.id.btnEnd:

                        videoView.seekTo(videoView.getDuration());
                        seekBar.setProgress(videoView.getCurrentPosition());
                        seekBar.postDelayed(onEverySecond, 50);
                        break;

                    case R.id.btnPreviousVideo:
                        Intent prVideo = new Intent(VideoInstallationImpeller.this, SelectWetActivity.class);
                        startActivity(prVideo);
                        overridePendingTransition(R.anim.left_out, R.anim.alpha);
                        finish();

                        break;

                    case R.id.btnNextVideo:
                        Intent nextVideo = new Intent(VideoInstallationImpeller.this, VideoAssemblyWetEnd.class);
                        startActivity(nextVideo);
                        overridePendingTransition(R.anim.left_out, R.anim.alpha);
                        finish();

                        break;

                    case R.id.btnSubtitles:

                        if (comments == false){

                            txtManual.bringToFront();
                            txtManual.setVisibility(View.VISIBLE);
                            comments = true;

                        } else {

                            txtManual.setVisibility(View.INVISIBLE);
                            comments = false;

                        }



                        break;



                    default:
                        break;

                }

            }
        };


        btnHome.setOnClickListener(onClickListener);
        btnTorque.setOnClickListener(onClickListener);
        btnPause.setOnClickListener(onClickListener);
        btnPlay.setOnClickListener(onClickListener);
        btnToStart.setOnClickListener(onClickListener);
        btnEnd.setOnClickListener(onClickListener);
        btnSubtitles.setOnClickListener(onClickListener);
        btnPreviousVideo.setOnClickListener(onClickListener);
        btnNextVideo.setOnClickListener(onClickListener);


       /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                textSubtitles = (TextView) findViewById(R.id.textSubtitles);
                textSubtitles.setText(R.string.unscrew_914_11);
                textSubtitles.startAnimation(anim);

                textTools =(TextView) findViewById(R.id.textTools);
                textTools.append("\n"+getText(R.string.wrench));
                textTools.startAnimation(anim);

            }
        }, 1000);*/

        /*Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                textSubtitlesBottom = (TextView) findViewById(R.id.textSubtitlesBottom);
                textSubtitlesBottom.setText(R.string.detach_impeller);
                textSubtitlesBottom.startAnimation(anim);

            }
        }, 7000);*/





        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(videoView.getDuration());
                seekBar.postDelayed(onEverySecond, 50);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    // this is when actually seekbar has been seeked to a new position
                    videoView.seekTo(progress);
                }
            }
        });

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

    }

    private Runnable onEverySecond=new Runnable() {
        @Override public void run() {
            if(seekBar != null) {
                seekBar.setProgress(videoView.getCurrentPosition());
            } if(videoView.isPlaying()) {
                seekBar.postDelayed(onEverySecond, 50);
            }
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }

    }

    private int hideSystemBars(){
        return  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

}
