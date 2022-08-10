package app.com.abisolacrowdfunding.ui.myCompaigns.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.databinding.ActivityAllCompaingsBinding;
import app.com.abisolacrowdfunding.databinding.ActivityMyCompaignsBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.HomePage.HomePageActivity;
import app.com.abisolacrowdfunding.ui.allcompaigns.adapter.allcompaigns;
import app.com.abisolacrowdfunding.ui.allcompaigns.model.AllComaignsResponse;
import app.com.abisolacrowdfunding.ui.createcompaign.view.CreateCompaignActivity;
import app.com.abisolacrowdfunding.ui.myCompaigns.adapter.Mycompaigns;
import app.com.abisolacrowdfunding.ui.myCompaigns.model.MyComaignsResponse;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
import app.com.abisolacrowdfunding.ui.withdrawamount.WithDrawAmountActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCompaignsActivity extends AppCompatActivity {
    ActivityMyCompaignsBinding binding;
    List<MyComaignsResponse> list;
    Mycompaigns adapter;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyCompaignsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("loginUser", MODE_PRIVATE);
        getCompaigns(sharedPreferences.getInt("id", 99));
    }

    //for getting compaigns
    public void getCompaigns(int id) {
        apiService = APIClient.getClient().create(ApiService.class);
        apiService.mYCompaings(id).enqueue(new Callback<List<MyComaignsResponse>>() {
            @Override
            public void onResponse(Call<List<MyComaignsResponse>> call, Response<List<MyComaignsResponse>> response) {
                if (response.isSuccessful()) {
                    if(response.body()==null){
                        binding.textView11.setVisibility(View.VISIBLE);
                    }
                    if (response.body() != null) {
                        list = response.body();
                        binding.recMyCompaings.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new Mycompaigns(getApplicationContext(), list);
                        binding.recMyCompaings.setAdapter(adapter);
                        adapter.setOnItemClick(new Mycompaigns.OnitemClickListener() {
                            @Override
                            public void onWithDrawClick(int position) {
                                Intent i = new Intent(MyCompaignsActivity.this, WithDrawAmountActivity.class);
                                i.putExtra("cid", list.get(position).getCompaign_id());//for sending compaings id to withdraw amount actitivity
                                i.putExtra("uid", id);
                                i.putExtra("amount", list.get(position).getAmount());
                                startActivity(i);
                            }
                        });
                    }
                } else {
                    Toast.makeText(MyCompaignsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MyComaignsResponse>> call, Throwable t) {
                Toast.makeText(MyCompaignsActivity.this, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }


}