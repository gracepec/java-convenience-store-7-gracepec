package store.model;

import java.util.List;

public record Receipt(
        List<Product> items,
        List<Product> promotionItems,
        int totalQuantity,
        int totalAmount,
        int promotionDiscount,
        int membershipDiscount,
        int finalAmount) {
}
