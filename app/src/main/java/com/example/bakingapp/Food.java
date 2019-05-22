package com.example.bakingapp;

import java.util.ArrayList;
import java.util.List;

public class Food {
    private String id;
    private String name;
    private List<Ingredient> ingredients;
    private List<Step> steps;

    public Food() {
    }

    public Food(String id, String name, ArrayList<Ingredient> Ingredients, ArrayList<Step> steps) {
        this.id = id;
        this.name = name;
        this.ingredients = Ingredients;
        steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> Ingredients) {
        this.ingredients = Ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> step) {
        steps = step;
    }
}
