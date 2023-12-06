package com.example.mantradashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentInDoneReq extends Fragment {

    RecyclerView recyclerView;
    AdapterTransactionRequest adapterTransactionRequest;

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_done_request, container, false);

/*        recyclerView = view.findViewById(R.id.);*/
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<TransactionHelperClass> options =
                new FirebaseRecyclerOptions.Builder<TransactionHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Transactions"), TransactionHelperClass.class)
                        .build();

        adapterTransactionRequest = new AdapterTransactionRequest(options);
        recyclerView.setAdapter(adapterTransactionRequest);

        return view;
    }
}