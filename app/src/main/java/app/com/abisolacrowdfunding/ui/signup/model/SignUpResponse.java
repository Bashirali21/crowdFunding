package app.com.abisolacrowdfunding.ui.signup.model;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    public SignUpResponse(String isSuccessfull, String message) {
        isSuccessfull = isSuccessfull;
        this.message = message;
    }

    public Boolean getisSuccessfull() {
        return isSuccessfull;
    }

    public void setisSuccessfull(String isSuccessfull) {
        isSuccessfull = isSuccessfull;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    @SerializedName("isSuccessfull")
    public Boolean isSuccessfull;
    @SerializedName("message")
    public String message;

    public SignUp getUser() {
        return user;
    }

    public void setUser(SignUp user) {
        this.user = user;
    }

    @SerializedName("user")
    public SignUp user;

}
