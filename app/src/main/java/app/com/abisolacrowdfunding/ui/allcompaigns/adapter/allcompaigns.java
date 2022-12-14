package app.com.abisolacrowdfunding.ui.allcompaigns.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;
import java.util.Random;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.ui.allcompaigns.model.AllComaignsResponse;
//adapter is a class which we used to show list of data for example this adapter is used to show all the compaings in the project
public class allcompaigns extends RecyclerView.Adapter<allcompaigns.View_Holder> {
    private allcompaigns.OnitemClickListener mListener;

    public interface OnitemClickListener {
        void OnItemClick(int position);//

        void onaddclick(int position);

    }

    public void setOnItemClick(allcompaigns.OnitemClickListener listener) {
        mListener = listener;
    }

    LayoutInflater layoutInflater;
    List<AllComaignsResponse> users;


    public allcompaigns(Context ctx, List<AllComaignsResponse> users) {
        this.layoutInflater = LayoutInflater.from(ctx);
        this.users = users;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_all_compaings, parent, false);//here we define what view is our adapter showing here we are showing row_all_compaings view which you can see in res->layout
        return new View_Holder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        AllComaignsResponse currentItem = users.get(position);
        holder.title.setText(users.get(position).getCompaign_title());//here we are defining our data what we have to show it is coming from tha api
        holder.description.setText(users.get(position).getCompaign_description());
        holder.createdBy.setText(users.get(position).getUser_fname());
        holder.date.setText(users.get(position).getDate());


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class View_Holder extends RecyclerView.ViewHolder {
        TextView title, description, createdBy, support,date;

        public View_Holder(@NonNull View itemView, final allcompaigns.OnitemClickListener listener) {
            super(itemView);
            //here we are initializing our components that were in the roww_all_views
            title = (TextView) itemView.findViewById(R.id.tvCompaignTitle);
            description = (TextView) itemView.findViewById(R.id.tvDescription);
            createdBy = (TextView) itemView.findViewById(R.id.tvCompaignCreaterName);
            support = (TextView) itemView.findViewById(R.id.tvSupport);
            date = (TextView) itemView.findViewById(R.id.tvDate);
            support.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {

                            listener.onaddclick(position);
                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {

                            listener.OnItemClick(position);
                        }
                    }
                }
            });

        }
    }
}


