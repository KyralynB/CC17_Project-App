package com.example.mantradashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterItemInventory extends FirebaseRecyclerAdapter<ItemHelperClass, AdapterItemInventory.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterItemInventory(@NonNull FirebaseRecyclerOptions<ItemHelperClass> options) {
        super(options);
    }

    //Add Item instantiates
    String[] typeOfItem = {"Medicine", "Tools", "Equipment"};
    String[] typeOfTransaction = {"In", "Out"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    AdapterItemInventory adapter;

    //Button Update
    TextInputLayout ItemName, Qr, Alert, ItemType, Unit, Quantity, Desc;
    TextInputEditText EItemName, EQr, EAlert, EUnit, EQuantity, EDesc;
    MaterialAutoCompleteTextView EItemType;

    //Button Add Transaction
    TextView ItemNameTV;
    TextInputLayout TransType, TransQuantity, Price, DelDate, ExpDate;
    TextInputEditText ETransQuantity, EPrice, EDelDate, EExpDate;
    MaterialAutoCompleteTextView ETransType;


    Button btnUpdate, btnDelete, btnSubmit;


    //Variables
    TextInputLayout regTransType, regQuantity, regPrice, regDeliveryDate, regExpirationDate;
    Button regSubmit, btnCancel;
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull ItemHelperClass model) {
        holder.name.setText(model.getItemName());
        holder.type.setText(model.getItemType());
        holder.quantity.setText(model.getQuantity());
        holder.unit.setText(model.getUnit());

/*        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .circleCrop()
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);*/

        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_edit_item))
                        .setExpanded(true, 1500)
                        .create();

                //Complete Item Hooks
                View view = dialogPlus.getHolderView();
                ItemName = view.findViewById(R.id.edit_item_name);
                Qr = view.findViewById(R.id.edit_qr_code);
                Alert = view.findViewById(R.id.edit_alert);
                ItemType = view.findViewById(R.id.edit_item_type);
                Unit = view.findViewById(R.id.edit_unit);
                Quantity = view.findViewById(R.id.edit_item_quantity);
                Desc = view.findViewById(R.id.edit_desc);

                setToEditTextCompleteItems();
                showAllItemData();

                btnUpdate = view.findViewById(R.id.btn_edit_item);
                btnDelete = view.findViewById(R.id.btn_delete);

                //Dropdown
                autoCompleteTextView = view.findViewById(R.id.auto_complete_txt);
                String dropdowntype = "itemDD";
                typeDropDown(dropdowntype);

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("itemName",EItemName.getText().toString());
                        map.put("qrCode",EQr.getText().toString());
                        map.put("lowlevel",EAlert.getText().toString());
                        map.put("itemType",EItemType.getText().toString());
                        map.put("unit",EUnit.getText().toString());
                        map.put("quantity",EQuantity.getText().toString());
                        map.put("desc",EDesc.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Items")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(),"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Data Input Failed", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                                .setContentHolder(new ViewHolder(R.layout.activity_add_transaction))
                                .setExpanded(true, 1500)
                                .create();

                    }
                });
            }

            private void setToEditTextCompleteItems() {
                EItemName = (TextInputEditText)ItemName.getEditText();
                EQr = (TextInputEditText)Qr.getEditText();
                EAlert = (TextInputEditText)Alert.getEditText();
                EItemType = (MaterialAutoCompleteTextView)ItemType.getEditText();
                EUnit = (TextInputEditText)Unit.getEditText();
                EQuantity = (TextInputEditText)Quantity.getEditText();
                EDesc = (TextInputEditText)Desc.getEditText();
            }

            private void typeDropDown(String dropdownType) {

                switch (dropdownType) {
                    case "itemDD":
                        adapterItems = new ArrayAdapter<String>(holder.type.getContext(), R.layout.list_role, typeOfItem);
                        break;
                    case "transactionDD":
                        adapterItems = new ArrayAdapter<String>(holder.type.getContext(), R.layout.list_role, typeOfTransaction);
                        break;
                    // Add more cases as needed

                    default:
                        // Default case, handle it as needed
                        break;
                }

                autoCompleteTextView.setAdapter(adapterItems);

                // Action; dropdown items for roles
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String item = adapterView.getItemAtPosition(position).toString();
                        // Handle item selection if needed
                    }
                });

/*                adapterItems = new ArrayAdapter<String>(holder.type.getContext(),R.layout.list_role,typeOfItem);
                autoCompleteTextView.setAdapter(adapterItems);

                //Action; dropdown items for roles
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String item = adapterView.getItemAtPosition(position).toString();
                    }
                });*/
            }

            private void showAllItemData() {
                EItemName.setText(model.getItemName());
                EQr.setText(model.getQrCode());
                EAlert.setText(model.getLowLev());
                EItemType.setText(model.getItemType());
                EUnit.setText(model.getUnit());
                EQuantity.setText(model.getQuantity());
                EDesc.setText(model.getDesc());
            }
        });

        holder.btnAddTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_add_transaction))
                        .setExpanded(true, 1500)
                        .create();

                //Complete Item Hooks
                View view = dialogPlus.getHolderView();
                ItemNameTV = view.findViewById(R.id.edit_item_name);
                TransType = view.findViewById(R.id.reg_trans_type);
                TransQuantity = view.findViewById(R.id.reg_trans_quantity);
                Price = view.findViewById(R.id.reg_trans_price);
                DelDate = view.findViewById(R.id.reg_trans_del_date);
                ExpDate = view.findViewById(R.id.reg_trans_exp_date);

                setToEditTextCompleteItems();
                showAllItemData();

                btnSubmit = view.findViewById(R.id.btn_add_trans);

                //Dropdown
                autoCompleteTextView = view.findViewById(R.id.auto_complete_txt);
                String dropdowntype = "transactionDD";
                typeDropDown(dropdowntype);

                dialogPlus.show();

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("itemName",ItemNameTV.getText().toString());
                        map.put("ETransType",ETransType.getText().toString());
                        map.put("ETransQuantity",ETransQuantity.getText().toString());
                        map.put("EPrice",EPrice.getText().toString());
                        map.put("EDelDate",EDelDate.getText().toString());
                        map.put("EExpDate",EExpDate.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Transactions")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(),"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Data Input Failed", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }

            private void setToEditTextCompleteItems() {
                ETransType = (MaterialAutoCompleteTextView)TransType.getEditText();
                ETransQuantity = (TextInputEditText)TransQuantity.getEditText();
                EPrice = (TextInputEditText)Price.getEditText();
                EDelDate = (TextInputEditText)DelDate.getEditText();
                EExpDate = (TextInputEditText)ExpDate.getEditText();
            }

            private void typeDropDown(String dropdownType) {

                switch (dropdownType) {
                    case "itemDD":
                        adapterItems = new ArrayAdapter<String>(holder.type.getContext(), R.layout.list_role, typeOfItem);
                        break;
                    case "transactionDD":
                        adapterItems = new ArrayAdapter<String>(holder.type.getContext(), R.layout.list_role, typeOfTransaction);
                        break;
                    // Add more cases as needed

                    default:
                        // Default case, handle it as needed
                        break;
                }

                autoCompleteTextView.setAdapter(adapterItems);

                // Action; dropdown items for roles
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String item = adapterView.getItemAtPosition(position).toString();
                        // Handle item selection if needed
                    }
                });

/*                adapterItems = new ArrayAdapter<String>(holder.type.getContext(),R.layout.list_role,typeOfItem);
                autoCompleteTextView.setAdapter(adapterItems);

                //Action; dropdown items for roles
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String item = adapterView.getItemAtPosition(position).toString();
                    }
                });*/
            }

            private void showAllItemData() {
                ItemNameTV.setText(model.getItemName());
            }
        });
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_dynamic_items,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name, type, quantity, unit;
        Button btnedit, btnAddTrans;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.item_img);
            name = (TextView)itemView.findViewById(R.id.item_name);
            type = (TextView)itemView.findViewById(R.id.item_type);
            quantity = (TextView)itemView.findViewById(R.id.item_quantity);
            unit = (TextView)itemView.findViewById(R.id.item_unit);

            btnedit = (Button) itemView.findViewById(R.id.btn_edit);
            btnAddTrans = (Button) itemView.findViewById(R.id.btn_add_trans);

        }
    }
}
