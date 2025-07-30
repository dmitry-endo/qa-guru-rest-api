package specs;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpecs extends BaseSpec {

    public static final RequestSpecification defaultRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header("x-api-key", API_KEY)
            .contentType(JSON);
}
