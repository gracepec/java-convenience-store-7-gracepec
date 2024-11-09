package store;

import store.controller.OrderController;
import store.controller.PromotionController;
import store.controller.StoreController;
import store.service.OrderService;
import store.service.PromotionConditionService;
import store.service.PromotionService;
import store.service.StoreService;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        StoreService storeService = new StoreService();
        PromotionService promotionService = new PromotionService();
        PromotionConditionService promotionConditionService = new PromotionConditionService(orderService, storeService, promotionService);
        OrderController orderController = new OrderController(orderService, storeService);
        StoreController storeController = new StoreController(storeService);
        PromotionController promotionController = new PromotionController(promotionService, promotionConditionService, orderService);

        OutputView.printWelcome();
        storeController.printStoreProducts();

        orderController.processUserOrder();
        promotionController.processPromotion();

    }
}
