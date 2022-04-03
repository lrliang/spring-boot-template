package com.jtp.hr.about;

import com.jtp.hr.BaseApiTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class AboutControllerApiTest extends BaseApiTest {

    @Test
    public void should_display_about_info() {
        given()
                .when()
                .get("/about")
                .then()
                .statusCode(200);
    }
}