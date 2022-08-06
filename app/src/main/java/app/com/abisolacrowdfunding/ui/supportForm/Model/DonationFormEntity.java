package app.com.abisolacrowdfunding.ui.supportForm.Model;

import com.google.gson.annotations.SerializedName;

public class DonationFormEntity {
    public DonationFormEntity(int donation_compaign_id, int donation_user_id, int donation_amount) {
        this.donation_compaign_id = donation_compaign_id;
        this.donation_user_id = donation_user_id;
        this.donation_amount = donation_amount;
    }

    public int getDonation_compaign_id() {
        return donation_compaign_id;
    }

    public void setDonation_compaign_id(int donation_compaign_id) {
        this.donation_compaign_id = donation_compaign_id;
    }

    public int getDonation_user_id() {
        return donation_user_id;
    }

    public void setDonation_user_id(int donation_user_id) {
        this.donation_user_id = donation_user_id;
    }

    public int getDonation_amount() {
        return donation_amount;
    }

    public void setDonation_amount(int donation_amount) {
        this.donation_amount = donation_amount;
    }

    @SerializedName("donation_compaign_id")
    public int donation_compaign_id;
    @SerializedName("donation_user_id")
    public int donation_user_id;
    @SerializedName("donation_amount")
    public int donation_amount;

}
