package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String USER_ORDER_PROMPT = "\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String PROMOTION_CONFIRM_PROMPT = "\n현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n";
    private static final String PROMOTION_UNAVAILABLE_PROMPT = "\n현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n";
    private static final String MEMBERSHIP_PROMPT = "\n멤버십 할인을 받으시겠습니까? (Y/N)";
    private static final String ANYTHING_ELSE_PROMPT = "\n감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";

    public static String readUserOrder() {
        System.out.println(USER_ORDER_PROMPT);
        return Console.readLine();
    }

    public static String readPromotionConfirm(String itemName) {
        System.out.printf(String.format(PROMOTION_CONFIRM_PROMPT, itemName));
        return Console.readLine();
    }

    public static String readPromotionUnavailable(String itemName, int quantity) {
        System.out.printf(String.format(PROMOTION_UNAVAILABLE_PROMPT, itemName, quantity));
        return Console.readLine();
    }

    public static String readMembershipUsed() {
        System.out.println(MEMBERSHIP_PROMPT);
        return Console.readLine();
    }

    public static String readAnythingElse() {
        System.out.println(ANYTHING_ELSE_PROMPT);
        return Console.readLine();
    }
}
