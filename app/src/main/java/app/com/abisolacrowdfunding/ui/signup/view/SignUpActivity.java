package app.com.abisolacrowdfunding.ui.signup.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
                    Toast.makeText(SignUpActivity.this, "please fill all data", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(SignUpActivity.this, "Sign Up Succesfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, response.body().message+"", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t + "", Toast.LENGTH_SHORT).show();
                binding.progressBar2.setVisibility(View.GONE);

            }
        });
    }
    //this function is used to check whether edit text is empty or not
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }


}