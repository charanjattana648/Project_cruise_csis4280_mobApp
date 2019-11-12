package com.example.cruiseapp;

import android.content.Intent;
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
        myCustomAdapterHolder.txt_guest.setText(c.getGuests()+"");
        myCustomAdapterHolder.txt_crew.setText(c.getCrew()+"");
        String d="";
        for(int j=0;j<c.getDeparts_from().length;j++)
        {
            d+=c.getDeparts_from()[j]+" ";
        }
        myCustomAdapterHolder.txt_depart.setText(d);
        String s="";
        for(int k=0;k<c.getDeparts_from().length;k++)
        {
            s+=c.getDeparts_from()[k]+" ";
        }
        myCustomAdapterHolder.txt_sail.setText(s);


    }

    @Override
    public int getItemCount() {
        return cruises_list.size();
    }

    public CustomAdapter(ArrayList<Cruise> cruises_list){
        this.cruises_list=cruises_list;
    }

    public class myCustomAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_comp_name,txt_cruise_name , txt_crew,txt_depart,txt_sail , txt_guest;
        ImageView cruise_img;
        public myCustomAdapterHolder(@NonNull final View itemView) {
            super(itemView);
            txt_comp_name=itemView.findViewById(R.id.txt_cname_val);
            txt_cruise_name=itemView.findViewById(R.id.txt_cruiseName_val);
            cruise_img=itemView.findViewById(R.id.cruise_imageView);
            txt_guest = itemView.findViewById(R.id.txt_guest3);
            txt_crew = itemView.findViewById(R.id.txt_crew);
            txt_depart = itemView.findViewById(R.id.txt_Depart);
            txt_sail = itemView.findViewById(R.id.txt_sail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Hello Welcome", Toast.LENGTH_SHORT).show();
                    Intent cruiseDetail_intent=new Intent(itemView.getContext(),CruiseDetailActivity.class);
                    cruiseDetail_intent.putExtra("name",txt_cruise_name.getText());
                    itemView.getContext().startActivity(cruiseDetail_intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
