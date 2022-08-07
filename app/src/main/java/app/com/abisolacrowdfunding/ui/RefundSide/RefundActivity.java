package app.com.abisolacrowdfunding.ui.RefundSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.databinding.ActivityRefundBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.HomePage.HomePageActivity;
import app.com.abisolacrowdfunding.ui.myCompaigns.ui.MyCompaignsActivity;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefundActivity extends AppCompatActivity {
int userId,compaignId,donationId;
ActivityRefundBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRefundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        userId=bundle.getInt("userId",0);//for getting cid from previous activity
        compaignId=bundle.getInt("compaignId",0);//for getting cid from previous activity
        donationId=bundle.getInt("donationId",0);//for getting cid from previous activity
        onClicks();
    }

    public void onClicks(){
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(binding.EtSupportAmount)||isEmpty(binding.description)){
                    Toast.makeText(RefundActivity.this, "please fill all the data", Toast.LENGTH_SHORT).show();

                }
                else {
                    ApiService apiService = APIClient.getClient().create(ApiService.class);//for calling api
                    apiService.refund(userId,donationId,compaignId, Integer.parseInt(binding.EtSupportAmount.getText().toString())).enqueue(new Callback<SignUpResponse>() {
                        @Override
                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                            if(response.isSuccessful()){
                                if(response.body().isSuccessfull){
                                    startActivity(new Intent(RefundActivity.this, HomePageActivity.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(RefundActivity.this, response.body().message+"", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else {
                                Toast.makeText(RefundActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpResponse> call, Throwable t) {
                            Toast.makeText(RefundActivity.this, t.getMessage()+"", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}