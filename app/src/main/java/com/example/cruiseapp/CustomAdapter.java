package com.example.cruiseapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myCustomAdapterHolder>  {

    ArrayList<Cruise> cruises_list=new ArrayList<>();
    public CustomAdapter(){}

    @NonNull
    @Override
    public myCustomAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.layout_cruise_list,viewGroup,false);
        myCustomAdapterHolder customAdapterHolder=new myCustomAdapterHolder(view);

        return customAdapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myCustomAdapterHolder myCustomAdapterHolder, int i) {

        Cruise c=cruises_list.get(i);
        myCustomAdapterHolder.txt_comp_name.setText(c.getCompanyName());
        myCustomAdapterHolder.txt_cruise_name.setText(c.getCruiseName());
        Picasso.get().load(c.getCruiseImage()).into(myCustomAdapterHolder.cruise_img);


    }

    @Override
    public int getItemCount() {
        return cruises_list.size();
    }

    public CustomAdapter(ArrayList<Cruise> cruises_list){
        this.cruises_list=cruises_list;
    }

    public class myCustomAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_comp_name,txt_cruise_name;
        ImageView cruise_img;
        public myCustomAdapterHolder(@NonNull final View itemView) {
            super(itemView);
            txt_comp_name=itemView.findViewById(R.id.txt_cname_val);
            txt_cruise_name=itemView.findViewById(R.id.txt_cruiseName_val);
            cruise_img=itemView.findViewById(R.id.cruise_imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Hello Welcome", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
