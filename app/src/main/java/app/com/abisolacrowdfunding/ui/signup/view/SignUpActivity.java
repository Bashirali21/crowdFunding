package app.com.abisolacrowdfunding.ui.signup.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import app.com.abisolacrowdfunding.databinding.ActivitySignUpBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.HomePage.HomePageActivity;
import app.com.abisolacrowdfunding.ui.login.MainActivity;
import app.com.abisolacrowdfunding.ui.signup.model.SignUp;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onClicks();
    }

    public void onClicks() {
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmpty(binding.tvEnterFirstName) || isEmpty(binding.EtLastName)|| isEmpty(binding.EtEmail) || isEmpty(binding.EtAddress)||isEmpty(binding.EtPassword)) {//checking whether edit text is empty or not
                    ShowMessage("please fill all data");

                } else {

                    binding.EtPassword.getText().toString();
                    Register(new SignUp(1, binding.tvEnterFirstName.getText().toString(), binding.EtPassword.getText().toString(), binding.EtEmail.getText().toString(), binding.EtAddress.getText().toString(),binding.EtLastName.getText().toString()));
                }
            }
        });
    }
    //this function will get data from form and send it to api
    public void Register(SignUp data) {

        Log.d("dataSign",data+"");
        binding.progressBar2.setVisibility(View.VISIBLE);
        ApiService uploadService = APIClient.getClient().create(ApiService.class);//for calling api
        uploadService.Register(data).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                binding.progressBar2.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().isSuccessfull) {
                      ShowMessage("SignUp Succesfully");
                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            }
                        }, 1000);
                    }
                    else{
                        ShowMessage(response.body().message+"");
                    }
                } else {
                    ShowMessage("Something went wrong Server Error");
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                ShowMessage("Something went wrong Server Error");
                binding.progressBar2.setVisibility(View.GONE);

            }
        });
    }
    //this function is used to check whether edit text is empty or not
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    public  void ShowMessage(String message) {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}