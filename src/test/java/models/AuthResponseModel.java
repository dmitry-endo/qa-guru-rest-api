package models;

import lombok.Data;

@Data
public class AuthResponseModel {
    private Integer id;
    private String token, error;
}
