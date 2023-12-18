import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatusTest {

    @Test
    void getOrderStatusValue() {
        //GIVEN
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING);

        //WHEN
        String actual = newOrder.orderStatus().getValue();

        //THEN
        String expected = "processing";
        assertEquals(actual, expected);
    }

    @Test
    void getOrderStatus() {
        //GIVEN
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), OrderStatus.IN_DELIVERY);

        //WHEN
        OrderStatus actual = newOrder.orderStatus();

        //THEN
        OrderStatus expected = OrderStatus.IN_DELIVERY;
        assertEquals(actual, expected);
    }
}