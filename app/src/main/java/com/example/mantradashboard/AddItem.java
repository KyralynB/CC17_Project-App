package com.example.mantradashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItem extends AppCompatActivity {

    String[] type = {"Vial", "Tablet", "Boxes"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    //Variables
    TextInputLayout regItemName, reQr, regAlert, regItemType, regUnit, regDesc;
    Button regSubmit, callAddTrans;

    //Firebase
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //Hooks
        regItemName = findViewById(R.id.reg_item_name);
        reQr = findViewById(R.id.reg_qr_code);
        regAlert = findViewById(R.id.reg_alert);
        regItemType = findViewById(R.id.reg_item_type);
        regUnit = findViewById(R.id.reg_unit);
        regDesc = findViewById(R.id.reg_desc);


        //Buttons
        regSubmit = findViewById(R.id.reg_add_item);
        callAddTrans = findViewById(R.id.call_add_trans);

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
                reference = rootNode.getReference("Items");

                submitItem(v);
            }
        });

        //Action; Go To Add Items
        callAddTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItem.this, AddTransaction.class);
                startActivity(intent);

            }
        });
    }

    public void submitItem(View v) {

        //Get all the values
        String itemName = regItemName.getEditText().getText().toString();
        String Qr = reQr.getEditText().getText().toString();
        String Alert = regAlert.getEditText().getText().toString();
        String itemType = regItemType.getEditText().getText().toString();
        String Unit = regUnit.getEditText().getText().toString();
        String Desc = regDesc.getEditText().getText().toString();

        //Registration excecution
        ItemHelperClass helperClass = new ItemHelperClass(itemName, Qr, Alert, itemType, Unit, Desc);
        reference.child(itemName).setValue(helperClass);

    }
}