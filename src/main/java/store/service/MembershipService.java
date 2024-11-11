package store.service;

import store.model.Product;
import store.model.Promotion;

public class MembershipService {
    private final int discount;
    private boolean use;

    public MembershipService() {
        discount = -0;
        use = false;
    }

    public void setUse(String answer) {
        if (answer.equals("Y")) {
            use = true;
        }
        if (answer.equals("N")) {
            use = false;
        }
    }

    public int getDiscount(int afterTotal, PromotionService promotionService) {
        if (use) {
            return calculate(afterTotal, promotionService);
        }

        return discount;
    }

    private int calculate(int afterTotal, PromotionService promotionService) {
        for (Product product : promotionService.getPromotionItems()) {
            Promotion promotion = promotionService.findPromotionByName(product.getPromotion());
            int promotionTypeNumber = promotion.getBuyQuantity();
            afterTotal -= product.getQuantity() * (promotionTypeNumber + 1) * product.getPrice();
        }
        int calculatedDiscount = (int) (-1 * afterTotal * 0.3);

        return checkLimit(calculatedDiscount);
    }

    private int checkLimit(int calculatedDiscount) {
        if (calculatedDiscount < -8000) {
            return -8000;
        }

        return calculatedDiscount;
    }
}
