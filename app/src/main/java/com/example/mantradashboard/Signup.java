package com.example.mantradashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    //dropdown items
    String[] role = {"admin", "pharmacist", "staff", "doctor"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    //Variables
    TextInputLayout regFirstName, regLastName, regEmail, regRole, regPass, regContNum, regAdd;
    Button regSignUp, callLogIn;

    //Firebase
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
        regRole = findViewById(R.id.reg_role);
        regPass = findViewById(R.id.reg_password);
        regContNum = findViewById(R.id.reg_contact_num);
        regAdd = findViewById(R.id.reg_address);

        //Buttons
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        regSignUp = findViewById(R.id.reg_signup);
        callLogIn = findViewById(R.id.login_screen);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_role, role);
        autoCompleteTextView.setAdapter(adapterItems);

        //Action; dropdown items for roles
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
            }
        });

        //Action; User Register/Sign up
        regSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                //Get all the values
                String fname = regFirstName.getEditText().getText().toString();
                String lname = regLastName.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String role = regRole.getEditText().getText().toString();
                String password = regPass.getEditText().getText().toString();
                String contact_num = regContNum.getEditText().getText().toString();
                String address = regAdd.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(fname, lname, email, role, password, contact_num, address);
                reference.child(contact_num).setValue(helperClass);
            }
        });

        //Action; Go back to Login
        callLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
    }
}