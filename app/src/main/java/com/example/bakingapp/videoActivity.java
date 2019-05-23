package com.example.bakingapp;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.icu.util.Measure;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class videoActivity extends AppCompatActivity {
    private Step step;
    private int pos;
    private ArrayList<Step> steps;
    private FragmentManager fragmentManager;
    private MasterFragment description;
    private VideoFragment vid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            findViewById(R.id.de).setVisibility(View.GONE);
            findViewById(R.id.prev).setVisibility(View.GONE);
            findViewById(R.id.nextbutton).setVisibility(View.GONE);

        } else {
        }
        if (savedInstanceState == null) {

            Bundle bundle = getIntent().getExtras();
            step = bundle.getParcelable("step");

            pos = bundle.getInt("position");
            steps = bundle.getParcelableArrayList("steps");
            fragmentManager = getSupportFragmentManager();


            description = new MasterFragment();
            description.setSteps(step);
            fragmentManager.beginTransaction()
                    .add(R.id.de, description)
                    .commit();

            vid = new VideoFragment();
            vid.setStep(step);

            fragmentManager.beginTransaction()
                    .add(R.id.vid, vid)
                    .commit();

        } else {
            steps = savedInstanceState.getParcelableArrayList("staps");
            pos = savedInstanceState.getInt("posi");
        }
    }

    void next(View view) {
        pos++;
        if(pos<0){
            pos=2;
        }
        if (pos < steps.size()) {
            changePage();
        } else {
            Toast.makeText(this, "this is the last step", Toast.LENGTH_SHORT).show();
        }
    }
    void prev(View view){
        pos--;
        if(pos>=steps.size()){
            pos = steps.size()-2;
        }
        if (pos > 0) {
            changePage();
        } else {
            Toast.makeText(this, "this is the first step", Toast.LENGTH_SHORT).show();
        }
    }

    private void changePage() {
        fragmentManager.beginTransaction().remove(vid).commit();
        fragmentManager.beginTransaction().remove(description).commit();

        description = new MasterFragment();
        description.setSteps(steps.get(pos));
        fragmentManager.beginTransaction()
                .add(R.id.de, description)
                .commit();
        vid = new VideoFragment();
        vid.setStep(steps.get(pos));

        fragmentManager.beginTransaction()
                .add(R.id.vid, vid)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("staps", steps);
        outState.putInt("posi", pos);

    }
}
