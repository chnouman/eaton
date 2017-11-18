package com.noumanch.eaton;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.noumanch.eaton.models.Food;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView foodRecyclerView;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference food;
    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);


        foodRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_food);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FoodList.this);
        foodRecyclerView.setHasFixedSize(true);
        foodRecyclerView.setLayoutManager(linearLayoutManager);

        if (getIntent() != null) {

            String categoryId  = getIntent().getStringExtra("CategoryId");
            if (!categoryId.isEmpty() && categoryId!=null) {

                Toast.makeText(this, "id received is "+categoryId, Toast.LENGTH_SHORT).show();
                loadListFood(categoryId);
            }
        }
    }

    //food.orderByChild("MenuId").equalTo(categoryId)   equivalent to select * from Food where catgoryId = "something"
    private void loadListFood(String categoryId) {


        firebaseDatabase = FirebaseDatabase.getInstance();
        food             = firebaseDatabase.getReference("Foods");
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.layout.food_item,FoodViewHolder.class,food.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, final Food model, int position) {

                viewHolder.title.setText(model.getName());
                Picasso.with(FoodList.this)
                        .load(model.getImage())
                        .into(viewHolder.image);

                viewHolder.setClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FoodList.this, ""+model.getName(), Toast.LENGTH_SHORT).show();

                        Intent foodDetail = new Intent(FoodList.this,FoodDetail.class);
                        foodDetail.putExtra("foodId",adapter.getRef(position).getKey());
                        startActivity(foodDetail);
                    }
                });
            }
        };
        foodRecyclerView.setAdapter(adapter);

    }
}
