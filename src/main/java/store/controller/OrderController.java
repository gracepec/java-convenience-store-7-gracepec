package store.controller;

import store.service.OrderService;
import store.service.StoreService;
import store.validator.OrderValidator;
import store.validator.StoreValidator;
import store.view.InputView;
import store.util.InputUtil;

public class OrderController {
    public void processUserOrder(OrderService orderService, StoreService storeService) {
        InputUtil.doLoop(() -> {
            String userOrder = InputView.readUserOrder();
            OrderValidator.orderFormat(userOrder);

            orderService.create(userOrder);

            StoreValidator.checkOrderProduct(orderService, storeService);

            return null;
        });
    }
}
