package app.com.abisolacrowdfunding.ui.SupportedCompaings.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.ui.SupportedCompaings.mode.SupportedResponse;


public class AllSupportCompaings extends RecyclerView.Adapter<AllSupportCompaings.View_Holder> {
    private AllSupportCompaings.OnitemClickListener mListener;

    public interface OnitemClickListener {
        void onAccept(int position);

        void onReject(int position);

    }

    public void setOnItemClick(AllSupportCompaings.OnitemClickListener listener) {
        mListener = listener;
    }

    LayoutInflater layoutInflater;
    List<SupportedResponse> users;


    public AllSupportCompaings(Context ctx, List<SupportedResponse> users) {
        this.layoutInflater = LayoutInflater.from(ctx);
        this.users = users;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_supported_compaings, parent, false);
        return new View_Holder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        SupportedResponse currentItem = users.get(position);
        holder.title.setText(users.get(position).getCompaign_title());
        holder.amount.setText(users.get(position).getWithdrawl_amount()+"");
        holder.description.setText(users.get(position).getwithdrawl_discription());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class View_Holder extends RecyclerView.ViewHolder {
        TextView title, amount,description;
        Button accept, reject;

        public View_Holder(@NonNull View itemView, final AllSupportCompaings.OnitemClickListener listener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvSupportedCompaignTitle);
            amount = (TextView) itemView.findViewById(R.id.tvRequestedAmount);
            description=itemView.findViewById(R.id.tvDescriptionRequest);
            accept = itemView.findViewById(R.id.btnAccept);
            reject = itemView.findViewById(R.id.btnReject);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {

                            listener.onAccept(position);
                        }
                    }
                }
            });
            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {

                            listener.onReject(position);
                        }
                    }
                }
            });

        }
    }
}


