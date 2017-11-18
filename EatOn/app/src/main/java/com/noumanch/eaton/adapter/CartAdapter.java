package com.noumanch.eaton.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.noumanch.eaton.ItemClickListener;
import com.noumanch.eaton.R;
import com.noumanch.eaton.models.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nouman01 on 10/2/2017.
 */


class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    ItemClickListener clickListener;
    public TextView name,price;
    public ImageView count;
    public CartViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.cart_item_name);
        price = (TextView) itemView.findViewById(R.id.cart_item_price);
        count = (ImageView) itemView.findViewById(R.id.cart_item_count);


    }

    @Override
    public void onClick(View view) {

    }
}
public class CartAdapter  extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> data  = new ArrayList<>();
    private Context context;

    public CartAdapter( Context context,List<Order> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_item,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {

        TextDrawable drawable= TextDrawable.builder().buildRound(""+data.get(position).getQuantity(), Color.RED);
        //holder.count.setImageBitmap(drawable);

        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int i =     (Integer.parseInt(data.get(position).getPrice()) *(Integer.parseInt(data.get(position).getQuantity())) );
        holder.price.setText(fmt.format(i));
        holder.name.setText(data.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
