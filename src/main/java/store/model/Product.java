package store.model;

public class Product {
    private final String name;
    private int price;
    private int quantity;
    private final String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public Product(Product other) {
        this.name = other.name;
        this.price = other.price;
        this.quantity = other.quantity;
        this.promotion = other.promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity() {
        quantity++;
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
