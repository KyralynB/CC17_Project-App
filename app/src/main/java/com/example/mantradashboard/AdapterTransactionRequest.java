package com.example.mantradashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterTransactionRequest extends FirebaseRecyclerAdapter<TransactionHelperClass,AdapterTransactionRequest.transactionViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterTransactionRequest(@NonNull FirebaseRecyclerOptions<TransactionHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull transactionViewHolder holder, int position, @NonNull TransactionHelperClass model) {
//        holder.name.setText(model.());
        holder.transtype.setText(model.getTranstype());
        holder.quantity.setText(model.getQuantity());
        holder.price.setText(model.getPrice());
        holder.status.setText(model.getStatus());

/*        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .circleCrop()
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);*/
    }

    @NonNull
    @Override
    public transactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_dynamic_request,parent,false);
        return new transactionViewHolder(view);
    }

    class transactionViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView transtype, quantity, price, status;

        public transactionViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.item_img);
            transtype = (TextView)itemView.findViewById(R.id.item_transtype);
            quantity = (TextView)itemView.findViewById(R.id.item_quantity);
            price = (TextView)itemView.findViewById(R.id.item_price);
            status = (TextView)itemView.findViewById(R.id.item_status);
        }
    }
}
