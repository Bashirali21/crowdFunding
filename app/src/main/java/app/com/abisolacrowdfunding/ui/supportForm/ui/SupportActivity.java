package app.com.abisolacrowdfunding.ui.supportForm.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.databinding.ActivitySupportBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.HomePage.HomePageActivity;
import app.com.abisolacrowdfunding.ui.createcompaign.view.CreateCompaignActivity;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
import app.com.abisolacrowdfunding.ui.supportForm.Model.DonationFormEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupportActivity extends AppCompatActivity {
    ActivitySupportBinding binding;
    int compaignId;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras= getIntent ().getExtras ();
        compaignId=extras.getInt("Compaign_id",9);//for gettting compaign id from previous
        userId=extras.getInt("userId",9);//for gettting user id from previous
        onClickts();

    }
    //for managing onCLicks
    public  void onClickts(){
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(binding.EtSupportAmount)){
                    Toast.makeText(SupportActivity.this, "please enter amount", Toast.LENGTH_SHORT).show();
                }
                else{
                    ApiService apiService = APIClient.getClient().create(ApiService.class);
                    apiService.addDonation(new DonationFormEntity(compaignId,userId,Integer.parseInt(binding.EtSupportAmount.getText().toString()))).enqueue(new Callback<SignUpResponse>() {
                        @Override
                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                            if(response.isSuccessful()){
                                if(response.body().isSuccessfull){
                                    ShowDialog();
                                }
                            }
                            else{
                                Toast.makeText(SupportActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpResponse> call, Throwable t) {
                            Toast.makeText(SupportActivity.this, t.getMessage()+"", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    //this function is used to check whether edit text is empty or not
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    //for showing dialogs
    public void ShowDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(SupportActivity.this);
        builder1.setMessage("Successfully Added");
        builder1.setCancelable(false);
        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(SupportActivity.this, HomePageActivity.class));
                        finish();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}