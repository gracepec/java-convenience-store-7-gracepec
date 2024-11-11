package store.controller;

import store.service.OrderProductsService;
import store.service.OrderService;
import store.service.StoreService;
import store.validator.OrderValidator;
import store.validator.StoreValidator;
import store.view.InputView;
import store.util.InputUtil;

public class OrderController {
    private final OrderService orderService;
    private final StoreService storeService;
    private final OrderProductsService orderProductsService;

    public OrderController(OrderService orderService, StoreService storeService, OrderProductsService orderProductsService) {
        this.orderService = orderService;
        this.storeService = storeService;
        this.orderProductsService = orderProductsService;
    }

    public void processUserOrder() {
        InputUtil.doLoop(() -> {
            String userOrder = InputView.readUserOrder();
            OrderValidator.orderFormat(userOrder);

            orderService.create(userOrder);
            orderProductsService.takeOrder(storeService, userOrder);

            StoreValidator.checkOrderProduct(orderService, storeService);

            return null;
        });
    }
}
