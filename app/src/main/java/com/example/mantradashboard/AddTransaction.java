package com.example.mantradashboard;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTransaction {

    private String[] type = {"In", "Out"};
    private ArrayAdapter<String> adapterItems;

    private TextInputLayout regTransType, regQuantity, regPrice, regDeliveryDate, regExpirationDate;
    private TextView itemname;
    private Button regSubmit, btnCancel;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    private Context context;

    public AddTransaction(Context context, View rootView) {
        this.context = context;

        // Initialize views
        itemname = rootView.findViewById(R.id.item_name);
        regTransType = rootView.findViewById(R.id.reg_trans_type);
        regQuantity = rootView.findViewById(R.id.reg_trans_quantity);
        regPrice = rootView.findViewById(R.id.reg_trans_price);
        regDeliveryDate = rootView.findViewById(R.id.reg_trans_del_date);
        regExpirationDate = rootView.findViewById(R.id.reg_trans_exp_date);

        regSubmit = rootView.findViewById(R.id.btn_add_trans);
        btnCancel = rootView.findViewById(R.id.btn_cancel);


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
                Intent intent = new Intent(context, AddItem.class);
                context.startActivity(intent);
            }
        });
    }

    void submitTrans() {
        // Get all the values
        String itemname = regTransType.getEditText().getText().toString();
        String transtype = regTransType.getEditText().getText().toString();
        String quantity = regQuantity.getEditText().getText().toString();
        String price = regPrice.getEditText().getText().toString();
        String deldate = regDeliveryDate.getEditText().getText().toString();
        String expdate = regExpirationDate.getEditText().getText().toString();
        String status = "Pending";

        // Registration execution
        TransactionHelperClass helperClass = new TransactionHelperClass(itemname, transtype, quantity, price, deldate, expdate, status);
        reference.child(quantity).setValue(helperClass);

        // Display a message
        Toast.makeText(context, "Transaction added successfully", Toast.LENGTH_SHORT).show();
    }
}
