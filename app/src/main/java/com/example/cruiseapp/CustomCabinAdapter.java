package com.example.cruiseapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CustomCabinAdapter extends RecyclerView.Adapter<CustomCabinAdapter.myCustomCabinHolder> {

    private ArrayList<CruiseCabin> cruiseCabinsList=new ArrayList<>();
    public CustomCabinAdapter(ArrayList<CruiseCabin> cruiseCabinsList){
        this.cruiseCabinsList=cruiseCabinsList;
    }

    @NonNull
    @Override
    public myCustomCabinHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull myCustomCabinHolder myCustomCabinHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class myCustomCabinHolder extends RecyclerView.ViewHolder {
        public myCustomCabinHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
