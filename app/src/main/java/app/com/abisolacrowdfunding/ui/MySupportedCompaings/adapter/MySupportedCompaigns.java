package app.com.abisolacrowdfunding.ui.MySupportedCompaings.adapter;


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
import app.com.abisolacrowdfunding.ui.MySupportedCompaings.model.MySuppotedResponse;



public class MySupportedCompaigns extends RecyclerView.Adapter<MySupportedCompaigns.View_Holder> {
    private MySupportedCompaigns.OnitemClickListener mListener;

    public interface OnitemClickListener {
        void onAccept(int position);



    }

    public void setOnItemClick(MySupportedCompaigns.OnitemClickListener listener) {
        mListener = listener;
    }

    LayoutInflater layoutInflater;
    List<MySuppotedResponse> users;


    public MySupportedCompaigns(Context ctx, List<MySuppotedResponse> users) {
        this.layoutInflater = LayoutInflater.from(ctx);
        this.users = users;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_all_my_supported_compaigns, parent, false);
        return new View_Holder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        MySuppotedResponse currentItem = users.get(position);
        holder.title.setText(users.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class View_Holder extends RecyclerView.ViewHolder {
        TextView title;
        Button refund;

        public View_Holder(@NonNull View itemView, final MySupportedCompaigns.OnitemClickListener listener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvMySupportedCompaignTitle);
            refund = itemView.findViewById(R.id.btnRefund);
            refund.setOnClickListener(new View.OnClickListener() {
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


        }
    }
}


