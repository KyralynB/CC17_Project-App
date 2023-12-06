package com.example.mantradashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentItemInventory extends Fragment {

    Button callAddItem;
    RecyclerView recyclerView;
    AdapterItemInventory adapterItemInventory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_inventory, container, false);

        recyclerView = view.findViewById(R.id.rv_item_inventory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<ItemHelperClass> options =
                new FirebaseRecyclerOptions.Builder<ItemHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Items"), ItemHelperClass.class)
                        .build();

        adapterItemInventory = new AdapterItemInventory(options);
        recyclerView.setAdapter(adapterItemInventory);

        callAddItem = view.findViewById(R.id.btn_add_item);

        callAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddItem.class));
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterItemInventory.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterItemInventory.stopListening();
    }
}