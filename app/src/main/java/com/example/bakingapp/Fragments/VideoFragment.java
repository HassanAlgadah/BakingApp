package com.example.bakingapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bakingapp.R;
import com.example.bakingapp.Data.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoFragment extends Fragment {
    private Step step;
    private SimpleExoPlayer player;
    PlayerView playerView;
    private int currentWindow;
    private long playbackPosition;
    private boolean ready;

    public void setStep(Step step) {
        this.step = step;
    }

    public VideoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.vid_fragment, container, false);
        playerView = rootView.findViewById(R.id.vidplayer);
        ready = true;
        currentWindow = 0;
        playbackPosition = 0;

        if (savedInstanceState != null) {
            step = savedInstanceState.getParcelable("staps");
            playbackPosition = savedInstanceState.getLong("playpos", 0);
            ready = savedInstanceState.getBoolean("ready");
        }
        if (step.getVideoURL().equals("")) {
            Toast.makeText(this.getContext(), "there is no video for this step", Toast.LENGTH_SHORT).show();
            return rootView;
        }

        return rootView;

    }
    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this.getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);
        player.setPlayWhenReady(ready);

        Uri uri = Uri.parse(step.getVideoURL());
        MediaSource mediaSource = new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("ExpoPlayer")).
                createMediaSource(uri);

        player.prepare(mediaSource, true, false);
        player.seekTo(playbackPosition);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (playerView != null && player != null) {
            playerView.setPlayer(null);
            if (Util.SDK_INT > 23) {
                releasePlayer();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }
    private void releasePlayer() {
            if (player != null) {
                playbackPosition = player.getCurrentPosition();
                currentWindow = player.getCurrentWindowIndex();
                ready = player.getPlayWhenReady();
                player.release();
                player = null;
            }
        }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("staps", step);
        outState.putLong("playpos", player.getCurrentPosition());
        System.out.println(player.getContentPosition());
        outState.putBoolean("ready", player.getPlayWhenReady());

    }
}


