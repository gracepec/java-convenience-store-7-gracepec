package store.service;

import store.validator.InputValidator;
import store.view.InputView;

import java.util.function.Supplier;

public class InputService {
    private <T> T inputLoop(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String readUserOrder() {
        return inputLoop(InputView::readUserOrder);
    }

    public String readPromotionConfirm() {
        return inputLoop(() -> {
            return InputValidator.OnlyYesOrNo(InputView.readPromotionConfirm());
        });
    }


}
