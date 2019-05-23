package com.example.bakingapp.Networking;

import com.example.bakingapp.Data.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Networking {

    public static Call<List<Recipe>> getResponse(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceHolderAPI api = retrofit.create(PlaceHolderAPI.class);
        Call<List<Recipe>> call = api.getfoods();
        return call;

    }


}
