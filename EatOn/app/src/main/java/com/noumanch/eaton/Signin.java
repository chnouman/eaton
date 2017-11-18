package com.noumanch.eaton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.noumanch.eaton.common.CoreData;
import com.noumanch.eaton.models.Users;

public class Signin extends AppCompatActivity {



    FirebaseDatabase firebaseDatabase ;

    private EditText phoneEditText,passwordEditTex;
    private Button    signinButton;
    private DatabaseReference usersTable;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        phoneEditText   = (EditText) findViewById(R.id.phoneNmr);
        passwordEditTex = (EditText) findViewById(R.id.password);
        signinButton    = (Button) findViewById(R.id.signinBtn);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        usersTable = firebaseDatabase.getReference("Users");
        signinButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            progressDialog = new ProgressDialog(Signin.this);
            progressDialog.show();
            usersTable.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    progressDialog.dismiss();
                    if (dataSnapshot.child(phoneEditText.getText().toString()).exists()) {

                        Users users  = dataSnapshot.child(phoneEditText.getText().toString()).getValue(Users.class);
                        if (users.getPassword().equals(passwordEditTex.getText().toString())) {

                            Toast.makeText(Signin.this, "Signin Sucessfully", Toast.LENGTH_SHORT).show();

                            Intent dashboardIntent = new Intent(Signin.this,Dashboard.class);
                            CoreData.users = users;

                            startActivity(dashboardIntent);

                        }else{
                            Toast.makeText(Signin.this, "Password is not correct", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(Signin.this, "Phone nmr doesn't exist", Toast.LENGTH_SHORT).show();
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
