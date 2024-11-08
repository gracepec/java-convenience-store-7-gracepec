package store.controller;

import store.model.Order;
import store.service.OrderService;
import store.validator.OrderValidator;
import store.validator.StoreValidator;
import store.view.InputView;
import store.util.InputUtil;

public class OrderController {
    public Order getUserOrder(OrderService orderService) {
        return InputUtil.doLoop(() -> {
            String userOrder = InputView.readUserOrder();
            OrderValidator.orderFormat(userOrder);

            Order order = orderService.create(userOrder);

//            StoreValidator.isStoreProduct(order);
//            StoreValidator.isProductRemaining(order);

            return order;
        });
    }
}
