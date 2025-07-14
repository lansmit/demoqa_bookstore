package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CredentialsModel {
    @JsonProperty("userName")
    private String userName;
    
    @JsonProperty("password")
    private String password;
}
