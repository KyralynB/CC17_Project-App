package com.example.mantradashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTransaction extends AppCompatActivity {

    String[] type = {"In", "Out"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    //Variables
    TextInputLayout regTransType, regQuantity, regPrice, regDeliveryDate, regExpirationDate;
    Button regSubmit, callAddItems;

    //Firebase
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        //Hooks
        regTransType = findViewById(R.id.reg_trans_type);
        regQuantity = findViewById(R.id.reg_trans_quantity);
        regPrice = findViewById(R.id.reg_trans_price);
        regDeliveryDate = findViewById(R.id.reg_trans_del_date);
        regExpirationDate = findViewById(R.id.reg_trans_exp_date);


        //Buttons
        regSubmit = findViewById(R.id.reg_add_trans);
        callAddItems = findViewById(R.id.call_add_item);

        //Dropdown
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_role, type);
        autoCompleteTextView.setAdapter(adapterItems);

        //Action; dropdown items for roles
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
            }
        });

        //Action; Add Transaction
        regSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Transactions");

                submitTrans(v);
            }
        });

        //Action; Go To Add Items
        callAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTransaction.this, AddItem.class);
                startActivity(intent);

            }
        });
    }

    public void submitTrans(View v) {

        //Get all the values
        String transtype = regTransType.getEditText().getText().toString();
        String quantity = regQuantity.getEditText().getText().toString();
        String price = regPrice.getEditText().getText().toString();
        String deldate = regDeliveryDate.getEditText().getText().toString();
        String expdate = regExpirationDate.getEditText().getText().toString();

        //Registration excecution
        TransactionHelperClass helperClass = new TransactionHelperClass(transtype, quantity, price, deldate, expdate);
        reference.child(quantity).setValue(helperClass);

    }
}