package step;

import com.google.gson.Gson;
import courier.Courier;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class DeleteCourierStep {
    private static final Gson gson = new Gson();

    @Step("Create courier")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(gson.toJson(courier))
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Login courier and get ID")
    public String loginCourierAndGetId(String login, String password) {
        return given()
                .header("Content-type", "application/json")
                .body(gson.toJson(new Courier(login, password)))
                .when()
                .post("/api/v1/courier/login")
                .then()
                .extract()
                .path("id")
                .toString();
    }

    @Step("Delete courier")
    public ValidatableResponse deleteCourier(String id) {
        if (id == null) {
            return given()
                    .header("Content-type", "application/json")
                    .when()
                    .delete("/api/v1/courier/")
                    .then();
        } else {
            return given()
                    .header("Content-type", "application/json")
                    .pathParam("id", id)
                    .when()
                    .delete("/api/v1/courier/{id}")
                    .then();
        }
    }
}