package store.view;

import store.model.Product;
import store.model.Receipt;

import java.util.List;

public class OutputView {
    private static final String WELCOME = "안녕하세요. W편의점입니다.\n" + "현재 보유하고 있는 상품입니다.\n";

    public static void printWelcome() {
        System.out.println(WELCOME);
    }

    public static void printProductList(List<Product> products) {
        products.forEach(System.out::println);
    }

    public static void printReceipt(Receipt receipt) {
        System.out.println("===========W 편의점=============\n" + "상품명\t\t\t수량\t\t금액");
        for (Product item : receipt.items()) {
            System.out.printf("%-12s\t%-5d\t%,d%n", item.getName(), item.getQuantity(), item.getPrice()); // 실제 데이터를 반영해야 함
        }
        System.out.println("===========증\t정=============");
        for (Product promotionItem : receipt.promotionItems()) {
            System.out.printf("%-12s\t%-5d\n", promotionItem.getName(), promotionItem.getQuantity());
        }
        System.out.printf(
                """
                        ==============================
                        총구매액\t\t\t%d\t\t%,d
                        행사할인\t\t\t\t\t%,d
                        멤버십할인\t\t\t\t\t%,d
                        내실돈\t\t\t\t\t%,d
                        """,
                receipt.totalQuantity(), receipt.totalAmount(), receipt.promotionDiscount(),
                receipt.membershipDiscount(), receipt.finalAmount()
        );
    }
}
