package com.noumanch.eaton;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.noumanch.eaton.models.Users;

public class SignUp extends AppCompatActivity {


    private EditText phoneEditText,passwordEditTex,nameEditText;
    private Button signUpButton;
    FirebaseDatabase database;
    DatabaseReference usersTable;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = FirebaseDatabase.getInstance();
        usersTable = database.getReference("Users");



         phoneEditText   = (EditText) findViewById(R.id.phoneNmr);
        passwordEditTex = (EditText) findViewById(R.id.password);
        nameEditText    = (EditText) findViewById(R.id.name);
        signUpButton    = (Button) findViewById(R.id.signUpBtn);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.show();

                /*we work here */
                usersTable.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        progressDialog.dismiss();
                        if (dataSnapshot.child(phoneEditText.getText().toString()).exists()) {

                            Toast.makeText(SignUp.this, "Number already register", Toast.LENGTH_SHORT).show();
                        }else{
                            Users users = new Users(nameEditText.getText().toString(),passwordEditTex.getText().toString(),phoneEditText.getText().toString());
                            usersTable.child(phoneEditText.getText().toString()).setValue(users);
                            Toast.makeText(SignUp.this, "Signup Sucessfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
    }
}
