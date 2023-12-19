import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchProductExeption {
        ShopService shopService = new ShopService(new ProductRepo(), new OrderMapRepo());
        shopService.addOrder(new ArrayList<>(Arrays.asList("1", "3")));
        shopService.addOrder(new ArrayList<>(List.of("4")));
        shopService.addOrder(new ArrayList<>(Arrays.asList("2", "4", "5")));
    }
}
