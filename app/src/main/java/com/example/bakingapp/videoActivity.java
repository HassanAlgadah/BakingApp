package com.example.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class videoActivity extends AppCompatActivity {
    private Step step;
    private int pos;
    private ArrayList<Step> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Bundle bundle = getIntent().getExtras();
        step = bundle.getParcelable("step");
        pos = bundle.getInt("position");
        steps = bundle.getParcelableArrayList("steps");
        System.out.println(pos);

        MasterFragment description = new MasterFragment();

        description.setIsvid(false);
        description.setSteps(step);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.de,description)
                .commit();

    }
    void next(View view) {
        pos++;
        if (pos < steps.size() - 1) {
            Intent intent = new Intent(this, videoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("step", steps.get(pos));
            bundle.putInt("position", pos);
            bundle.putParcelableArrayList("steps",steps);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            Toast.makeText(this, "this is the last step", Toast.LENGTH_SHORT).show();
        }
    }
}
