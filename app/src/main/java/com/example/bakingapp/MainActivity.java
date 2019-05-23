package com.example.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Recipe recipe;
    private String JsonAPI = "https://d17h27t6h515a5.cloudfront.net/";
    private ImageButton ib;
    static String ing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipe = new Recipe();
        ib = findViewById(R.id.brownies);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Brownies(v);
            }
        });

    }

    void NutellaPie(View view) {
        start(0);
    }

    void Brownies(View view) {
        start(1);
    }

    void YellowCake(View view) {
        start(2);
    }

    void Cheesecake(View view) {
        start(3);
    }

    void start(final int choice) {
        Call<List<Recipe>> call = Networking.getResponse(JsonAPI);
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipe.setName(response.body().get(choice).getName());
                recipe.setId(response.body().get(choice).getId());
                recipe.setIngredients(response.body().get(choice).getIngredients());
                recipe.setSteps(response.body().get(choice).getSteps());
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                ingredients.addAll(recipe.getIngredients());
                ArrayList<Step> steps = new ArrayList<>();
                steps.addAll(recipe.getSteps());


                Intent intent = new Intent(getApplicationContext(), RecipeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("ingredients", ingredients);
                bundle.putParcelableArrayList("steps", steps);
                WidgetSetup(ingredients);


                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }

    private void WidgetSetup(ArrayList<Ingredient> ingredients) {
        ing = null;

        for (int i = 0; i < ingredients.size(); i++) {
            ing += (ingredients.get(i).getQuantity() + ingredients.get(i).getMeasure() + " " + ingredients.get(i).getIngredient() + "\n");
        }
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), IngredientsWidgets.class));
        IngredientsWidgets myWidget = new IngredientsWidgets();
        myWidget.onUpdate(getApplicationContext(), AppWidgetManager.getInstance(getApplicationContext()), ids);
    }

}
