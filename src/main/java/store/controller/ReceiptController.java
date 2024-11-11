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
        paymentService.generate(orderProductsService, promotionService);

        OutputView.printReceipt(paymentService.getReceipt());
    }
}
