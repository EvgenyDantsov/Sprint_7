import io.qameta.allure.junit4.DisplayName;
import order.Orders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import step.CreateOrderStep;
import util.BaseTest;

import static org.hamcrest.CoreMatchers.*;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest extends BaseTest {
    private final Orders orders;
    private CreateOrderStep createOrderStep;

    public CreateOrderParameterizedTest(Orders orders) {
        this.orders = orders;
    }

    @Parameterized.Parameters(name = "Test {index}: Test with colors={0}")
    public static Object[][] getColorData() {
        return new Object[][]{
                {new Orders("Evgeny", "Smith", "123 Street", "5",
                        "+1234567890", 5, "2024-05-20", "Comment", new String[]{"BLACK"})},
                {new Orders("Evgeny", "Smith", "123 Street", "5",
                        "+1234567890", 5, "2024-05-20", "Comment", new String[]{"GREY"})},
                {new Orders("Evgeny", "Smith", "123 Street", "5",
                        "+1234567890", 5, "2024-05-20", "Comment", new String[]{"BLACK", "GREY"})},
                {new Orders("Evgeny", "Smith", "123 Street", "5",
                        "+1234567890", 5, "2024-05-20", "Comment", new String[]{})}
        };
    }

    @Before
    public void setUp() {
        createOrderStep = new CreateOrderStep();
    }

    @After
    public void tearDown() {
        if (orders.getTrack() != null) {
            createOrderStep.cancelOrder(orders);
        }
    }

    @Test
    @DisplayName("Create order with different colors")
    public void createOrderTest() {
        createOrderStep.createOrder(orders)
                .statusCode(201)
                .body("track", notNullValue());
    }
}