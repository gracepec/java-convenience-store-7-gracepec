package store.service;

public class PaymentService {

    public double membershipDiscount(int afterPromotionDiscount) {
        return afterPromotionDiscount * 0.7; //after에 적용 xx
    }
}
