package app.com.abisolacrowdfunding.ui.allcompaigns.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.databinding.ActivityAllCompaingsBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.allcompaigns.adapter.allcompaigns;
import app.com.abisolacrowdfunding.ui.allcompaigns.model.AllComaignsResponse;
import app.com.abisolacrowdfunding.ui.supportForm.ui.SupportActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCompaingsActivity extends AppCompatActivity {
    ActivityAllCompaingsBinding binding;
    List<AllComaignsResponse> list;
    allcompaigns adapter;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllCompaingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("loginUser", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id", 99);
        getCompaigns(userId);
    }

    public void getCompaigns(int id) {
        ApiService apiService = APIClient.getClient().create(ApiService.class);
        apiService.allCompaings(id).enqueue(new Callback<List<AllComaignsResponse>>() {
            @Override
            public void onResponse(Call<List<AllComaignsResponse>> call, Response<List<AllComaignsResponse>> response) {
                if (response.isSuccessful()) {
                    if(response.body()==null){
                        binding.tvStatus.setVisibility(View.VISIBLE);
                    }
                    if (response.body() != null) {
                        list = response.body();
                        Log.d("list",response.body().toString());
                        binding.recAllCompaigns.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new allcompaigns(getApplicationContext(), list);
                        binding.recAllCompaigns.setAdapter(adapter);
                        adapter.setOnItemClick(new allcompaigns.OnitemClickListener() {
                            @Override
                            public void OnItemClick(int position) {


                            }

                            @Override
                            public void onaddclick(int position) {
                                Intent i = new Intent(AllCompaingsActivity.this, SupportActivity.class);
                                i.putExtra("Compaign_id", list.get(position).getCompaign_id());
                                i.putExtra("userId", userId);
                                startActivity(i);
                                finish();
                            }
                        });
                    }
                } else {
                    Toast.makeText(AllCompaingsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AllComaignsResponse>> call, Throwable t) {
                Toast.makeText(AllCompaingsActivity.this, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });
      ;
    }

}