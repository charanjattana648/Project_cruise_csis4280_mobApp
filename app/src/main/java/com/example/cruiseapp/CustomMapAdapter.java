package com.example.cruiseapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomMapAdapter extends RecyclerView.Adapter<CustomMapAdapter.myCustomMapHolder> {

    private ArrayList<CruisePackage> cruisePackagesList=new ArrayList<>();
    public CustomMapAdapter(ArrayList<CruisePackage> cruisePackagesList){
        this.cruisePackagesList=cruisePackagesList;
    }

    @NonNull
    @Override
    public CustomMapAdapter.myCustomMapHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.layout_packages_with_map,viewGroup,false);
        CustomMapAdapter.myCustomMapHolder customMapHolder=new CustomMapAdapter.myCustomMapHolder(view);
        return customMapHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomMapAdapter.myCustomMapHolder myCustomMapHolder, int i) {
        CruisePackage cruisePackage=cruisePackagesList.get(i);
        Picasso.get().load(cruisePackage.getRouteMap()).into(myCustomMapHolder.img_route_map);
        myCustomMapHolder.txt_package_name.setText(cruisePackage.getPackageName());
        myCustomMapHolder.txt_dept_date.setText(cruisePackage.getDeptDate()+"");
        //myCustomMapHolder.img_route_map

    }

    @Override
    public int getItemCount() {
        return cruisePackagesList.size();
    }

    public class myCustomMapHolder extends RecyclerView.ViewHolder{
        TextView txt_package_name,txt_dept_date;
        ImageView img_route_map;
        public myCustomMapHolder(@NonNull View itemView) {
            super(itemView);
            txt_package_name=itemView.findViewById(R.id.txt_packageName);
            txt_dept_date=itemView.findViewById(R.id.txt_date);
            img_route_map=itemView.findViewById(R.id.img_route);


        }

    }
}
