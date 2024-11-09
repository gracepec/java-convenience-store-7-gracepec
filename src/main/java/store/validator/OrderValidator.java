package store.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderValidator {
    private static final String ORDER_PATTERN = "^(\\[[가-힣a-zA-Z]+-\\d+\\])(,\\[[가-힣a-zA-Z]+-\\d+\\])*$";

    public static void orderFormat(String userOrder) {
        validateNull(userOrder);
        validatePattern(userOrder);
    }

    private static void validateNull(String userOrder) {
        if (userOrder == null || userOrder.trim().isEmpty()) {
            throw new IllegalArgumentException("[Error] 사용자의 입력이 비어 있습니다.");
        }
    }

    private static void validatePattern(String userOrder) {
        Pattern pattern = Pattern.compile(ORDER_PATTERN);
        Matcher matcher = pattern.matcher(userOrder);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }
}
