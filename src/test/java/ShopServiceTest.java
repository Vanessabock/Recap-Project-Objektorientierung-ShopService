import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() throws NoSuchProductExeption {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING, ZonedDateTime.now());
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNoSuchProductExeption() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "6");

        //WHEN & THEN
        assertThrows(NoSuchProductExeption.class, () -> shopService.addOrder(productsIds));
    }

    @Test
    void getOrdersByOrderStatusTest_WhenPROCESSING_ThenListOfProductsWithStatusProcessing() throws NoSuchProductExeption {
        //GIVEN
        ShopService shopService = new ShopService();

        //WHEN
        List<String> productsIds = List.of("1");
        Order order = shopService.addOrder(productsIds);
        List<Order> actual = shopService.getOrdersByOrderStatus(OrderStatus.PROCESSING);

        //THEN
        List<Order> expected = new ArrayList<>();
        expected.add(new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING, ZonedDateTime.now()));
        assertEquals(expected.get(0).orderStatus(), actual.get(0).orderStatus());
    }

    @Test
    void getOrderByIdTest_WhenId_ThenOrder() throws NoSuchProductExeption {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order expected = shopService.addOrder(productsIds);
        Order actual = shopService.getOrderById(expected.id());


        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void updateOrderTest_WhenPROCESSING_ThenIN_DELIVERY() throws NoSuchProductExeption {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);
        shopService.updateOrderById(actual.id(), OrderStatus.IN_DELIVERY);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.IN_DELIVERY, ZonedDateTime.now());
        assertEquals(expected.orderStatus(), shopService.getOrderById(actual.id()).orderStatus());
    }
}
