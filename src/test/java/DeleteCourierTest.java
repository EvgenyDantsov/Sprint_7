import com.google.gson.Gson;
import courier.Courier;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import step.DeleteCourierStep;
import util.BaseTest;

import static org.hamcrest.CoreMatchers.is;

public class DeleteCourierTest extends BaseTest {
    private DeleteCourierStep deleteCourierStep;
    private Courier courier;
    private String courierId;

    @Before
    public void setUp() {
        courier = new Courier("sprint_7", "1234", "Evgeny");
        deleteCourierStep = new DeleteCourierStep();
        deleteCourierStep.createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
        courierId = deleteCourierStep.loginCourierAndGetId(courier.getLogin(), courier.getPassword());
    }

    @After
    public void tearDown() {
        if (courierId != null) {
            deleteCourierStep.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Successful deletion of a courier")
    public void deleteCourierSuccessTest() {
        deleteCourierStep.deleteCourier(courierId)
                .statusCode(200)
                .body("ok", is(true));
        courierId = null; // Устанавливаем значение null, чтобы избежать удаления в tearDown
    }

    @Test
    @DisplayName("Delete courier without ID")
    public void deleteCourierWithoutIdTest() {
        deleteCourierStep.deleteCourier(null)
                .statusCode(404)
                .body("message", is("Not Found."));
    }

    @Test
    @DisplayName("Delete courier with non-existing ID")
    public void deleteCourierNonExistingIdTest() {
        deleteCourierStep.deleteCourier("11111")
                .statusCode(404)
                .body("message", is("Курьера с таким id нет."));
    }
}