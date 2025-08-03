package api.account;

import api.models.AuthRequestModel;
import api.models.AuthResponseModel;

import static api.ApiEndpoints.LOGIN_PATH;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AccountApi {

    public static AuthResponseModel getAuthorization() {
        AuthRequestModel authData = new AuthRequestModel();
        authData.setUsername("dmitry_endo");
        authData.setPassword("73^MkhSqH94Dq*tI");

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
