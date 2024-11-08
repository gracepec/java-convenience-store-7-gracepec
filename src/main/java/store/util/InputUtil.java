package store.util;

import java.util.function.Supplier;

public class InputUtil {
    public static <T> T doLoop(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
