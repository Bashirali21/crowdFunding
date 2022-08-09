package app.com.abisolacrowdfunding.ui.CompaignDetail.Model;

import com.google.gson.annotations.SerializedName;

public class CompaignDetailResponse {
    @SerializedName("percentage")
    public float percentage;

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalDonation() {
        return totalDonation;
    }

    public void setTotalDonation(int totalDonation) {
        this.totalDonation = totalDonation;
    }

    public int getWithdrawnAmount() {
        return withdrawnAmount;
    }

    public void setWithdrawnAmount(int withdrawnAmount) {
        this.withdrawnAmount = withdrawnAmount;
    }

    public int getCountPeopleDonated() {
        return countPeopleDonated;
    }

    public void setCountPeopleDonated(int countPeopleDonated) {
        this.countPeopleDonated = countPeopleDonated;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    @SerializedName("title")
    public String title;
    @SerializedName("totalDonation")
    public int totalDonation;
    @SerializedName("withdrawnAmount")
    public int withdrawnAmount;
    @SerializedName("countPeopleDonated")
    public int countPeopleDonated;
    @SerializedName("currentBalance")
    public int currentBalance;
}
