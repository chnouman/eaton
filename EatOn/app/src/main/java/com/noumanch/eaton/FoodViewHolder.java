package com.noumanch.eaton;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nouman01 on 9/29/2017.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title;
    ImageView image;
    ItemClickListener clickListener;
    public FoodViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.food_name);
        image = (ImageView) itemView.findViewById(R.id.food_image);
        image.setOnClickListener(this);
        title.setOnClickListener(this);
    }


    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View view) {

        Toast.makeText(view.getContext(), "clicked on food item", Toast.LENGTH_SHORT).show();
        clickListener.onClick(view,getAdapterPosition(),false);
    }


}
