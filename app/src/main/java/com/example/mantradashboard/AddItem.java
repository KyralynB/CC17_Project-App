package com.example.mantradashboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItem extends AppCompatActivity {

    String[] type = {"Medicine", "Tools", "Equipment"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    AdapterItemInventory adapter;

    //Variables
    TextInputLayout regItemName, reQr, regAlert, regItemType, regUnit, regQuantity, regDesc;
    Button regSubmit, btnCancel;

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
        regQuantity = findViewById(R.id.reg_item_quantity);
        regDesc = findViewById(R.id.reg_desc);


        //Buttons
        regSubmit = findViewById(R.id.reg_add_item);
        btnCancel = findViewById(R.id.btn_cancel);

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
                Toast.makeText(AddItem.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });

        //Action; Go To Add Items
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddItem.this);
                builder.setTitle("Exit Without Saving");
                builder.setMessage("Are you sure you want to cancel? Data inputs will be discarded.");

                builder.setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AddItem.this, "Item Discarded", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AddItem.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
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
        String Quantity = regQuantity.getEditText().getText().toString();
        String Desc = regDesc.getEditText().getText().toString();

        //Registration excecution
        ItemHelperClass helperClass = new ItemHelperClass(itemName, Qr, Alert, itemType, Unit, Quantity, Desc);
        reference.child(Qr).setValue(helperClass);
        finish();


    }
}