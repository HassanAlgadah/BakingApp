package com.example.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.Data.Ingredient;
import com.example.bakingapp.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.Viewholder> {
    private List<Ingredient> Ingredients;
    private Context con;

    @NonNull
    @Override
    public IngredientsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        con = viewGroup.getContext();
        View view = inflater.inflate(R.layout.recyclerhold, viewGroup, false);

        return new IngredientsAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.Viewholder viewholder, int i) {
        Ingredient m = Ingredients.get(i);
        viewholder.title.setText(m.getQuantity() + m.getMeasure() + " " + m.getIngredient());

    }

    public void setIngredients(List<Ingredient> Ingredient) {
        Ingredients = Ingredient;
    }

    @Override
    public int getItemCount() {
        if (Ingredients == null) {
            return 0;
        }
        return Ingredients.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView title;

        public Viewholder(View v) {
            super(v);
            title = v.findViewById(R.id.name);
        }
    }
}
