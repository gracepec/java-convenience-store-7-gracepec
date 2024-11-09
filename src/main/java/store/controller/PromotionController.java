package store.controller;

import store.service.OrderService;
import store.service.PromotionConditionService;
import store.service.PromotionService;
import store.util.InputUtil;
import store.validator.InputValidator;
import store.view.InputView;

import java.util.List;

public class PromotionController {
    private final PromotionService promotionService;
    private final PromotionConditionService promotionConditionService;
    private final OrderService orderService;

    public PromotionController(PromotionService promotionService, PromotionConditionService promotionConditionService, OrderService orderService) {
        this.promotionService = promotionService;
        this.promotionConditionService = promotionConditionService;
        this.orderService = orderService;
    }

    public void processPromotion() {
        String promotionFileName = "promotions.md";
        promotionService.loadPromotionsFromFile(promotionFileName);

        List<String> freeItem = promotionConditionService.canGetMoreItems();

        for (String name : freeItem) {
            execute(name);
        }
    }

    private void execute(String itemName) {
        InputUtil.doLoop(() -> {
            String answer = InputView.readPromotionConfirm(itemName);
            InputValidator.OnlyYesOrNo(answer);

            if (answer.equals("Y")) {
                orderService.plusOne(itemName);
            }

            return null;
        });
    }

}
