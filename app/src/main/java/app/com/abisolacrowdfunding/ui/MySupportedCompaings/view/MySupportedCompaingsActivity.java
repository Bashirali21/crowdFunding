package app.com.abisolacrowdfunding.ui.MySupportedCompaings.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.com.abisolacrowdfunding.databinding.ActivityMySupportedCompaingsBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.MySupportedCompaings.adapter.MySupportedCompaigns;
import app.com.abisolacrowdfunding.ui.MySupportedCompaings.model.MySuppotedResponse;
import app.com.abisolacrowdfunding.ui.RefundSide.RefundActivity;
import app.com.abisolacrowdfunding.ui.SupportedCompaings.adapter.AllSupportCompaings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySupportedCompaingsActivity extends AppCompatActivity {
    ActivityMySupportedCompaingsBinding binding;
    ApiService apiService;
    int userId;
    MySupportedCompaigns adapter;
    List<MySuppotedResponse> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMySupportedCompaingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiService = APIClient.getClient().create(ApiService.class);
        SharedPreferences sharedPreferences = getSharedPreferences("loginUser", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id", 99);//for gettting user id of currently login user
        list = new ArrayList<>();
        getData();
    }

    public void getData() {
        apiService.MySupportedCompaings(userId).enqueue(new Callback<List<MySuppotedResponse>>() {
            @Override
            public void onResponse(Call<List<MySuppotedResponse>> call, Response<List<MySuppotedResponse>> response) {
                list = response.body();
                binding.rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new MySupportedCompaigns(getApplicationContext(), list);
                binding.rec.setAdapter(adapter);
                adapter.setOnItemClick(new MySupportedCompaigns.OnitemClickListener() {
                    @Override
                    public void onAccept(int position) {
                        Intent i=new Intent(MySupportedCompaingsActivity.this, RefundActivity.class);
                        i.putExtra("userId",userId);
                        i.putExtra("compaignId",list.get(position).getCompaignId());
                        i.putExtra("donationId",list.get(position).getDonationId());
                        startActivity(i);
                    }

                });
            }
            @Override
            public void onFailure(Call<List<MySuppotedResponse>> call, Throwable t) {

            }
        });

    }
}