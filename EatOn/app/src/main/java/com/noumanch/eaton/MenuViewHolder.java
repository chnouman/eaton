package com.noumanch.eaton;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nouman01 on 9/29/2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder  {

    TextView title;
    ImageView image;
    ItemClickListener clickListener;
    public MenuViewHolder(final View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.menu_name);
        image = (ImageView) itemView.findViewById(R.id.menu_image);

        //title.setOnClickListener();

    }




}
