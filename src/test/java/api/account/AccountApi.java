package api.account;

import api.models.AuthRequestModel;
import api.models.AuthResponseModel;
import config.ConfigReader;
import config.ApiConfig;

import static api.ApiEndpoints.LOGIN_PATH;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AccountApi {

    public static AuthResponseModel successfulAuth() {

        ApiConfig apiConfig = ConfigReader.getApiConfig();
        AuthRequestModel authData = new AuthRequestModel(
                apiConfig.getUsername(), apiConfig.getPassword());

        return given()
                .log().all()
                .body(authData)
                .contentType(JSON)
                .when()
                .post(LOGIN_PATH)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(AuthResponseModel.class);
    }
}
