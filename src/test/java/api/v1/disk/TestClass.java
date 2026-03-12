package api.v1.disk;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class TestClass {

    @Test
    public void getDiskMeta() {
        given()
                .baseUri("https://cloud-api.yandex.net")
                .basePath("/v1/disk")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", System.getProperty("token"))
                .when()
                .log().all()
                .get()
                .then()
                .log().headers()
                .statusCode(200)
                .contentType("application/json")
                .body("user.uid", equalTo("2346219801"));

    }

}
