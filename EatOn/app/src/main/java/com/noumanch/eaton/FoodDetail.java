package com.noumanch.eaton;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.noumanch.eaton.database.Database;
import com.noumanch.eaton.models.Food;
import com.noumanch.eaton.models.Order;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {


    private ElegantNumberButton elegantNumberButton ;
    private TextView            foodName;
     private TextView foodDescription;
    private TextView foodPrice;
    private ImageView food_image;
    FloatingActionButton cartButton;
    private String foodId;

    Food currentFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        elegantNumberButton = (ElegantNumberButton) findViewById(R.id.elegane_butn);
        cartButton          = (FloatingActionButton) findViewById(R.id.btnCart);
        foodDescription     = (TextView) findViewById(R.id.food_description);
        foodName            = (TextView) findViewById(R.id.food_name);
        foodPrice           = (TextView) findViewById(R.id.food_price);
        food_image          = (ImageView) findViewById(R.id.img_food);




        if (getIntent() != null) {

             foodId = getIntent().getStringExtra("foodId");
            if (!foodId.isEmpty() && foodId !=null) {

                loadDetail(foodId);

            }

        }
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(FoodDetail.this).addToCart(new Order(
                        foodId,
                        currentFood.getName(),
                        elegantNumberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDiscount()

                ));
                Toast.makeText(FoodDetail.this, "added to cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDetail(final String foodId) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference detail  = database.getReference("Foods");

        detail.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                currentFood = dataSnapshot.getValue(Food.class);
                Picasso.with(FoodDetail.this)
                        .load(currentFood.getImage())
                        .into(food_image);
                 foodPrice.setText(currentFood.getPrice());
                foodName.setText(currentFood.getName());
                foodDescription.setText(currentFood.getDescription());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
