package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModel {
    @JsonProperty("userId")
    private String userId;
    
    @JsonProperty("username")
    private String username;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("token")
    private String token;
    
    @JsonProperty("expires")
    private String expires;
    
    @JsonProperty("isActive")
    private String isActive;

    @JsonProperty("created_date")
    private String createdDate;
}
