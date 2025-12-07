package API.Gateway.Service.API.Gateway.Service.dto;

import API.Gateway.Service.API.Gateway.Service.controller.UserType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.json.Json;
import javax.json.JsonObject;

@NoArgsConstructor
public class AuthDTO{



    private  String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private UserType userType;

    public AuthDTO(String userName, String password, String email, String phoneNumber, UserType userType) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public JsonObject convertToJSON() {
        return Json.createObjectBuilder()
                .add("userName", userName)
                .add("password", password)
                .add("role", userName)
                .build();
    }
}