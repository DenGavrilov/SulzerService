package com.gavriden.sulzerservice;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

public class VideoActivity extends FragmentActivity {

    private View decorView;

    TextView textSubtitles;
    TextView textTools;
    TextView txtManual;

    ImageButton btnRepeat;
    ImageButton btnNextVideo;
    ImageButton btnPreviousVideo;
    ImageButton btnEnd;
    ImageButton btnToStart;
    ImageButton btnHome;
    ImageButton btnTorque;
    ImageButton btnPlay;
    ImageButton btnPause;
    ImageButton btnSubtitles;
    Button btnNext;
    Button btnPrevious;
    DialogFragment dlg;

    Animation anim;

    private SeekBar seekBar;
    private VideoView videoView2;
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
        btnHome = findViewById(R.id.btnHome);
        btnSubtitles = findViewById(R.id.btnSubtitles);
        btnTorque = findViewById(R.id.btnTorque);
        //btnTorque.setVisibility(View.VISIBLE);
        dlg = new TorqueDialogFragment();
        btnPause = findViewById(R.id.btnPause);
        btnPause.setVisibility(View.INVISIBLE);
        seekBar = findViewById(R.id.seekBar);
        videoView2 = findViewById(R.id.videoView);
        btnPlay = findViewById(R.id.btnPlay);
        btnNextVideo = findViewById(R.id.btnNextVideo);
        btnPreviousVideo = findViewById(R.id.btnPreviousVideo);
        btnEnd = findViewById(R.id.btnEnd);
        btnToStart = findViewById(R.id.btnToStart);
        videoLayout = findViewById(R.id.videoLayout);
        txtManual = findViewById(R.id.txtManual);
        txtManual.setText(R.string.unscrew_901_31);
        textTools =findViewById(R.id.textTools);
        textTools.append("\n"+getText(R.string.wrench)+"\n"+getText(R.string.w_13)+"\n"+getText(R.string.w_17)+"\n"+getText(R.string.w_19)+"\n"+getText(R.string.w_24));
        textTools.setVisibility(View.VISIBLE);

        videoView2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.unscrew_901_31));
        videoView2.requestFocus();
        seekBar.setMax(videoView2.getDuration());
        videoView2.start();

        videoView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:

                        if (!touched) {
                            if (videoView2.canPause() && !videoView2.isPlaying()) {
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
                        }else if (touched){
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

                        Intent b = new Intent(VideoActivity.this, ServiceActivity.class);
                        startActivity(b);
                        overridePendingTransition(R.anim.left_out, R.anim.alpha);
                        finish();

                        break;

                    case R.id.btnTorque:

                        dlg.show(getSupportFragmentManager(), "dlg");
                        break;

                    case R.id.btnPause:

                        videoView2.pause();
                        btnPause.setVisibility(View.INVISIBLE);
                        btnPlay.setVisibility(View.VISIBLE);
                        break;

                    case R.id.btnPlay:

                        videoView2.start();
                        seekBar.setProgress(videoView2.getCurrentPosition());
                        seekBar.postDelayed(onEverySecond2, 25);
                        btnPlay.setVisibility(View.INVISIBLE);
                        btnPause.setVisibility(View.VISIBLE);
                        break;

                    case R.id.btnToStart:

                        videoView2.seekTo(0);
                        seekBar.setProgress(videoView2.getCurrentPosition());
                        seekBar.postDelayed(onEverySecond2, 25);

                        break;

                    case R.id.btnEnd:

                        videoView2.seekTo(videoView2.getDuration());
                        seekBar.setProgress(videoView2.getCurrentPosition());
                        seekBar.postDelayed(onEverySecond2, 25);
                        break;

                    case R.id.btnNextVideo:
                        Intent next = new Intent(VideoActivity.this, Video2Activity.class);
                        startActivity(next);
                        overridePendingTransition(R.anim.left_out, R.anim.alpha);
                        finish();

                        break;

                    case R.id.btnPreviousVideo:
                        Intent prev = new Intent(VideoActivity.this, SelectBUActivity.class);
                        startActivity(prev);
                        overridePendingTransition(R.anim.left_out, R.anim.alpha);
                        finish();

                        break;

                    case R.id.btnSubtitles:

                        if (!comments){

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
        btnNextVideo.setOnClickListener(onClickListener);


        /*View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){

                    /*case R.id.btnRepeat:

                        VideoView videoView = (VideoView) findViewById(R.id.videoView);
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.unscrew_901_31));
                        videoView.requestFocus();
                        videoView.start();
                        break;*/

                    /*case R.id.btnNext:

                        Intent n = new Intent(VideoActivity.this, Video2Activity.class);
                        startActivity(n);
                        overridePendingTransition(R.anim.right_in, R.anim.alpha);
                        finish();

                        break;

                    case R.id.btnPrevious:*/

                    /*case R.id.btnHome:

                        Intent b = new Intent(VideoActivity.this, ServiceActivity.class);
                        startActivity(b);
                        overridePendingTransition(R.anim.left_out, R.anim.alpha);
                        finish();

                        break;

                }

            }
        };

        btnRepeat.setOnClickListener(onClickListener);
        btnNext.setOnClickListener(onClickListener);
        btnPrevious.setOnClickListener(onClickListener);
        btnHome.setOnClickListener(onClickListener);*/


        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                textSubtitles = (TextView) findViewById(R.id.textSubtitles);
                textSubtitles.startAnimation(anim);

                textTools =(TextView) findViewById(R.id.textTools);
                textTools.append("\n"+getText(R.string.wrench)+"\n"+getText(R.string.w_13)+"\n"+getText(R.string.w_17)+"\n"+getText(R.string.w_19)+"\n"+getText(R.string.w_24));
                textTools.startAnimation(anim);

            }
        }, 10000);




        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.unscrew_901_31));
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.requestFocus();
        videoView.start();


        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });*/

        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(videoView2.getDuration());
                seekBar.postDelayed(onEverySecond2, 25);
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
                    videoView2.seekTo(progress);
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

    private Runnable onEverySecond2=new Runnable() {
        @Override public void run() {
            if(seekBar != null) {
                seekBar.setProgress(videoView2.getCurrentPosition());
            } if(videoView2.isPlaying()) {
                seekBar.postDelayed(onEverySecond2, 25);
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
