package store.model;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotion() {
        return promotion;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("- ").append(name).append(" ").append(price).append("원 ");

        appendQuantity(stringBuilder);
        appendPromotion(stringBuilder);

        return stringBuilder.toString();
    }

    private void appendQuantity(StringBuilder stringBuilder) {
        if (quantity == 0) {
            stringBuilder.append("재고 없음");
            return;
        }
        stringBuilder.append(quantity).append("개");
    }

    private void appendPromotion(StringBuilder stringBuilder) {
        if (!promotion.equals("null")) {
            stringBuilder.append(" ").append(promotion);
        }
    }
}
