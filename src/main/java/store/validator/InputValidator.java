package store.validator;

public class InputValidator {
    public static void OnlyYesOrNo(String answer) {
        checkNull(answer);

        if (answer.equals("Y") || answer.equals("N")) {
            return;
        }

        throw new IllegalArgumentException("[ERROR] 입력으로 'Y' 혹은 'N'을 입력해 주세요.");
    }

    private static void checkNull(String answer) {
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어 있습니다.");
        }
    }
}
