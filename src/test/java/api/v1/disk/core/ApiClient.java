package api.v1.disk.core;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import api.v1.disk.config.TestConfig;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public final class ApiClient {

    private ApiClient() {}

    private static RequestSpecification baseRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(TestConfig.BASE_URI)
                .setBasePath(TestConfig.BASE_PATH)
                .addHeader("Authorization", "OAuth " + TestConfig.OAUTH_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    private static RequestSpecification requestWithParams(Map<String, Object> params) {
        RequestSpecification req = given().spec(baseRequest());

        if (params != null && !params.isEmpty()) {
            req.queryParams(params);
        }

        return req;
    }

    public static Response get(String endpoint, Map<String, Object> params) {
        return requestWithParams(params)
                .get(endpoint);
    }

    public static Response post(String endpoint, Map<String, Object> params) {
        return requestWithParams(params)
                .post(endpoint);
    }

    public static Response put(String endpoint, Map<String, Object> params) {
        return requestWithParams(params)
                .put(endpoint);
    }

    public static Response delete(String endpoint, Map<String, Object> params) {
        return requestWithParams(params)
                .delete(endpoint);
    }

    public static Response putFile(String url, File file) {
        return given()
                .spec(baseRequest())
                .body(file)
                .put(url);
    }

}
