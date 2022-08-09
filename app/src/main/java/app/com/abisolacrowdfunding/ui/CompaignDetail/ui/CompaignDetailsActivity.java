package app.com.abisolacrowdfunding.ui.CompaignDetail.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import app.com.abisolacrowdfunding.R;
import app.com.abisolacrowdfunding.databinding.ActivityCompaignDetailsBinding;
import app.com.abisolacrowdfunding.network.APIClient;
import app.com.abisolacrowdfunding.network.ApiService;
import app.com.abisolacrowdfunding.ui.CompaignDetail.Model.CompaignDetailResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompaignDetailsActivity extends AppCompatActivity {
    int cid;
    ActivityCompaignDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCompaignDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        cid = extras.getInt("Compaign_id", 9);//for gettting compaign id from previous
        SetData();
    }

    public void SetData() {
        //here we are just calling our compaignDetails function which we define in the api service class and this function is giving us response which we are showing in our acitivity
        ApiService apiService = APIClient.getClient().create(ApiService.class);
        apiService.CompaignDetails(cid).enqueue(new Callback<CompaignDetailResponse>() {
            @Override
            public void onResponse(Call<CompaignDetailResponse> call, Response<CompaignDetailResponse> response) {
                if (response.isSuccessful()) {
                    binding.tvCompaignTitle.setText(response.body().title+"");
                    binding.tvCountPeopleDonated.setText(response.body().countPeopleDonated+"");
                    binding.tvCurrentBalance.setText(response.body().currentBalance+"");
                    binding.tvTotalAmountWithDraw.setText(response.body().withdrawnAmount+"");
                    binding.tvDonations.setText(response.body().totalDonation+"");
                }
            }

            @Override
            public void onFailure(Call<CompaignDetailResponse> call, Throwable t) {

            }
        });
    }

}