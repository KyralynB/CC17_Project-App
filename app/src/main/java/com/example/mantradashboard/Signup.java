package com.example.mantradashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    //Variables
    TextInputLayout regFirstName, regLastName, regEmail, regPass, regContNum, regAdd;
    Button regSignUp, callLogIn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Hooks
        regFirstName = findViewById(R.id.reg_first_name);
        regLastName = findViewById(R.id.reg_last_name);
        regEmail = findViewById(R.id.reg_email);
        regPass = findViewById(R.id.reg_password);
        regContNum = findViewById(R.id.reg_contact_num);
        regAdd = findViewById(R.id.reg_address);

        regSignUp = findViewById(R.id.reg_signup);
        callLogIn = findViewById(R.id.login_screen);

        regSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                //Get all the values
                String fname = regFirstName.getEditText().getText().toString();
                String lname = regLastName.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String password = regPass.getEditText().getText().toString();
                String contact_num = regContNum.getEditText().getText().toString();
                String address = regAdd.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(fname, lname, email, password, contact_num, address);

                reference.setValue(helperClass);
            }
        });

        callLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
    }
}