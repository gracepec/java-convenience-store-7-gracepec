package store.controller;

import store.service.OrderService;
import store.service.PromotionConditionService;
import store.service.PromotionService;
import store.util.InputUtil;
import store.validator.InputValidator;
import store.view.InputView;

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
        promotionService.loadPromotionsFromFile("promotions.md");

        for (String itemName : promotionConditionService.canGetMoreItems()) {
            offerFreeItemToUser(itemName);
        }

        for (String itemName : promotionConditionService.itemsWithoutPromotion()) {
            checkPurchaseWithoutPromotion(itemName, promotionConditionService.quantityWithoutPromotion(itemName));
        }
    }

    private void offerFreeItemToUser(String itemName) {
        InputUtil.doLoop(() -> {
            String answer = InputView.readPromotionConfirm(itemName);
            InputValidator.OnlyYesOrNo(answer);

            if (answer.equals("Y")) {
                orderService.plusOne(itemName);
            }

            return null;
        });
    }

    private void checkPurchaseWithoutPromotion(String itemName, int quantity) {
        InputUtil.doLoop(() -> {
            String answer = InputView.readPromotionUnavailable(itemName, quantity);
            InputValidator.OnlyYesOrNo(answer);

            if (answer.equals("Y")) {
                // orderService.(itemName); 결과값에 반영
            }

            return null;
        });
    }

}
