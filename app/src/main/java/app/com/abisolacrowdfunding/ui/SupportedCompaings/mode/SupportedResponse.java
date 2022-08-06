package app.com.abisolacrowdfunding.ui.SupportedCompaings.mode;

import com.google.gson.annotations.SerializedName;

public class SupportedResponse {
    public int getdonation_reference() {
        return donation_reference;
    }

    public void setdonation_reference(int donation_reference) {
        this.donation_reference = donation_reference;
    }

    public String getCompaign_title() {
        return compaign_title;
    }

    public void setCompaign_title(String compaign_title) {
        this.compaign_title = compaign_title;
    }

    public String getCompaign_date_created() {
        return compaign_date_created;
    }

    public void setCompaign_date_created(String compaign_date_created) {
        this.compaign_date_created = compaign_date_created;
    }

    @SerializedName("donation_reference")
    public int donation_reference;
    @SerializedName("compaign_title")
    public String compaign_title;
    @SerializedName("compaign_date_created")
    public String compaign_date_created;

}
