package store.view;

import store.model.Product;
import store.model.Receipt;

import java.util.List;

public class OutputView {
    private static final String WELCOME = "안녕하세요. W편의점입니다.\n" + "현재 보유하고 있는 상품입니다.\n";
    private static final String RECEIPT = """
            ===========W 편의점=============
            상품명		수량	금액
            콜라		3 	3,000
            에너지바 		5 	10,000
            ===========증	정=============
            콜라		1
            ==============================
            총구매액		8	13,000
            행사할인			-1,000
            멤버십할인			-3,000
            내실돈			 9,000
            """;


    public static void printWelcome() {
        System.out.println(WELCOME);
    }

    public static void printProductList(List<Product> products) {
        products.forEach(System.out::println);
    }

    public static void printReceipt(Receipt receipt) {
        System.out.println("===========W 편의점=============\n" + "상품명\t\t\t수량\t\t금액");
        for (Product item : receipt.getItems()) {
            System.out.printf("%-10s\t\t%-5d\t%,d%n", item.getName(), item.getQuantity(), item.getPrice()); // 실제 데이터를 반영해야 함
        }
        System.out.println("===========증\t정=============");
        for (Product promotionItem : receipt.getPromotionItems()) {
            System.out.printf("%-10s\t\t%-5d\n", promotionItem.getName(), promotionItem.getQuantity());
        }
        System.out.printf(
                        """
                        ==============================\n
                        총구매액\t\t\t%d\t\t%,d
                        행사할인\t\t\t\t\t%,d
                        멤버십할인\t\t\t\t\t%,d
                        내실돈\t\t\t\t\t%,d
                        """,
                receipt.getTotalQuantity(), receipt.getTotalAmount(), receipt.getPromotionDiscount(),
                receipt.getMembershipDiscount(), receipt.getFinalAmount()
        );
    }
}
