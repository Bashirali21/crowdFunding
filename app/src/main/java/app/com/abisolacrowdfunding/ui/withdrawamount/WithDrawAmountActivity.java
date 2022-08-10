package app.com.abisolacrowdfunding.ui.withdrawamount;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.databinding.ActivityWithDrawAmountBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.HomePage.HomePageActivity;
import app.com.abisolacrowdfunding.ui.createcompaign.view.CreateCompaignActivity;
import app.com.abisolacrowdfunding.ui.myCompaigns.ui.MyCompaignsActivity;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
import app.com.abisolacrowdfunding.ui.withdrawamount.model.WithDrawlRequestData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithDrawAmountActivity extends AppCompatActivity {
    int cid;
    int uid;
    int balance;
    ActivityWithDrawAmountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWithDrawAmountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        cid = bundle.getInt("cid", 0);//for getting cid from previous activity
        uid = bundle.getInt("uid", 0);//for getting cid from previous activity
        balance = bundle.getInt("amount", 0);
        onClicks();
    }

    public void onClicks() {
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmpty(binding.EtSupportAmount) || isEmpty(binding.description)) {
                    showMessage("please fill all the form");

                } else if (check()) {
                    showMessage("enter correct amount");
                } else {
                    ApiService apiService = APIClient.getClient().create(ApiService.class);//for calling api
                    WithDrawlRequestData data = new WithDrawlRequestData();
                    data.withdrawl_amount = Integer.parseInt(binding.EtSupportAmount.getText().toString());
                    data.withdrawl_compaign_id = cid;
                    data.withdrawl_user_id = uid;
                    data.withdrawl_discription = binding.description.getText().toString();
                    apiService.withDrawAmount(data).enqueue(new Callback<SignUpResponse>() {
                        @Override
                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body().isSuccessfull) {
                                    ShowDialog();
                                } else {
                                    Toast.makeText(WithDrawAmountActivity.this, response.body().message + "", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(WithDrawAmountActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpResponse> call, Throwable t) {
                            Toast.makeText(WithDrawAmountActivity.this, t.getMessage() + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public void ShowDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(WithDrawAmountActivity.this);
        builder1.setMessage("Successfully Added");
        builder1.setCancelable(false);
        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(WithDrawAmountActivity.this, MyCompaignsActivity.class));
                        finish();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public boolean check() {
        int chk = balance - Integer.parseInt(binding.EtSupportAmount.getText().toString());
        if (chk < 0) {
            return true;
        } else {
            return false;
        }
    }
}