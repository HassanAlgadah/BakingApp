package com.example.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.Data.Step;

public class DescriptionFragment extends Fragment {
    private Step steps;


    public DescriptionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentbody, container, false);
        TextView text = rootView.findViewById(R.id.desc);
        if (savedInstanceState != null) {
            steps = savedInstanceState.getParcelable("staps");
        }
        if (steps != null) {
            text.setText(steps.getDescription());
        }

        return rootView;

    }

    public void setSteps(Step steps) {
        this.steps = steps;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("staps", steps);
    }
}
