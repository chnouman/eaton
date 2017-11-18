package com.noumanch.eaton;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nouman01 on 9/29/2017.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView id,status,phone,address;
    ItemClickListener clickListener;
    public OrderViewHolder(View itemView) {
        super(itemView);

        id = (TextView) itemView.findViewById(R.id.order_id);
        status = (TextView) itemView.findViewById(R.id.order_status);
        phone = (TextView) itemView.findViewById(R.id.order_address);
        address = (TextView) itemView.findViewById(R.id.order_phone);

        itemView.setOnClickListener(this);
    }


    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View view) {

        Toast.makeText(view.getContext(), "clicked on order item", Toast.LENGTH_SHORT).show();
        clickListener.onClick(view,getAdapterPosition(),false);
    }


}
