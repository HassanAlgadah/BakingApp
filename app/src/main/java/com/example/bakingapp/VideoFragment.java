package com.example.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoFragment extends Fragment {
    private Step step;
    private SimpleExoPlayer player;
    PlayerView playerView;

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
        if(savedInstanceState!=null){
            step = savedInstanceState.getParcelable("staps");
        }
        if(step.getVideoURL().equals("")){
            Toast.makeText(this.getContext(), "there is no video for this step", Toast.LENGTH_SHORT).show();
            return rootView;
        }

        player = ExoPlayerFactory.newSimpleInstance(this.getContext(),
                new DefaultTrackSelector());
        playerView.setPlayer(player);

        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(this.getContext(),
                Util.getUserAgent(this.getContext(), "ExpoPlayer"));

        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(defaultDataSourceFactory)
                .createMediaSource(Uri.parse(step.getVideoURL()));
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);

        return rootView;

    }

    @Override
    public void onStop() {
        super.onStop();
        if(playerView!=null&&player!=null) {
            playerView.setPlayer(null);
            player.release();
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("staps",step);
    }
}


