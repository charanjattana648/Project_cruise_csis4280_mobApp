package com.example.cruiseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomCabinAdapter extends RecyclerView.Adapter<CustomCabinAdapter.myCustomCabinHolder> {

    private ArrayList<CruiseCabin> cruiseCabinsList=new ArrayList<>();
    public CustomCabinAdapter(ArrayList<CruiseCabin> cruiseCabinsList){
        this.cruiseCabinsList=cruiseCabinsList;
    }

    @NonNull
    @Override
    public myCustomCabinHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.layout_cabins,viewGroup,false);
        CustomCabinAdapter.myCustomCabinHolder customCabinHolder=new CustomCabinAdapter.myCustomCabinHolder(view);
        return customCabinHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myCustomCabinHolder myCustomCabinHolder, int i) {
        CruiseCabin cruiseCabin=cruiseCabinsList.get(i);
        myCustomCabinHolder.txtType.setText(cruiseCabin.getType());
        myCustomCabinHolder.txtPrice.setText(cruiseCabin.getPrice()+"");
        Picasso.get().load(cruiseCabin.getImage()).into(myCustomCabinHolder.imgCabin);

    }

    @Override
    public int getItemCount() {
        return cruiseCabinsList.size();
    }

    public class myCustomCabinHolder extends RecyclerView.ViewHolder {
        TextView txtType,txtPrice;
        ImageView imgCabin;
        public myCustomCabinHolder(@NonNull final View itemView) {
            super(itemView);
            txtType=itemView.findViewById(R.id.txt_cabin_type_val);
            txtPrice=itemView.findViewById(R.id.txt_cabin_price_val);
            imgCabin=itemView.findViewById(R.id.img_cabin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orderIntent=new Intent(itemView.getContext(),CheckoutTrip.class);
                    orderIntent.putExtra("ctype",txtType.getText());
                    orderIntent.putExtra("cprice",txtPrice.getText());
                    orderIntent.putExtra("cruisename",txtPrice.getText());

                    itemView.getContext().startActivity(orderIntent);
                }
            });
        }



    }
}
