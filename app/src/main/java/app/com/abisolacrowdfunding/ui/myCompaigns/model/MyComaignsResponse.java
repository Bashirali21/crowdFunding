package app.com.abisolacrowdfunding.ui.myCompaigns.model;

import com.google.gson.annotations.SerializedName;

public class MyComaignsResponse {
    public int getCompaign_id() {
        return compaign_id;
    }

    public void setCompaign_id(int compaign_id) {
        this.compaign_id = compaign_id;
    }

    public String getCompaign_title() {
        return compaign_title;
    }
    public void setCompaign_title(String compaign_title) {
        this.compaign_title = compaign_title;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @SerializedName("id")
    public int compaign_id;
    @SerializedName("title")
    public String compaign_title;
    @SerializedName("amount")
    public int amount;



}
