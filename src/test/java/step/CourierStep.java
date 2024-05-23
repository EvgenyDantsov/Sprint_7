package step;

import courier.Courier;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class CourierStep {
    private static final Gson gson = new Gson();

    @Step("Create new courier and set flag")
    public void createNewCourier(Courier courier) {
        createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
    }

    @Step("Create courier")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(gson.toJson(courier))
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Login courier")
    public String loginCourier(String login, String password) {
        return given()
                .header("Content-type", "application/json")
                .body(gson.toJson(new Courier(login, password)))
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(200)
                .extract()
                .path("id")
                .toString();
    }

    @Step("Delete courier")
    public void deleteCourier(String id) {
        given()
                .header("Content-type", "application/json")
                .pathParam("id", id)
                .when()
                .delete("/api/v1/courier/{id}")
                .then()
                .statusCode(200);
    }
}