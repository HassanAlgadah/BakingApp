package com.example.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MasterFragment extends Fragment {
    private boolean isvid;
    private Step steps;


    public MasterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentbody, container, false);
        TextView text =rootView.findViewById(R.id.desc);
        if(!isvid) {
            if (steps != null) {
                text.setText(steps.getDescription());
            }
        }else{

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
