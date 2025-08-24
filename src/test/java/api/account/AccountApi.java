package api.account;

import api.models.AuthRequestModel;
import api.models.AuthResponseModel;
import config.ConfigReader;
import config.ProjectConfig;
import config.api.ApiConfig;
import config.web.WebConfig;

import static api.ApiEndpoints.LOGIN_PATH;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AccountApi {

    public static AuthResponseModel successfulAuth() {

        ApiConfig apiConfig = ConfigReader.getApiConfig();
        WebConfig webConfig = ConfigReader.getWebConfig();
        ProjectConfig projectConfig = new ProjectConfig(webConfig, apiConfig);
        AuthRequestModel authData = new AuthRequestModel(
                projectConfig.getUsername(), projectConfig.getPassword());

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
