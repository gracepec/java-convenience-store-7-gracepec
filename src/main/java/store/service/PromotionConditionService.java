package store.service;

import store.model.Product;
import store.model.Promotion;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class PromotionConditionService {
    private final OrderService orderService;
    private final StoreService storeService;
    private final PromotionService promotionService;

    private final List<String> getMore = new ArrayList<>();
    private final Map<String, Integer> withoutPromotion = new HashMap<>();

    public PromotionConditionService(OrderService orderService, StoreService storeService, PromotionService promotionService) {
        this.orderService = orderService;
        this.storeService = storeService;
        this.promotionService = promotionService;
    }

    public List<String> canGetMoreItems() {
        Map<String, Integer> orderItems = orderService.getOrder().getItems();
        List<Product> applicableProducts = storeService.getProducts().stream()
                .filter(product -> orderItems.containsKey(product.getName()))
                .filter(product -> !product.getPromotion().equals("null"))
                .filter(product -> orderItems.get(product.getName()) < product.getQuantity())
                .toList();

        checkMore(applicableProducts);

        return getMore;
    }

    public Set<String> itemsWithoutPromotion() {
        Map<String, Integer> orderItems = orderService.getOrder().getItems();
        List<Product> applicableProducts = storeService.getProducts().stream()
                .filter(product -> orderItems.containsKey(product.getName()))
                .filter(product -> !product.getPromotion().equals("null"))
                .filter(product -> orderItems.get(product.getName()) > product.getQuantity())
                .toList();

        checkWithoutPromotion(applicableProducts);

        return withoutPromotion.keySet();
    }

    public int quantityWithoutPromotion(String itemName) {
        return withoutPromotion.get(itemName);
    }

    private Promotion matchingPromotion(Product item) {
        String promotionName = item.getPromotion();
        return promotionService.findPromotionByName(promotionName);
    }

    private boolean isDateValid(Promotion promotion) {
        LocalDate today = DateTimes.now().toLocalDate();
        return !today.isBefore(promotion.getStartDate()) && !today.isAfter(promotion.getEndDate());
    }

    private void checkMore(List<Product> applicableProducts) {
        for (Product item : applicableProducts) {
            Promotion itemPromotion = matchingPromotion(item);

            if (!isDateValid(itemPromotion) || !isPromotionRemaining(item, itemPromotion)) {
                return;
            }
        }
    }

    private void checkWithoutPromotion(List<Product> applicableProducts) {
        for (Product item : applicableProducts) {
            Promotion itemPromotion = matchingPromotion(item);

            if (!isDateValid(itemPromotion) || !isPromotionInsufficient(item)) {
                return;
            }
        }
    }

    private boolean isPromotionRemaining(Product item, Promotion itemPromotion) {
        int userQuantity = orderService.getOrder().getQuantity(item.getName());
        int typePromotion = itemPromotion.getBuyQuantity();

        if (userQuantity % typePromotion + 1 == typePromotion) {
            getMore.add(item.getName());
            return true;
        }

        return false;
    }

    private boolean isPromotionInsufficient(Product item) {
        int userQuantity = orderService.getOrder().getQuantity(item.getName());
        int storeQuantity = item.getQuantity();

        if (userQuantity > storeQuantity) {
            withoutPromotion.put(item.getName(), userQuantity - storeQuantity);
            return true;
        }

        return false;
    }
}
