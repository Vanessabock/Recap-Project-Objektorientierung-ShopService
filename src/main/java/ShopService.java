import lombok.*;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@AllArgsConstructor
public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();
    private IdService idService = new IdService();

    public Order addOrder(List<String> productIds) throws NoSuchProductExeption {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                throw new NoSuchProductExeption("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING, ZonedDateTime.now());

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.orderStatus() == orderStatus)
                .collect(Collectors.toList());
    }

    public Order getOrderById(String id) {
        return orderRepo.getOrders().stream()
                .filter(order -> Objects.equals(order.id(), id))
                .toList().get(0);
    }

    public void updateOrderById(String id, OrderStatus orderStatus){
        Order orderToUpdate = orderRepo.getOrderById(id);
        Order newOrder = orderToUpdate.withOrderStatus(orderStatus);
        // orderRepo.removeOrder(id);
        orderRepo.addOrder(newOrder);
    }
}
