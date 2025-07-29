package specs;

import api_tests.TestBase;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class AuthSpec extends TestBase {
    public static RequestSpecification authRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header("x-api-key", API_KEY)
            .contentType(JSON);

    public static ResponseSpecification authResponseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification authResponseSpec400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();
}
