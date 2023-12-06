package com.example.mantradashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    String[] type = {"Medicine", "Tools", "Equipment"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    AdapterItemInventory adapter;

    TextInputLayout ItemName, Qr, Alert, ItemType, Unit, Quantity, Desc;
    Button btnUpdate, btnCancel;
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ItemHelperClass model) {
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

                //Hooks
                View view = dialogPlus.getHolderView();
                ItemName = view.findViewById(R.id.edit_item_name);
                Qr = view.findViewById(R.id.edit_qr_code);
                Alert = view.findViewById(R.id.edit_alert);
                ItemType = view.findViewById(R.id.edit_item_type);
                Unit = view.findViewById(R.id.edit_unit);
                Quantity = view.findViewById(R.id.edit_item_quantity);
                Desc = view.findViewById(R.id.edit_desc);
                
                ShowAllItemData();

                btnUpdate = view.findViewById(R.id.btn_edit_item);

                //Dropdown
                autoCompleteTextView = view.findViewById(R.id.auto_complete_txt);

                adapterItems = new ArrayAdapter<String>(holder.type.getContext(),R.layout.list_role,type);
                autoCompleteTextView.setAdapter(adapterItems);

                //Action; dropdown items for roles
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String item = adapterView.getItemAtPosition(position).toString();
                    }
                });
/*
                ItemName.setText(model.getItemName());
                Qr.setText(model.getQrCode());
                Alert.setText(model.getLowLev());
                ItemType.setText(model.getItemType());
                Unit.setText(model.getUnit());
                Quantity.setText(model.getQuantity());
                Desc.setText(model.getDesc());*/

                dialogPlus.show();
            }

            private void ShowAllItemData() {

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
        Button btnedit, btndelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.item_img);
            name = (TextView)itemView.findViewById(R.id.item_name);
            type = (TextView)itemView.findViewById(R.id.item_type);
            quantity = (TextView)itemView.findViewById(R.id.item_quantity);
            unit = (TextView)itemView.findViewById(R.id.item_unit);

            btnedit = (Button) itemView.findViewById(R.id.btn_edit);
            btndelete = (Button) itemView.findViewById(R.id.btn_del);

        }
    }
}
