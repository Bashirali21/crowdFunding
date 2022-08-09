package app.com.abisolacrowdfunding.ui.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.databinding.ActivityHomePageBinding;
import app.com.abisolacrowdfunding.ui.MySupportedCompaings.view.MySupportedCompaingsActivity;
import app.com.abisolacrowdfunding.ui.SupportedCompaings.ui.SupportedCompaingsActivity;
import app.com.abisolacrowdfunding.ui.allcompaigns.ui.AllCompaingsActivity;
import app.com.abisolacrowdfunding.ui.createcompaign.view.CreateCompaignActivity;
import app.com.abisolacrowdfunding.ui.login.MainActivity;
import app.com.abisolacrowdfunding.ui.myCompaigns.model.MyComaignsResponse;
import app.com.abisolacrowdfunding.ui.myCompaigns.ui.MyCompaignsActivity;

public class HomePageActivity extends AppCompatActivity {
    ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences sharedPreferences = getSharedPreferences("loginUser", MODE_PRIVATE);
        binding.tvsName.setText(sharedPreferences.getString("name", "ali"));
        onCLicks();
    }

    public void onCLicks() {

        binding.cardCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, CreateCompaignActivity.class));//for navigating to createc compaign page
            }
        });
        //same thing for all below we are jus navigating
        binding.cardShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, AllCompaingsActivity.class));
            }
        });
        binding.cardMyCompaigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, MyCompaignsActivity.class));
            }
        });

        binding.cardSupportedCompaings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, SupportedCompaingsActivity.class));
            }
        });
        binding.cardMySupportedCompaings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageActivity.this, MySupportedCompaingsActivity.class));
            }
        });
        binding.tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomePageActivity.this, "Logout Succesfully", Toast.LENGTH_SHORT).show();
                SharedPreferences settings = getSharedPreferences("PreferencesName",MODE_PRIVATE);
                settings.edit().clear().commit();
                startActivity(new Intent(HomePageActivity.this, MainActivity.class));
                finish();

            }
        });
    }

}