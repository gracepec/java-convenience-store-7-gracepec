package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String USER_ORDER_PROMPT = "\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String PROMOTION_CONFIRM_PROMPT = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String PROMOTION_UNAVAILABLE_PROMPT = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private static final String MEMBERSHIP_PROMPT = "멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String ANYTHING_ELSE_PROMPT = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";

    public static String readUserOrder() {
        System.out.println(USER_ORDER_PROMPT);
        return Console.readLine();
    }

    public static String readPromotionConfirm() {
        System.out.println(PROMOTION_CONFIRM_PROMPT);
        return Console.readLine();
    }

    public String readPromotionUnavailable() {
        System.out.println(PROMOTION_UNAVAILABLE_PROMPT);

        return Console.readLine();
    }



//    """
//    - [ ] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력받는다.
//      - Y: 증정 받을 수 있는 상품을 추가한다.
//      - N: 증정 받을 수 있는 상품을 추가하지 않는다.
//    - [ ] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 입력받는다.
//      - Y: 일부 수량에 대해 정가로 결제한다.
//      - N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.
//    - [ ] 멤버십 할인 적용 여부를 입력 받는다.
//      - Y: 멤버십 할인을 적용한다.
//      - N: 멤버십 할인을 적용하지 않는다.
//    - [ ] 추가 구매 여부를 입력 받는다.
//      - Y: 재고가 업데이트된 상품 목록을 확인 후 추가로 구매를 진행한다.
//      - N: 구매를 종료한다.
//    """
}
