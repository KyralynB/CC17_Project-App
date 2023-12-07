package com.example.mantradashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTransaction extends AppCompatActivity {

    private String[] type = {"In", "Out"};
    private ArrayAdapter<String> adapterItems;
    AutoCompleteTextView autoCompleteTextView;

    private TextInputLayout regTransType, regQuantity, regPrice, regDeliveryDate, regExpirationDate;
    private TextView ItemNameTV;
    private Button regSubmit, btnCancel;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);  // Replace with your layout resource

        // Initialize views
        ItemNameTV = findViewById(R.id.item_name);
        regTransType = findViewById(R.id.reg_trans_type);
        regQuantity = findViewById(R.id.reg_trans_quantity);
        regPrice = findViewById(R.id.reg_trans_price);
        regDeliveryDate = findViewById(R.id.reg_trans_del_date);
        regExpirationDate = findViewById(R.id.reg_trans_exp_date);

        regSubmit = findViewById(R.id.btn_add_trans);
        btnCancel = findViewById(R.id.btn_cancel);

        //getExtra from MainActivity
        Intent intent = getIntent();
        String receivedData = intent.getStringExtra("scanned_qr_code");
        ItemNameTV.setText(receivedData);

        //Dropdown
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        typeDropDown();

        // Firebase
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Transactions");

        // Action; Add Transaction
        regSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTrans();
            }
        });

        // Action; Go To Add Items
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void submitTrans() {
        // Get all the values
        String itemName = ItemNameTV.getText().toString();
        String transtype = regTransType.getEditText().getText().toString();
        String quantity = regQuantity.getEditText().getText().toString();
        String price = regPrice.getEditText().getText().toString();
        String deldate = regDeliveryDate.getEditText().getText().toString();
        String expdate = regExpirationDate.getEditText().getText().toString();
        String status = "Pending";

        // Registration execution
        TransactionHelperClass helperClass = new TransactionHelperClass(itemName, transtype, quantity, price, deldate, expdate, status);
        reference.child(quantity).setValue(helperClass);

        // Display a message
        Toast.makeText(this, "Transaction added successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void typeDropDown() {
         adapterItems = new ArrayAdapter<String>(regTransType.getContext(),R.layout.list_role,type);
                autoCompleteTextView.setAdapter(adapterItems);

                //Action; dropdown items for roles
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String item = adapterView.getItemAtPosition(position).toString();
                    }
                });
    }
}
