package store.service;

import store.model.Product;
import store.model.Receipt;

import java.util.List;

public class PaymentService {
    private Receipt receipt;

    public Receipt getReceipt() {
        return receipt;
    }

    public void generate(OrderProductsService orderProductsService, PromotionService promotionService, MembershipService membershipService) {
        List<Product> orderItems = eachSumAmount(orderProductsService.getOrderProducts());
        List<Product> promotionItems = promotionService.getPromotionItems().stream().filter(product -> product.getQuantity() > 0).toList();
        int totalQuantity = orderProductsService.getTotalQuantity();
        int totalAmount = orderProductsService.getTotalPrice();
        int promotionDiscount = promotionService.getDiscount();
        int membershipDiscount = membershipService.getDiscount(totalAmount, promotionService);
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
}
