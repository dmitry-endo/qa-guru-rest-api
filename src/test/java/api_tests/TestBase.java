package api_tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    protected static final String API_KEY = "reqres-free-v1";

    protected static final String REGISTER_PATH = "/register";
    protected static final String LOGIN_PATH = "/login";

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "/api";
    }
}
