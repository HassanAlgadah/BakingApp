package com.example.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity implements StepsAdapter.RecAdapterClickHandler {
    private ArrayList<Step> steps;
    private ArrayList<Ingredient> ingredients;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private StepsAdapter stepsAdapter;
    private IngredientsAdapter ingAdapter;
    private RecyclerView.LayoutManager layoutManager, layoutManager2;
    private boolean isTablet;
    private FragmentManager fragmentManager;
    private DescriptionFragment description;
    private VideoFragment vid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        isTablet = getResources().getBoolean(R.bool.isTablet);

        Bundle bundle = getIntent().getExtras();
        steps = bundle.getParcelableArrayList("steps");
        ingredients = bundle.getParcelableArrayList("ingredients");
        recyclerView = findViewById(R.id.steps);
        recyclerView2 = findViewById(R.id.ing);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager2 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        stepsAdapter = new StepsAdapter(this);
        ingAdapter = new IngredientsAdapter();
        stepsAdapter.setSteps(steps);
        ingAdapter.setIngredients(ingredients);
        recyclerView.setAdapter(stepsAdapter);
        recyclerView2.setAdapter(ingAdapter);
    }

    @Override
    public void onClick(Step step, int k) {
        if (!isTablet) {
            Intent intent = new Intent(this, VideoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("step", step);
            bundle.putParcelableArrayList("steps", steps);
            bundle.putInt("position", k);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            fragmentManager = getSupportFragmentManager();
            if (fragmentManager.findFragmentById(R.id.decr) != null) {
                fragmentManager.beginTransaction().remove(vid).commit();
                fragmentManager.beginTransaction().remove(description).commit();
            }

            description = new DescriptionFragment();
            description.setSteps(step);
            fragmentManager.beginTransaction()
                    .add(R.id.decr, description)
                    .commit();

            vid = new VideoFragment();
            vid.setStep(step);
            fragmentManager.beginTransaction()
                    .add(R.id.video, vid)
                    .commit();


        }

    }
}
