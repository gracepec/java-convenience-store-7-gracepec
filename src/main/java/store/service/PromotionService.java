package store.service;

import store.controller.StoreController;
import store.model.Product;
import store.model.Promotion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PromotionService {
    private final List<Promotion> promotions = new ArrayList<>();
    private List<Product> userOrderPromotionItems = new ArrayList<>();

    public void loadPromotionsFromFile(String fileName) {
        try (
                InputStream inputStream = StoreController.class.getClassLoader().getResourceAsStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            readFileLines(reader);
        } catch (IOException e) {
            throw new IllegalStateException("프로모션 정보를 불러오는 데 실패했습니다.");
        }
    }

    private void readFileLines(BufferedReader reader) {
        try {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                promotions.add(parseLineToProduct(line));
            }
        } catch (IOException e) {
            throw new IllegalStateException("파일 읽기 중 오류 발생");
        }
    }

    private Promotion parseLineToProduct(String line) {
        String[] fields = line.split(",");

        String name = fields[0];
        int buy = Integer.parseInt(fields[1]);
        int get = Integer.parseInt(fields[2]);
        LocalDate startDate = LocalDate.parse(fields[3]);
        LocalDate endDate = LocalDate.parse(fields[4]);

        return new Promotion(name, buy, get, startDate, endDate);
    }

    public Promotion findPromotionByName(String promotionName) {
        return promotions.stream()
                .filter(promotion -> promotion.getName().equals(promotionName))
                .findFirst()
                .orElse(null);
    }

    public void checkUserPromotion(PromotionConditionService promotionConditionService) {
        userOrderPromotionItems = promotionConditionService.promotionProductsInOrder();
    }

    public List<Product> getPromotionItems() {
        return userOrderPromotionItems;
    }

    public int getPromotionDiscount() {
        int discountAmount = 0;
        for (Product product : userOrderPromotionItems) {
            discountAmount = product.getQuantity() * product.getPrice();
        }
        return -1 * discountAmount;
    }
}
