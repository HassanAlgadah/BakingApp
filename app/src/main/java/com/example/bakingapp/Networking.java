package com.example.bakingapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Networking {
//    public static List<Food> ParaseMoviepop(String json) {
//        try {
//            ArrayList<Food> foods = new ArrayList<>();
//            JSONArray foodsj = new JSONArray(json);
//            for (int i = 0; i < foodsj.length(); i++) {
//                Food food = new Food();
//                JSONObject jsonObject = foodsj.getJSONObject(i);
//                food.setId(jsonObject.getString("id"));
//                food.setName(jsonObject.getString("name"));
//                JSONArray resultsing = jsonObject.getJSONArray("ingredients");
//                ArrayList<Ingredient> ingredients = new ArrayList<>();
//                for (int j = 0; j < resultsing.length(); j++) {
//                    Ingredient ingredient = new Ingredient();
//                    JSONObject firstob = resultsing.getJSONObject(j);
//                    ingredient.setIngredient(firstob.getString("ingredient"));
//                    ingredient.setQuantity(firstob.getString("quantity"));
//                    ingredient.setMeasure(firstob.getString("measure"));
//                    ingredients.add(ingredient);
//                }
//                JSONArray resultsstep = jsonObject.getJSONArray("steps");
//                ArrayList<Step> steps = new ArrayList<>();
//                for (int j = 0; j < resultsstep.length(); j++) {
//                    Step step = new Step();
//                    JSONObject firstob = resultsstep.getJSONObject(j);
//                    step.setId(firstob.getString("id"));
//                    step.setShortDescription(firstob.getString("shortDescription"));
//                    step.setDescription(firstob.getString("description"));
//                    step.setVid(firstob.getString("videoURL"));
//                    steps.add(step);
//                }
//                food.setIngredients(ingredients);
//                food.setSteps(steps);
//                foods.add(food);
//            }
//            return foods;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Call<List<Food>> getResponse(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceHolderAPI api = retrofit.create(PlaceHolderAPI.class);
        Call<List<Food>> call = api.getfoods();
        return call;

}



}
