package store;

import store.controller.MembershipController;
import store.controller.OrderController;
import store.controller.PromotionController;
import store.controller.StoreController;
import store.service.MembershipService;
import store.service.OrderService;
import store.service.PromotionService;
import store.service.PromotionConditionService;
import store.service.StoreService;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        StoreService storeService = new StoreService();
        PromotionService promotionService = new PromotionService();
        MembershipService membershipService = new MembershipService();
        PromotionConditionService promotionConditionService = new PromotionConditionService(orderService, storeService, promotionService);

        OrderController orderController = new OrderController(orderService, storeService);
        StoreController storeController = new StoreController(storeService);
        PromotionController promotionController = new PromotionController(promotionService, promotionConditionService, orderService);
        MembershipController membershipController = new MembershipController(membershipService);

        OutputView.printWelcome();
        storeController.printStoreProducts();

        orderController.processUserOrder();
        promotionController.processPromotion();

        membershipController.processMembership();
    }
}
