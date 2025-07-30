package specs;

import io.restassured.specification.ResponseSpecification;

public class ResponsesSpecs extends BaseSpec {

    public static final ResponseSpecification responseSpec200 = responseSpec(200);
    public static final ResponseSpecification responseSpec201 = responseSpec(201);
    public static final ResponseSpecification responseSpec204 = responseSpec(204);
    public static final ResponseSpecification responseSpec400 = responseSpec(400);
}
