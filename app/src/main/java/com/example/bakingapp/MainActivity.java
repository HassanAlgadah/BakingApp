package com.example.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Food food;
    private String JsonAPI = "https://d17h27t6h515a5.cloudfront.net/";
    private ImageButton ib;
     static String ing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        food = new Food();
        ib = findViewById(R.id.brownies);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Brownies(v);
            }
        });

    }

    void NutellaPie(View view){
        start(0);
    }
    void Brownies(View view){
        start(1);
    }
    void YellowCake(View view){
        start(2);
    }
    void Cheesecake(View view){
        start(3);
    }

    void start(final int choice){
        Call<List<Food>> call=Networking.getResponse(JsonAPI);
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                food.setName(response.body().get(choice).getName());
                food.setId(response.body().get(choice).getId());
                food.setIngredients(response.body().get(choice).getIngredients());
                food.setSteps(response.body().get(choice).getSteps());
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                ingredients.addAll(food.getIngredients());
                ArrayList<Step> steps = new ArrayList<>();
                steps.addAll(food.getSteps());


                Intent intent = new Intent(getApplicationContext(),RecipeDetail.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("ingredients", ingredients);
                bundle.putParcelableArrayList("steps", steps);
                WidgetSetup(ingredients);


                intent.putExtras(bundle);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
    }

    private void WidgetSetup(ArrayList<Ingredient> ingredients) {
        ing = null;

        for (int i = 0; i < ingredients.size(); i++) {
            ing += (ingredients.get(i).getQuantity()+ingredients.get(i).getMeasure()+" "+ingredients.get(i).getIngredient()+"\n");
        }
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), IngredientsWidgets.class));
        IngredientsWidgets myWidget = new IngredientsWidgets();
        myWidget.onUpdate(getApplicationContext(), AppWidgetManager.getInstance(getApplicationContext()),ids);
    }

}
