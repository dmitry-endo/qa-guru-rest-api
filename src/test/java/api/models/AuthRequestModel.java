package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthRequestModel {

    @JsonProperty("userName")
    private String username;
    private String password;
}
