package store.service;

import store.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderProductsService {
    private final List<Product> orderProducts = new ArrayList<>();

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

    public void takeOrder(StoreService storeService, String userOrder) {
        orderProducts.clear();
        for (String item : splitItems(userOrder)) {
            String[] productAndQuantity = splitDash(item);
            String productName = productAndQuantity[0];
            int quantity = Integer.parseInt(productAndQuantity[1]);
            int price = getPrice(storeService, productName);
            String promotion = getPromotion(storeService, productName);

            orderProducts.add(new Product(productName, price, quantity, promotion));
        }
    }

    public void plusOne(String itemName) {
        orderProducts.stream()
                .filter(product -> product.getName().equals(itemName))
                .findFirst()
                .ifPresent(Product::addQuantity);
    }

    public void minus(String itemName, int minus) {
        orderProducts.stream()
                .filter(product -> product.getName().equals(itemName))
                .findFirst()
                .ifPresent(product -> product.minusQuantity(minus));
    }

    private String getPromotion(StoreService storeService, String productName) {
        return storeService.getProducts().stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .map(Product::getPromotion)
                .orElse("null");
    }

    private int getPrice(StoreService storeService, String productName) {
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
