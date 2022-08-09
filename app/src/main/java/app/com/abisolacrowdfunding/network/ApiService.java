package app.com.abisolacrowdfunding.network;

import java.util.List;

import app.com.abisolacrowdfunding.ui.CompaignDetail.Model.CompaignDetailResponse;
import app.com.abisolacrowdfunding.ui.MySupportedCompaings.model.MySuppotedResponse;
import app.com.abisolacrowdfunding.ui.SupportedCompaings.mode.SupportedResponse;
import app.com.abisolacrowdfunding.ui.allcompaigns.model.AllComaignsResponse;
import app.com.abisolacrowdfunding.ui.createcompaign.Model.CompaignFormData;
import app.com.abisolacrowdfunding.ui.myCompaigns.model.MyComaignsResponse;
import app.com.abisolacrowdfunding.ui.signup.model.SignUp;
import app.com.abisolacrowdfunding.ui.signup.model.SignUpResponse;
import app.com.abisolacrowdfunding.ui.supportForm.Model.DonationFormEntity;
import app.com.abisolacrowdfunding.ui.withdrawamount.model.WithDrawlRequestData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
// this is class where we are calling our api functions here
public interface ApiService {

    //here we are defining signup function of the api here we only define our function it will be called from the signup page
    @POST("SignUp")
    Call<SignUpResponse> Register(@Body SignUp dataModal);
    //just like about function we are just defining login function of api here and soo on
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
    @POST("withDrawAmount")
    Call<SignUpResponse> withDrawAmount(
            @Body WithDrawlRequestData data
            );

    @GET("donaterSupportedCompaigns")
    Call<List<MySuppotedResponse>> MySupportedCompaings(
            @Query("userId") int id

    );
    @GET("refundRequest")
    Call<SignUpResponse> refund(
            @Query("userId") int status,
            @Query("donationId") int cId,
            @Query("compaignId") int compaignId,
            @Query("amount") int amount
    );

    @GET("compaignDetail")
    Call<CompaignDetailResponse> CompaignDetails(
            @Query("cid") int id

    );
}
