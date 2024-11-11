package store.model;

import java.util.HashMap;
import java.util.Map;

public record Order(Map<String, Integer> items) {
    public Order(Map<String, Integer> items) {
        this.items = new HashMap<>(items);
    }

    public int getQuantity(String product) {
        return items.getOrDefault(product, 0);
    }
}
