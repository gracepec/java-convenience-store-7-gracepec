package store.model;

import java.util.Map;

public record Order(Map<String, Integer> items) {

    public Order {
        items = Map.copyOf(items);
    }

    public int getQuantity(String product) {
        return items.getOrDefault(product, 0);
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
