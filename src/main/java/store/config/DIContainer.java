package store.config;

import store.controller.MainController;
import store.controller.MembershipController;
import store.controller.OrderController;
import store.controller.PromotionController;
import store.controller.StoreController;

import store.service.MembershipService;
import store.service.OrderService;
import store.service.PromotionService;
import store.service.PromotionConditionService;
import store.service.StoreService;

public class DIContainer {
    private static final OrderService orderService = new OrderService();
    private static final StoreService storeService = new StoreService();
    private static final PromotionService promotionService = new PromotionService();
    private static final MembershipService membershipService = new MembershipService();
    private static final PromotionConditionService promotionConditionService = new PromotionConditionService(orderService, storeService, promotionService);

    private static final OrderController orderController = new OrderController(orderService, storeService);
    private static final StoreController storeController = new StoreController(storeService);
    private static final PromotionController promotionController = new PromotionController(promotionService, promotionConditionService, orderService);
    private static final MembershipController membershipController = new MembershipController(membershipService);

    public static MainController createMainController() {
        return new MainController(orderController, storeController, promotionController, membershipController);
    }
}
