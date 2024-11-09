package store.service;

import store.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void create(String userOrder) {
        Map<String, Integer> itemMap = new HashMap<>();

        for (String item : splitItems(userOrder)) {
            String[] productAndQuantity = splitDash(item);
            itemMap.put(productAndQuantity[0], Integer.parseInt(productAndQuantity[1]));
        }

        order = new Order(itemMap);
    }

    public void plusOne(String itemName) {
        order.addQuantity(itemName, 1);
    }

    private List<String> splitItems(String userOrder) {
        userOrder = userOrder.substring(1, userOrder.length() - 1); // 양 끝 대괄호 제거

        return List.of(userOrder.split("\\],\\["));
    }

    private String[] splitDash(String item) {
        return item.split("-");
    }
}
