package step;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import order.Orders;

import static io.restassured.RestAssured.given;

public class GetOrderStep {
    private static final Gson gson = new Gson();

    @Step("Create order")
    public ValidatableResponse createOrder(Orders orders) {
        return given()
                .header("Content-type", "application/json")
                .body(gson.toJson(orders))
                .when()
                .post("/api/v1/orders")
                .then();
    }

    @Step("Delete order")
    public ValidatableResponse deleteOrder(String track) {
        return given()
                .queryParam("track", track)
                .when()
                .put("/api/v1/orders/cancel")
                .then();
    }

    @Step("Get order by number")
    public ValidatableResponse getOrder(String track) {
        return given()
                .queryParam("t", track)
                .when()
                .get("/api/v1/orders/track")
                .then();
    }
}