package api.specs;

import api.account.AccountApi;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class DefaultSpecs {

    public static final RequestSpecification defaultRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .header("Authorization", "Bearer " + AccountApi.getAuthorization().getToken())
            .contentType(JSON);

    public static ResponseSpecification defaultResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(ALL)
                .build();
    }
}
