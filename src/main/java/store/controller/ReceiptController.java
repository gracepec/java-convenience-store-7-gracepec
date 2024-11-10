package store.controller;

import store.model.Receipt;
import store.service.OrderProductsService;
import store.service.PaymentService;
import store.service.PromotionService;
import store.view.OutputView;

public class ReceiptController {
    private final PromotionService promotionService;
    private final OrderProductsService orderProductsService;
    private final PaymentService paymentService;

    public ReceiptController(PromotionService promotionService, OrderProductsService orderProductsService, PaymentService paymentService) {
        this.promotionService = promotionService;
        this.orderProductsService = orderProductsService;
        this.paymentService = paymentService;
    }

    public void printReceipt() {
        Receipt receipt = payProcess();

        OutputView.printReceipt(receipt);
    }

    private Receipt payProcess() {
        int totalQuantity = orderProductsService.getTotalQuantity();
        int totalAmount = orderProductsService.getTotalPrice();
        int promotionDiscount = promotionService.getPromotionDiscount();
        double membershipDiscount = paymentService.membershipDiscount(totalAmount + promotionDiscount);
        double finalAmount = totalAmount + promotionDiscount + membershipDiscount;

        return new Receipt(orderProductsService.getOrderProducts(), promotionService.getPromotionItems(), totalQuantity, totalAmount,
                promotionDiscount, membershipDiscount, finalAmount);
    }
}
