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

import java.util.ArrayList;

public class CustomActivitiesAdapter  extends RecyclerView.Adapter<CustomActivitiesAdapter.myCustomActivitiesAdapterHolder> {

    ArrayList<String> item_list = new ArrayList<>();
    int image;
    String name;

    public CustomActivitiesAdapter(ArrayList<String> item_list,int image,String name) {
        this.image = image;
        this.name=name;
        this.item_list=item_list;
    }

    @NonNull
    @Override
    public CustomActivitiesAdapter.myCustomActivitiesAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.layout_activies,viewGroup,false);
        CustomActivitiesAdapter.myCustomActivitiesAdapterHolder customActivitiesAdapterHolder=new CustomActivitiesAdapter.myCustomActivitiesAdapterHolder(view);
        return customActivitiesAdapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomActivitiesAdapter.myCustomActivitiesAdapterHolder myCustomActivitiesAdapterHolder, int i) {
            String itemName=item_list.get(i);
            myCustomActivitiesAdapterHolder.txt_item_title.setText(name+" Name");
            myCustomActivitiesAdapterHolder.txt_item_value.setText(itemName);
            myCustomActivitiesAdapterHolder.activity_img.setImageResource(image);
//            if(name.equalsIgnoreCase("Activiy"))
            switch (name)
            {
                case "Activiy":
                    break;
                case "Entertainment":
                    break;
                case "Dining":
                    break;
            }


    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }

    public class myCustomActivitiesAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_item_title,txt_item_value;
        ImageView activity_img;
        public myCustomActivitiesAdapterHolder(@NonNull final View itemView) {
            super(itemView);
            txt_item_title=itemView.findViewById(R.id.txt_activity_title);
            txt_item_value=itemView.findViewById(R.id.txt_activity_title_val);
            activity_img=itemView.findViewById(R.id.imageView_activities);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Hello Welcome", Toast.LENGTH_SHORT).show();
//                    Intent cruiseDetail_intent=new Intent(itemView.getContext(),CruiseDetailActivity.class);
//                    cruiseDetail_intent.putExtra("name",txt_cruise_name.getText());
//                    itemView.getContext().startActivity(cruiseDetail_intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }


}

