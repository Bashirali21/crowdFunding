package app.com.abisolacrowdfunding.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.com.abisolacrowdfunding.ui.SupportedCompaings.mode.SupportedResponse;
import app.com.abisolacrowdfunding.ui.allcompaigns.model.AllComaignsResponse;
import app.com.abisolacrowdfunding.ui.createcompaign.Model.CompaignFormData;
import app.com.abisolacrowdfunding.ui.myCompaigns.model.MyComaignsResponse;
import app.com.abisolacrowdfunding.ui.signup.model.SignUp;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
import app.com.abisolacrowdfunding.ui.supportForm.Model.DonationFormEntity;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    @POST("SignUp")
    Call<SignUpResponse> Register(@Body SignUp dataModal);

    @GET("UserLogin")
    Call<SignUpResponse> Login(
            @Query("username") String email,
            @Query("password") String password
    );

    @POST("createCompaign")
    Call<SignUpResponse> createCompaign(@Body CompaignFormData data);

    @GET("showAllCompaigns")
    Call<List<AllComaignsResponse>> allCompaings(
            @Query("id") int id
    );

    @GET("showMyCompaigns")
    Call<List<MyComaignsResponse>> mYCompaings(
            @Query("id") int id
    );

    @POST("addDonation")
    Call<SignUpResponse> addDonation(@Body DonationFormEntity data);

    @GET("requestWithdraw")
    Call<SignUpResponse> sendWithDrawRequest(
            @Query("compaign") int compaign
    );

    @GET("showDonatersRequest")
    Call<List<SupportedResponse>> getSupportedCompaings(
            @Query("id") int id
    );

    @GET("approveDisaproveWithDrawal")
    Call<SignUpResponse> approveDisapproveCompaign(
            @Query("status") Boolean status,
            @Query("donation_reference") int donation_reference
    );
    @GET("withDrawAmount")
    Call<SignUpResponse> withDrawAmount(
            @Query("amount") int status,
            @Query("cId") int cId
    );
}
