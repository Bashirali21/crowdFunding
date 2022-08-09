package app.com.abisolacrowdfunding.ui.allcompaigns.model;

import com.google.gson.annotations.SerializedName;
//model is the tha data we are getting from api or we are sending to api
public class AllComaignsResponse {
    public AllComaignsResponse(int compaign_id, String compaign_title, String compaign_description, String user_fname) {
        this.compaign_id = compaign_id;
        this.compaign_title = compaign_title;
        this.compaign_description = compaign_description;
        this.user_fname = user_fname;
    }

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

    public String getCompaign_description() {
        return compaign_description;
    }

    public void setCompaign_description(String compaign_description) {
        this.compaign_description = compaign_description;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    @SerializedName("compaign_id")
    public int compaign_id;
    @SerializedName("compaign_title")
    public String compaign_title;
    @SerializedName("compaign_description")
    public String compaign_description;
    @SerializedName("user_fname")
    public String user_fname;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @SerializedName("compaign_date_created")
    public String date;
}
