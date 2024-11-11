package store.service;

import store.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderProductsService {
    private final List<Product> orderProducts = new ArrayList<>();

    private final StoreService storeService;

    public OrderProductsService(StoreService storeService) {
        this.storeService = storeService;
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public int getTotalQuantity() {
        return orderProducts.stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }

    public int getTotalPrice() {
        return orderProducts.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }

    public void takeOrder(String userOrder) {
        orderProducts.clear();
        for (String item : splitItems(userOrder)) {
            String[] productAndQuantity = splitDash(item);
            String productName = productAndQuantity[0];
            int quantity = Integer.parseInt(productAndQuantity[1]);
            int price = getPrice(productName);
            String promotion = getPromotion(productName);

            orderProducts.add(new Product(productName, price, quantity, promotion));
        }
    }

    private String getPromotion(String productName) {
        return storeService.getProducts().stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .map(Product::getPromotion)
                .orElse("null");
    }

    public void plusOne(String itemName) {
        orderProducts.stream()
                .filter(product -> product.getName().equals(itemName))
                .findFirst()
                .ifPresent(Product::addQuantity);

//        item.addQuantity();
    }

    private int getPrice(String productName) {
        return storeService.getProducts().stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .map(Product::getPrice)
                .orElse(0);
    }

    private List<String> splitItems(String userOrder) {
        userOrder = userOrder.substring(1, userOrder.length() - 1); // 양 끝 대괄호 제거

        return List.of(userOrder.split("\\],\\["));
    }

    private String[] splitDash(String item) {
        return item.split("-");
    }


}
