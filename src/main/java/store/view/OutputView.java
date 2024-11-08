package store.view;

import store.model.Product;
import java.util.List;

public class OutputView {
    public static void printProductList(List<Product> products) {
        products.forEach(System.out::println);
    }
}
