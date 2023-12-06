package com.example.mantradashboard;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

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

    TextView statusTV;

    @Override
    protected void onBindViewHolder(@NonNull transactionViewHolder holder, int position, @NonNull TransactionHelperClass model) {
        holder.name.setText(model.getItemName());
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

        holder.btnApproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusTV = holder.itemView.findViewById(R.id.item_status);
                String statusNew = "Approved";

                Map<String,Object> map = new HashMap<>();
                map.put("status",statusNew);

                // Update the UI
                statusTV.setText(statusNew);
                statusTV.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.status.getContext(), R.color.light_green))); // Use your light_green color resource
                statusTV.setTextColor(ContextCompat.getColor(holder.status.getContext(), R.color.green)); // Use your green color resource
            }
        });

        holder.btnDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusTV = holder.itemView.findViewById(R.id.item_status);
                String statusNew = "Denied";

                Map<String,Object> map = new HashMap<>();
                map.put("status",statusNew);

                // Update the UI
                statusTV.setText(statusNew);
                statusTV.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.status.getContext(), R.color.light_pink))); // Use your light_green color resource
                statusTV.setTextColor(ContextCompat.getColor(holder.status.getContext(), R.color.red)); // Use your green color resource
            }
        });
    }

    @NonNull
    @Override
    public transactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_dynamic_request,parent,false);
        return new transactionViewHolder(view);
    }

    class transactionViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name, transtype, quantity, price, status;

        Button btnApproved, btnDeny;
        public transactionViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.item_name);
            img = (CircleImageView)itemView.findViewById(R.id.item_img);
            transtype = (TextView)itemView.findViewById(R.id.item_transtype);
            quantity = (TextView)itemView.findViewById(R.id.item_quantity);
            price = (TextView)itemView.findViewById(R.id.item_price);
            status = (TextView)itemView.findViewById(R.id.item_status);


            btnApproved = (Button)itemView.findViewById(R.id.btn_approved);
            btnDeny = (Button)itemView.findViewById(R.id.btn_deny);
        }
    }
}
