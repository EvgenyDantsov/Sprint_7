package step;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ListOrdersStep {
    @Step("Get orders list")
    public ValidatableResponse getOrders() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders")
                .then();
    }
}