import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import step.ListOrdersStep;
import util.BaseTest;

import static org.hamcrest.Matchers.*;

public class ListOrderTest extends BaseTest {
    private ListOrdersStep listOrdersStep;

    @Before
    public void setUp() {
        listOrdersStep = new ListOrdersStep();
    }

    @Test
    @DisplayName("Get orders list")
    public void getOrdersListTest() {
        listOrdersStep.getOrders()
                .statusCode(200)
                .body("orders", is(not(empty())));
    }
}