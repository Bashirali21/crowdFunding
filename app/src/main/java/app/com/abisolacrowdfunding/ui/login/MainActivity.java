package app.com.abisolacrowdfunding.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import app.com.abisolacrowdfunding.databinding.ActivityMainBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.HomePage.HomePageActivity;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
import app.com.abisolacrowdfunding.ui.signup.view.SignUpActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onCLicks();//onClicks function

    }
    //called when we click buttons on our login page
    public void onCLicks(){
        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));//for moving to signup page
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for cheking any field is empty or not
                if(isEmpty(binding.EtEmail)||isEmpty(binding.etPass)){
                    Toast.makeText(MainActivity.this, "please fill all the data", Toast.LENGTH_SHORT).show();
                }
                else{
                    Authenticate();
                }
            }
        });
    }

    //function for authentication
    public void Authenticate(){
        binding.progressBar3.setVisibility(View.VISIBLE);
        ApiService apiService = APIClient.getClient().create(ApiService.class);
        apiService.Login(binding.EtEmail.getText().toString(),binding.etPass.getText().toString()).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                binding.progressBar3.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    if(response.body().isSuccessfull){
                        //after the succesfull login we are just saving our user info in a session
                        SharedPreferences saveinfo=getSharedPreferences("loginUser",MODE_PRIVATE);//for saving user information on a sucessfull login
                        SharedPreferences.Editor edit=saveinfo.edit();
                        edit.putInt("id",response.body().user.UserId);
                        edit.putString("name",response.body().user.FirstName);
                        edit.apply();
                       ShowMessage("Login Succesfully");
                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                            }
                        }, 1000);

                    }
                    else{
                        ShowMessage(response.body().message+"");

                    }
                }else{
                    ShowMessage("Wrong email or password");

                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                binding.progressBar3.setVisibility(View.GONE);
                ShowMessage("server error");
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