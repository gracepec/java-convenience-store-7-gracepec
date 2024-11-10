package store.controller;

import store.util.InputUtil;
import store.validator.InputValidator;
import store.view.InputView;

public class MainController {
    private final OrderController orderController;
    private final StoreController storeController;
    private final PromotionController promotionController;
    private final MembershipController membershipController;
    private final ReceiptController receiptController;

    public MainController(OrderController orderController, StoreController storeController,
                          PromotionController promotionController, MembershipController membershipController,
                          ReceiptController receiptController) {
        this.orderController = orderController;
        this.storeController = storeController;
        this.promotionController = promotionController;
        this.membershipController = membershipController;
        this.receiptController = receiptController;
    }

    public void start() {
        boolean continueOrdering = true;

        while (continueOrdering) {
            storeController.welcomeStorePrintProducts();
            orderController.processUserOrder();
            promotionController.processPromotion();
            membershipController.processMembership();
            receiptController.printReceipt();

            continueOrdering = askAnythingElse();
        }
    }

    private boolean askAnythingElse() {
        return InputUtil.doLoop(() -> {
            String answer = InputView.readAnythingElse();
            InputValidator.OnlyYesOrNo(answer);

            return answer.equals("Y");
        });
    }
}
