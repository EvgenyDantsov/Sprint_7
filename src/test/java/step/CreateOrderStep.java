package step;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import order.Orders;

import static io.restassured.RestAssured.given;

public class CreateOrderStep {
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

    @Step("Cancel order")
    public ValidatableResponse cancelOrder(Orders orders) {
        return given()
                .header("Content-type", "application/json")
                .body(gson.toJson(orders))
                .when()
                .put("/api/v1/orders/cancel")
                .then();
    }
}