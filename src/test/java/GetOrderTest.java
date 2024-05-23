import io.qameta.allure.junit4.DisplayName;
import order.Orders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import step.GetOrderStep;
import util.BaseTest;

import static org.hamcrest.CoreMatchers.*;

public class GetOrderTest extends BaseTest {
    private Orders orders;
    private GetOrderStep getOrderStep;

    @Before
    public void setUp() {
        getOrderStep = new GetOrderStep();
        orders = new Orders("Evgeny", "Smith", "123 Street", "5",
                "+1234567890", 5, "2024-05-20", "Comment", new String[]{"BLACK"});
        orders.setTrack(getOrderStep.createOrder(orders).extract().path("track").toString());
    }

    @After
    public void tearDown() {
        getOrderStep.deleteOrder(orders.getTrack())
                .statusCode(200)
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Get order by number")
    public void getOrderTest() {
        getOrderStep.getOrder(orders.getTrack())
                .statusCode(200)
                .body("order", notNullValue());
    }

    @Test
    @DisplayName("Get order without order number")
    public void getOrderWithoutOrderNumberTest() {
        getOrderStep.getOrder("")
                .statusCode(400)
                .body("message", is("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("Get order with invalid order number")
    public void getOrderWithInvalidOrderNumberTest() {
        getOrderStep.getOrder("11111")
                .statusCode(404)
                .body("message", is("Заказ не найден"));
    }
}