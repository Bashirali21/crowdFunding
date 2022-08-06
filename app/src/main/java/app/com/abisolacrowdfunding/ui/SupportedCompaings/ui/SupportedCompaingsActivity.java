package app.com.abisolacrowdfunding.ui.SupportedCompaings.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.databinding.ActivitySupportedCompaingsBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.HomePage.HomePageActivity;
import app.com.abisolacrowdfunding.ui.SupportedCompaings.adapter.AllSupportCompaings;
import app.com.abisolacrowdfunding.ui.SupportedCompaings.mode.SupportedResponse;
import app.com.abisolacrowdfunding.ui.myCompaigns.adapter.Mycompaigns;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupportedCompaingsActivity extends AppCompatActivity {
    ActivitySupportedCompaingsBinding binding;
    ApiService apiService;
    int userId;
    AllSupportCompaings adapter;
    List<SupportedResponse> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupportedCompaingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiService = APIClient.getClient().create(ApiService.class);
        SharedPreferences sharedPreferences = getSharedPreferences("loginUser", MODE_PRIVATE);
        userId = sharedPreferences.getInt("id", 99);//for gettting user id of currently login user
        list = new ArrayList<>();
        getData();

    }
//for getting data from api
    public void getData() {
        apiService.getSupportedCompaings(userId).enqueue(new Callback<List<SupportedResponse>>() {
            @Override
            public void onResponse(Call<List<SupportedResponse>> call, Response<List<SupportedResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        list = response.body();
                        binding.recSupportedCompaings.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter =new AllSupportCompaings(getApplicationContext(),list);
                        binding.recSupportedCompaings.setAdapter(adapter);
                        adapter.setOnItemClick(new AllSupportCompaings.OnitemClickListener() {
                            @Override
                            public void onAccept(int position) {
                                callApprovedFuntion(true, list.get(position).getdonation_reference());//
                            }

                            @Override
                            public void onReject(int position) {
                                callApprovedFuntion(false, list.get(position).getdonation_reference());
                            }
                        });
                    }
                }
                else{
                    errorMessage();
                }
            }

            @Override
            public void onFailure(Call<List<SupportedResponse>> call, Throwable t) {
                errorMessage();
            }
        });

    }

    public void errorMessage() {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }

    public void success() {
        Toast.makeText(this, "succesfull", Toast.LENGTH_SHORT).show();
    }
//for calling api approve function
    public void callApprovedFuntion(Boolean status, int donation_reference) {
        apiService.approveDisapproveCompaign(status, donation_reference).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccessfull) {
                        startActivity(new Intent(SupportedCompaingsActivity.this, HomePageActivity.class));
                        finish();
                    }

                }
                errorMessage();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                errorMessage();
            }
        });

    }
}