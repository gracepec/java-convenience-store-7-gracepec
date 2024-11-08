package store.validator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputValidator {
    private static final String ORDER_PATTERN = "^(\\[[가-힣a-zA-Z]+-\\d+\\])(,\\s*\\[[가-힣a-zA-Z]+-\\d+\\])*$";

    public static boolean isOrderFormat(String orderString) {
        if (orderString == null || orderString.trim().isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(ORDER_PATTERN);
        Matcher matcher = pattern.matcher(orderString);
        return matcher.matches();
    }

    public static String OnlyYesOrNo(String answer) {
        if (answer.equals("Y") || answer.equals("N")) {
            return answer;
        }

        throw new IllegalArgumentException("[Error] 입력으로 'Y' 혹은 'N'을 입력해 주세요.");
    }
}
