package app.com.abisolacrowdfunding.ui.createcompaign.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.databinding.ActivityCreateCompaignBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.HomePage.HomePageActivity;
import app.com.abisolacrowdfunding.ui.createcompaign.Model.CompaignFormData;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCompaignActivity extends AppCompatActivity {
ActivityCreateCompaignBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateCompaignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onClicks();
    }
    public void onClicks(){
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(binding.EtTitle)||isEmpty(binding.description)){
                    Toast.makeText(CreateCompaignActivity.this, "please fill all the data", Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences sharedPreferences = getSharedPreferences("loginUser", MODE_PRIVATE);

                    CreateCompaign(sharedPreferences.getInt("id", 99));
                }
            }
        });
    }

    private void CreateCompaign(int id) {
        ApiService apiService = APIClient.getClient().create(ApiService.class);
        apiService.createCompaign(new CompaignFormData(binding.EtTitle.getText().toString(),id,binding.description.getText().toString())).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccessfull){
                        ShowDialog();

                    }
                    else{
                        Toast.makeText(CreateCompaignActivity.this, response.body().message, Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    public void ShowDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(CreateCompaignActivity.this);
        builder1.setMessage("Successfully Added");
        builder1.setCancelable(false);
        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       startActivity(new Intent(CreateCompaignActivity.this, HomePageActivity.class));
                       finish();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}