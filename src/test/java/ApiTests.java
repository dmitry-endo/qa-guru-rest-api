import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ApiTests {
    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "/api";
    }

    @Test
    void successfulRegisterTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given()
                .body(authData)
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(notNullValue()))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void missingPasswordRegisterTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\"}";

        given()
                .body(authData)
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void missingEmailRegisterTest() {
        String authData = "{\"password\": \"pistol\"}";

        given()
                .body(authData)
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void createUserTest() {
        String userData = "{\"name\": \"helen\", \"job\": \"refugee\"}";

        given()
                .body(userData)
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("id", is(notNullValue()))
                .body("createdAt", is(notNullValue()))
                .body("name", is("helen"))
                .body("job", is("refugee"));
    }

    @Test
    void deleteUserTest() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

                .when()
                .delete("/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void checkDataSizeTest() {
        given()
                .log().uri()
                .queryParam("page", "2")
                .get("/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data", hasSize(6));
    }

    @Test
    void checkSupportContentTest() {
        String supportUrl = "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral";
        String supportText = "Tired of writing endless social media content? Let Content Caddy generate it for you.";

        given()
                .log().uri()
                .queryParam("page", "2")
                .get("/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("support.url", equalTo(supportUrl))
                .body("support.text", equalTo(supportText));
    }
}
