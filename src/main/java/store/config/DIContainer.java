package store.config;

import store.controller.MainController;
import store.controller.OrderController;
import store.controller.StoreController;
import store.controller.ReceiptController;
import store.controller.PromotionController;
import store.controller.MembershipController;

import store.service.OrderService;
import store.service.StoreService;
import store.service.PaymentService;
import store.service.PromotionService;
import store.service.MembershipService;
import store.service.OrderProductsService;
import store.service.PromotionConditionService;

public class DIContainer {
    public static MainController createMainController() {
        OrderService orderService = new OrderService();
        StoreService storeService = new StoreService();
        PaymentService paymentService = new PaymentService();
        PromotionService promotionService = new PromotionService();
        MembershipService membershipService = new MembershipService();
        OrderProductsService orderProductsService = new OrderProductsService();
        PromotionConditionService promotionConditionService = new PromotionConditionService(orderService, storeService, promotionService);

        OrderController orderController = new OrderController(orderService, storeService, orderProductsService);
        StoreController storeController = new StoreController(storeService, orderProductsService);
        PromotionController promotionController = new PromotionController(promotionService, promotionConditionService, orderProductsService);
        MembershipController membershipController = new MembershipController(membershipService);
        ReceiptController receiptController = new ReceiptController(promotionService, orderProductsService, paymentService, membershipService);

        return new MainController(orderController, storeController, promotionController, membershipController, receiptController);
    }
}
