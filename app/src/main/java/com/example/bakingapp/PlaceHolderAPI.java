package com.example.bakingapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolderAPI {
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Food>> getfoods();
}
