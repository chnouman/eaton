package com.noumanch.eaton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.noumanch.eaton.common.CoreData;
import com.noumanch.eaton.models.Order;
import com.noumanch.eaton.models.Request;
import com.noumanch.eaton.models.Users;

public class OrdersList extends AppCompatActivity {


    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference requests;
    FirebaseRecyclerAdapter<Request,OrderViewHolder> recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);
        recyclerView = (RecyclerView) findViewById(R.id.ordersList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrdersList.this);
        recyclerView.setLayoutManager(layoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        requests = firebaseDatabase.getReference("Request");

        loadOrder(CoreData.users.getPhone());
    }

    private void loadOrder(String phone) {

        recyclerAdapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(Request.class,R.layout.order_item,OrderViewHolder.class,requests.orderByChild("phone").equalTo(phone)) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {

                viewHolder.id.setText(recyclerAdapter.getRef(position).getKey());
                viewHolder.status.setText(convertCodeToStatus(model.getStatus()));
                viewHolder.phone.setText(model.getPhone());
                viewHolder.address.setText(model.getAddress());
            }
        };
        recyclerView.setAdapter(recyclerAdapter);
    }

    private String convertCodeToStatus(String status) {
        if (status.equals("0")){
            return "Placed";
        }else if (status.equals("2")){
            return "On my Way";
        }else
            return "Shipped";
    }

}
