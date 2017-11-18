package com.noumanch.eaton;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.noumanch.eaton.adapter.CartAdapter;
import com.noumanch.eaton.common.CoreData;
import com.noumanch.eaton.database.Database;
import com.noumanch.eaton.models.Order;
import com.noumanch.eaton.models.Request;

import java.io.LineNumberReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {


    RecyclerView recyclerView;
    Button b ;

    FirebaseDatabase firebase;
    DatabaseReference requests;
    CartAdapter adapter;
    List<Order> orders = new ArrayList<>();
    TextView textPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);



        firebase  = FirebaseDatabase.getInstance();
        requests   = firebase.getReference("Request");

        recyclerView = (RecyclerView) findViewById(R.id.items);
        b            = (Button) findViewById(R.id.place_order);
        textPrice    = (TextView) findViewById(R.id.total_price);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Cart.this);
        recyclerView.setLayoutManager(layoutManager);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //add data to request table

                showAlertDialog();


            }
        });

        loadListFoods();



    }

    private void showAlertDialog() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(Cart.this);
        alBuilder.setTitle("One more Step");
        alBuilder.setMessage("Enter your Address");
        final EditText editText = new EditText(Cart.this);
        LinearLayout.LayoutParams  lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        editText.setLayoutParams(lp);
        alBuilder.setView(editText);
        alBuilder.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        alBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Log.wtf("Value test", "onClick: "+CoreData.users.getPhone() );
                Request request = new Request(CoreData.users.getPhone(),CoreData.users.getName(),
                        editText.getText().toString(),
                        textPrice.getText().toString(),
                        orders
                        ,"0");
                requests.child(System.currentTimeMillis()+"").setValue(request);
                new Database(Cart.this).cleanCart(null);
                Toast.makeText(Cart.this, "Order Placed Sucessfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        alBuilder.show();
    }

    private void loadListFoods() {

        orders= new Database(Cart.this).getCharts();
        adapter = new CartAdapter(Cart.this,orders);
        recyclerView.setAdapter(adapter);

        int total = 0;
        for(Order order:orders){

            total+= Integer.parseInt(order.getPrice())* Integer.parseInt(order.getQuantity());
        }
        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        textPrice.setText(fmt.format(total));

    }
}
