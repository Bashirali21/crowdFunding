package app.com.abisolacrowdfunding.ui.MySupportedCompaings.model;

import com.google.gson.annotations.SerializedName;

public class MySuppotedResponse {
    public int getCompaignId() {
        return compaignId;
    }

    public void setCompaignId(int compaignId) {
        this.compaignId = compaignId;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("compaignId")
    public int compaignId;
    @SerializedName("donationId")
    public int donationId;
    @SerializedName("title")
    public String title;
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @SerializedName("status")
    public int status;
    @SerializedName("amount")
    public int amount;
}
