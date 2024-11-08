package store;

import store.controller.OrderController;
import store.controller.StoreController;
import store.service.OrderService;
import store.service.StoreService;

public class Application {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        StoreService storeService = new StoreService();
        OrderController orderController = new OrderController();
        StoreController storeController = new StoreController(storeService);

        storeController.printStoreProducts();

        orderController.getUserOrder(orderService);

//
//    public void run() {
//        String userOrder = inputService.readUserOrder();
//        Order order = OrderService.create(userOrder);
//
//        if (storeService.checkPromotionQuantityRemaining(order)) {
//            String answer = inputService.readPromotionConfirm();
//        }
//    }
    }
}
