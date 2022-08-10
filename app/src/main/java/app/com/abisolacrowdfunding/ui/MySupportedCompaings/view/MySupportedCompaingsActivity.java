package app.com.abisolacrowdfunding.ui.MySupportedCompaings.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import app.com.abisolacrowdfunding.databinding.ActivityMySupportedCompaingsBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.HomePage.HomePageActivity;
import app.com.abisolacrowdfunding.ui.MySupportedCompaings.adapter.MySupportedCompaigns;
import app.com.abisolacrowdfunding.ui.MySupportedCompaings.model.MySuppotedResponse;
import app.com.abisolacrowdfunding.ui.RefundSide.RefundActivity;
import app.com.abisolacrowdfunding.ui.SupportedCompaings.adapter.AllSupportCompaings;
import app.com.abisolacrowdfunding.ui.SupportedCompaings.ui.SupportedCompaingsActivity;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
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
                if (response.isSuccessful()) {
                    if (response.body().size() == 0) {
                        binding.textView6.setVisibility(View.VISIBLE);
                    } else {
                        list = response.body();
                        binding.rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new MySupportedCompaigns(getApplicationContext(), list);
                        binding.rec.setAdapter(adapter);
                        adapter.setOnItemClick(new MySupportedCompaigns.OnitemClickListener() {
                            @Override
                            public void onAccept(int position) {
                                sendRefundRequest(list.get(position).getDonationId());
                            }

                        });
                    }
                } else {
                    showMessage("server error");
                }
            }

            @Override
            public void onFailure(Call<List<MySuppotedResponse>> call, Throwable t) {
                showMessage("server error");
            }
        });

    }

    public void sendRefundRequest(int donationId) {
        ApiService apiService = APIClient.getClient().create(ApiService.class);//for calling api
        apiService.refund(userId, donationId).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().isSuccessfull) {
                        ShowDialog();

                    } else {
                        showMessage(response.body().message + "");
                    }

                } else {
                    showMessage("Server Error");
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(MySupportedCompaingsActivity.this, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void ShowDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MySupportedCompaingsActivity.this);
        builder1.setMessage("Succesfully Refunded");
        builder1.setCancelable(false);
        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(MySupportedCompaingsActivity.this, HomePageActivity.class));
                        finish();

                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
