package app.com.abisolacrowdfunding.ui.myCompaigns.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.ui.myCompaigns.model.MyComaignsResponse;

public class Mycompaigns extends RecyclerView.Adapter<Mycompaigns.View_Holder> {
    private Mycompaigns.OnitemClickListener mListener;

    public interface OnitemClickListener {
        void onWithDrawClick(int position);

    }

    public void setOnItemClick(Mycompaigns.OnitemClickListener listener) {
        mListener = listener;
    }

    LayoutInflater layoutInflater;
    List<MyComaignsResponse> users;


    public Mycompaigns(Context ctx, List<MyComaignsResponse> users) {
        this.layoutInflater = LayoutInflater.from(ctx);
        this.users = users;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_my_compaings, parent, false);
        return new View_Holder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        MyComaignsResponse currentItem = users.get(position);
        holder.title.setText(users.get(position).getCompaign_title());
        holder.tvDonation.setText(users.get(position).getAmount()+"");
        holder.progressBar.setProgress((int) users.get(position).getPercentage());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class View_Holder extends RecyclerView.ViewHolder {
        TextView title, tvDonation;
        Button withDraw;
        ProgressBar progressBar;

        public View_Holder(@NonNull View itemView, final Mycompaigns.OnitemClickListener listener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvMyCompaignTitle);
            tvDonation = (TextView) itemView.findViewById(R.id.tvTotalDonation);
            withDraw=itemView.findViewById(R.id.btnWithDraw);
            progressBar=itemView.findViewById(R.id.progressBar);
            withDraw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onWithDrawClick(position);
                        }
                    }
                }
            });
        }
    }
}


