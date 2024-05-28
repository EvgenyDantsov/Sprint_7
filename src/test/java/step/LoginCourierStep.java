package step;

import com.google.gson.Gson;
import courier.Courier;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class LoginCourierStep {
    private static final Gson gson = new Gson();

    @Step("Create courier and set flag")
    public void createCourier(Courier courier) {
        given()
                .header("Content-type", "application/json")
                .body(gson.toJson(courier))
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Login courier")
    public ValidatableResponse loginCourier(String login, String password) {
        return given()
                .header("Content-type", "application/json")
                .body(gson.toJson(new Courier(login, password)))
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    @Step("Login courier and get id")
    public String loginCourierAndGetId(String login, String password) {
        return loginCourier(login, password)
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