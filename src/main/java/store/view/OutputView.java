package store.view;

import store.model.Product;
import java.util.List;

public class OutputView {
    private static final String WELCOME = "안녕하세요. W편의점입니다.\n" + "현재 보유하고 있는 상품입니다.\n";

    public static void printWelcome() {
        System.out.println(WELCOME);
    }

    public static void printProductList(List<Product> products) {
        products.forEach(System.out::println);
    }
}
