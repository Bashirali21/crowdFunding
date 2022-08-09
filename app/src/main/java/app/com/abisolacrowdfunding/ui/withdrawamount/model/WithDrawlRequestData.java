package app.com.abisolacrowdfunding.ui.withdrawamount.model;

import com.google.gson.annotations.SerializedName;

public class WithDrawlRequestData {
    public int getWithdrawl_user_id() {
        return withdrawl_user_id;
    }

    public void setWithdrawl_user_id(int withdrawl_user_id) {
        this.withdrawl_user_id = withdrawl_user_id;
    }

    public String getWithdrawl_discription() {
        return withdrawl_discription;
    }

    public void setWithdrawl_discription(String withdrawl_discription) {
        this.withdrawl_discription = withdrawl_discription;
    }

    public int getWithdrawl_compaign_id() {
        return withdrawl_compaign_id;
    }

    public void setWithdrawl_compaign_id(int withdrawl_compaign_id) {
        this.withdrawl_compaign_id = withdrawl_compaign_id;
    }

    public int getWithdrawl_amount() {
        return withdrawl_amount;
    }

    public void setWithdrawl_amount(int withdrawl_amount) {
        this.withdrawl_amount = withdrawl_amount;
    }

    @SerializedName("withdrawl_user_id")
    public int withdrawl_user_id;
    @SerializedName("withdrawl_discription")
    public String withdrawl_discription;
    @SerializedName("withdrawl_compaign_id")
    public int withdrawl_compaign_id;
    @SerializedName("withdrawl_amount")
    public int withdrawl_amount;

}
