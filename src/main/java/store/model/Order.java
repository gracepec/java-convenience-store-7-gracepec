package store.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<String, Integer> items;

    public Order(Map<String, Integer> items) {
        this.items = new HashMap<>(items);
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public int getQuantity(String product) {
        return items.getOrDefault(product, 0);
    }

    public void addQuantity(String product, int quantity) {
        items.put(product, getQuantity(product) + quantity);
    }
}
