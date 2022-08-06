package app.com.abisolacrowdfunding.ui.signup.model;

import com.google.gson.annotations.SerializedName;

public class SignUp   {


    public SignUp(int userId, String firstName,String password, String user_email, String user_address,String lastName) {
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Password = password;
        this.user_email = user_email;
        this.user_address = user_address;
    }

    @SerializedName("user_id")
    public int UserId;
    @SerializedName("user_fname")
    public String FirstName;
    @SerializedName("user_Lastname")
    public String LastName;
    @SerializedName("user_password")
    public String Password;
    @SerializedName("user_email")
    public String user_email;
    @SerializedName("user_address")
    public String user_address;



    public int getUserId() {
        return UserId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }



    public String getuser_email() {
        return user_email;
    }

    public String getuser_address() {
        return user_address;
    }
}