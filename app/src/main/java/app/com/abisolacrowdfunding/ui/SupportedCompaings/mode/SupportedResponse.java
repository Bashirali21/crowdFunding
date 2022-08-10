package app.com.abisolacrowdfunding.ui.SupportedCompaings.mode;

import com.google.gson.annotations.SerializedName;

public class SupportedResponse {
    public int getwithdrawl_reference() {
        return withdrawl_reference;
    }

    public void setwithdrawl_reference(int withdrawl_reference) {
        this.withdrawl_reference = withdrawl_reference;
    }

    public String getCompaign_title() {
        return compaign_title;
    }

    public void setCompaign_title(String compaign_title) {
        this.compaign_title = compaign_title;
    }

    public String getwithdrawl_discription() {
        return withdrawl_discription;
    }

    public void setwithdrawl_discription(String withdrawl_discription) {
        this.withdrawl_discription = withdrawl_discription;
    }

    @SerializedName("withdrawl_reference")
    public int withdrawl_reference;

    public int getWithdrawl_amount() {
        return withdrawl_amount;
    }

    public void setWithdrawl_amount(int withdrawl_amount) {
        this.withdrawl_amount = withdrawl_amount;
    }

    @SerializedName("withdrawl_amount")
    public int withdrawl_amount;
    @SerializedName("compaign_title")
    public String compaign_title;
    @SerializedName("withdrawl_discription")
    public String withdrawl_discription;

}
