package store.service;

import store.model.Product;
import store.model.Receipt;

import java.util.List;

public class PaymentService {
    private Receipt receipt;

    public Receipt getReceipt() {
        return receipt;
    }

    public void generate(OrderProductsService orderProductsService, PromotionService promotionService) {
        List<Product> orderItems = eachSumAmount(orderProductsService.getOrderProducts());
        List<Product> promotionItems = promotionService.getPromotionItems();
        int totalQuantity = orderProductsService.getTotalQuantity();
        int totalAmount = orderProductsService.getTotalPrice();
        int promotionDiscount = promotionService.getPromotionDiscount();
        int membershipDiscount = membershipDiscount(totalAmount + promotionDiscount);
        int finalAmount = totalAmount + promotionDiscount + membershipDiscount;

        receipt = new Receipt(orderItems, promotionItems, totalQuantity, totalAmount,
                promotionDiscount, membershipDiscount, finalAmount);
    }

    public List<Product> eachSumAmount(List<Product> orderItems) {
        for (Product item : orderItems) {
            item.setPrice(item.getQuantity() * item.getPrice());
        }
        return orderItems;
    }

    public int membershipDiscount(int afterPromotionDiscount) {
        return (int) (afterPromotionDiscount * 0.3); //after에 적용 xx
    }
}
