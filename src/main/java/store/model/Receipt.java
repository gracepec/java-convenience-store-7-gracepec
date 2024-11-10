package store.model;

import java.util.List;

public class Receipt {
    private List<Product> items;
    private List<Product> promotionItems;
    private int totalQuantity;
    private int totalAmount;
    private int promotionDiscount;
    private double membershipDiscount;
    private double finalAmount;

    public Receipt(List<Product> items, List<Product> promotionItems, int totalQuantity, int totalAmount,
                   int promotionDiscount, double membershipDiscount, double finalAmount) {
        this.items = items;
        this.promotionItems = promotionItems;
        this.totalQuantity = totalQuantity;
        this.totalAmount = totalAmount;
        this.promotionDiscount = promotionDiscount;
        this.membershipDiscount = membershipDiscount;
        this.finalAmount = finalAmount;
    }

    public List<Product> getItems() {
        return items;
    }

    public List<Product> getPromotionItems() {
        return promotionItems;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getPromotionDiscount() {
        return promotionDiscount;
    }

    public double getMembershipDiscount() {
        return membershipDiscount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }
}
