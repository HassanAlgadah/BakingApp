package com.example.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.Viewholder> {
    private List<Step> steps;
    private Context con;

    private final RecAdapterClickHandler mClickHandler;

    public interface RecAdapterClickHandler {
        void onClick(Step step);
    }

    public StepsAdapter(RecAdapterClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        con = viewGroup.getContext();
        View view = inflater.inflate(R.layout.recyclerhold, viewGroup, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        Step m = steps.get(i);
        viewholder.title.setText(m.getDescription());

    }

    public void setSteps(List<Step> step) {
        steps = step;
    }

    @Override
    public int getItemCount() {
        if (steps == null) {
            return 0;
        }
        return steps.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;

        public Viewholder(View v) {
            super(v);
            title = v.findViewById(R.id.name);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Step m = steps.get(adapterPosition);
            mClickHandler.onClick(m);
        }
    }
}
