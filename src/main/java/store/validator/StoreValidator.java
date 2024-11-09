package store.validator;

import store.model.Product;
import store.service.OrderService;
import store.service.StoreService;

import java.util.List;
import java.util.Map;

public class StoreValidator {
    public static void checkOrderProduct(OrderService orderService, StoreService storeService) {
        List<Product> products = storeService.getProducts();
        Map<String, Integer> orderItems = orderService.getOrder().getItems();

        checkProductName(products, orderItems);
        checkProductRemaining(products, orderItems);
    }

    private static void checkProductName(List<Product> products, Map<String, Integer> orderItems) {
        for (String productName : orderItems.keySet()) {
            boolean productExists = products.stream()
                    .anyMatch(product -> product.getName().equals(productName));
            if (!productExists) {
                throw new IllegalArgumentException("[Error] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
            }
        }
    }

    private static void checkProductRemaining(List<Product> products, Map<String, Integer> orderItems) {
        for (Map.Entry<String, Integer> item : orderItems.entrySet()) {
            int totalAvailableQuantity = products.stream()
                    .filter(product -> product.getName().equals(item.getKey()))
                    .mapToInt(Product::getQuantity)
                    .sum();

            if (item.getValue() > totalAvailableQuantity) {
                throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }
        }
    }
}
