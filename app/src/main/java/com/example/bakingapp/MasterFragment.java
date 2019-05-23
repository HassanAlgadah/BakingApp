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
    private Step steps;


    public MasterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentbody, container, false);
        TextView text = rootView.findViewById(R.id.desc);
        if (savedInstanceState != null) {
            steps = savedInstanceState.getParcelable("staps");
        }
        PlayerView playerView = rootView.findViewById(R.id.vidplayer);
        if (steps != null) {
            text.setText(steps.getDescription());
        }

        return rootView;

    }


    public Step getSteps() {
        return steps;
    }

    public void setSteps(Step steps) {
        this.steps = steps;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("staps", steps);
    }
}
