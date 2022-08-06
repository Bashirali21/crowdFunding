package app.com.abisolacrowdfunding.ui.createcompaign.Model;

import com.google.gson.annotations.SerializedName;

public class CompaignFormData {
    public int getCompaignId() {
        return compaignId;
    }

    public void setCompaignId(int compaignId) {
        this.compaignId = compaignId;
    }

    public int getCreater_id() {
        return creater_id;
    }

    public void setCreater_id(int creater_id) {
        this.creater_id = creater_id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public CompaignFormData(String Title, int creater_id, String description) {
        this.title = Title;
        this.creater_id = creater_id;
        this.Description = description;
    }

    @SerializedName("compaign_id")
    public int compaignId;
    @SerializedName("compaign_creater_id")
    public int creater_id;
    @SerializedName("compaign_description")
    public String Description;
    @SerializedName("compaign_title")
    public String title;



}
