package store.validator;

public class InputValidator {
    public static String OnlyYesOrNo(String answer) {
        if (answer.equals("Y") || answer.equals("N")) {
            return answer;
        }

        throw new IllegalArgumentException("[Error] 입력으로 'Y' 혹은 'N'을 입력해 주세요.");
    }
}
