package store.controller;

import store.service.MembershipService;
import store.service.OrderProductsService;
import store.service.PaymentService;
import store.service.PromotionService;
import store.view.OutputView;

public class ReceiptController {
    private final PromotionService promotionService;
    private final OrderProductsService orderProductsService;
    private final PaymentService paymentService;
    private final MembershipService membershipService;

    public ReceiptController(PromotionService promotionService, OrderProductsService orderProductsService, PaymentService paymentService, MembershipService membershipService) {
        this.promotionService = promotionService;
        this.orderProductsService = orderProductsService;
        this.paymentService = paymentService;
        this.membershipService = membershipService;
    }

    public void printReceipt() {
        paymentService.generate(orderProductsService, promotionService, membershipService);

        OutputView.printReceipt(paymentService.getReceipt());
    }
}
