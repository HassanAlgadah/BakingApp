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

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public class MasterFragment extends Fragment {
    private boolean isvid;
    private Step steps;
    private SimpleExoPlayer player;


    public MasterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentbody, container, false);
        TextView text =rootView.findViewById(R.id.desc);
        PlayerView playerView = rootView.findViewById(R.id.vidplayer);
        if(!isvid) {
            if (steps != null) {
                text.setText(steps.getDescription());
            }
        }else{
            player = ExoPlayerFactory.newSimpleInstance(this.getContext(),
                    new DefaultTrackSelector());
            playerView.setPlayer(player);

            DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(this.getContext(),
                    Util.getUserAgent(this.getContext(),"ExpoPlayer"));

            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(defaultDataSourceFactory)
                    .createMediaSource(Uri.parse("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9cb_4-press-crumbs-in-pie-plate-creampie/4-press-crumbs-in-pie-plate-creampie.mp4"));
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);

        }
        return rootView;

    }

    public boolean isIsvid() {
        return isvid;
    }

    public void setIsvid(boolean isvid) {
        this.isvid = isvid;
    }

    public Step getSteps() {
        return steps;
    }

    public void setSteps(Step steps) {
        this.steps = steps;
    }
}
