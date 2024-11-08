package store.service;

import store.model.Order;
import store.validator.InputValidator;
import store.validator.StoreValidator;
import store.view.InputView;

import java.util.function.Supplier;

public class InputService {
    public String readUserOrder() {
        return inputLoop(() -> {
            String userOrder = InputView.readUserOrder();

            InputValidator.isOrderFormat(userOrder);
            Order order = new Order(userOrder);

            StoreValidator.isStoreProduct(order);
            StoreValidator.isProductRemaining(order);

            return order;
        });
    }

    public String readPromotionConfirm() {
        return inputLoop(() -> {
            return InputValidator.OnlyYesOrNo(InputView.readPromotionConfirm());
        });
    }

    private <T> T inputLoop(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
