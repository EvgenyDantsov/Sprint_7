package util;

import io.restassured.RestAssured;
import org.junit.Before;

public abstract class BaseTest {
    @Before
    public void setupRestAssured(){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }
}