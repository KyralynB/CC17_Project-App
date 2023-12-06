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

public class FragmentInventoryRequest extends Fragment {

    Button callAddTrans;
    RecyclerView recyclerView;
    AdapterTransactionRequest adapterTransactionRequest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_request, container, false);

        recyclerView = view.findViewById(R.id.rv_inventory_request);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<TransactionHelperClass> options =
                new FirebaseRecyclerOptions.Builder<TransactionHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Transactions"), TransactionHelperClass.class)
                        .build();

        adapterTransactionRequest = new AdapterTransactionRequest(options);
        recyclerView.setAdapter(adapterTransactionRequest);

        callAddTrans = view.findViewById(R.id.btn_add_trans);

        callAddTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddTransaction.class));
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterTransactionRequest.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterTransactionRequest.stopListening();
    }
}