package store.service;

import store.model.Product;
import store.model.Promotion;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.util.*;


public class PromotionConditionService {
    private final OrderService orderService;
    private final StoreService storeService;
    private final PromotionService promotionService;

    private final List<String> getMore = new ArrayList<>();
    private final Map<String, Integer> withoutPromotion = new LinkedHashMap<>();

    public PromotionConditionService(OrderService orderService, StoreService storeService, PromotionService promotionService) {
        this.orderService = orderService;
        this.storeService = storeService;
        this.promotionService = promotionService;
    }

    public List<Product> promotionProductsInOrder() {
        withoutPromotion.clear();
        return filterValid(storeService.getProducts().stream()
                .map(Product::new)
                .filter(product -> orderService.getOrder().getItems().containsKey(product.getName()))
                .filter(product -> !product.getPromotion().equals("null"))
                .toList()
        );
    }

    private List<Product> filterValid(List<Product> promotionProductsInOrder) {
        for (Product item : promotionProductsInOrder) {
            Promotion itemPromotion = matchingPromotion(item);

            if (!isDateValid(itemPromotion)) {
                return promotionProductsInOrder.stream()
                        .filter(product -> !product.getName().equals(item.getName()))
                        .toList();
            }

            int appliedPromotion = appliedPromotionCount(item, itemPromotion);
            item.setQuantity(appliedPromotion);
        }
        return promotionProductsInOrder;
    }

    private int appliedPromotionCount(Product item, Promotion itemPromotion) {
        int storeQuantity = item.getQuantity();
        int userQuantity = orderService.getOrder().getQuantity(item.getName());
        int typePromotion = itemPromotion.getBuyQuantity();

        if (userQuantity <= storeQuantity) {
            return userQuantity / (typePromotion + 1);
        }
        return storeQuantity / (typePromotion + 1);
    }

    public List<String> canGetMoreItems() {
        getMore.clear();
        Map<String, Integer> orderItems = orderService.getOrder().getItems();
        List<Product> applicableProducts = storeService.getProducts().stream()
                .map(Product::new)
                .filter(product -> orderItems.containsKey(product.getName()))
                .filter(product -> !product.getPromotion().equals("null"))
                .filter(product -> orderItems.get(product.getName()) < product.getQuantity())
                .toList();

        checkMore(applicableProducts);

        return getMore;
    }

    private void checkMore(List<Product> applicableProducts) {
        for (Product item : applicableProducts) {
            Promotion itemPromotion = matchingPromotion(item);

            if (isDateValid(itemPromotion)) {
                checkPromotionRemaining(item, itemPromotion);
            }
        }
    }

    private void checkPromotionRemaining(Product item, Promotion itemPromotion) {
        int userQuantity = orderService.getOrder().getQuantity(item.getName());
        int storeQuantity = item.getQuantity();
        int typePromotion = itemPromotion.getBuyQuantity();

        if (userQuantity <= storeQuantity) {
            if (userQuantity % (typePromotion + 1) == typePromotion) {
                getMore.add(item.getName());
                promotionService.setPromotionItemQuantity(item.getName(), userQuantity / (typePromotion+1));
            }
        }
    }

    public Set<String> itemsWithoutPromotion() {
        Map<String, Integer> orderItems = orderService.getOrder().getItems();
        List<Product> applicableProducts = storeService.getProducts().stream()
                .map(Product::new)
                .filter(product -> orderItems.containsKey(product.getName()))
                .filter(product -> !product.getPromotion().equals("null"))
                .filter(product -> orderItems.get(product.getName()) > product.getQuantity())
                .toList();

        checkWithoutPromotion(applicableProducts);

        return withoutPromotion.keySet();
    }

    private void checkWithoutPromotion(List<Product> applicableProducts) {
        for (Product item : applicableProducts) {
            Promotion itemPromotion = matchingPromotion(item);

            checkPromotionInsufficient(item, itemPromotion);
        }
    }

    private void checkPromotionInsufficient(Product item, Promotion itemPromotion) {
        int userQuantity = orderService.getOrder().getQuantity(item.getName());
        int storeQuantity = item.getQuantity();
        int typePromotion = itemPromotion.getBuyQuantity();

        if (userQuantity > storeQuantity) {
            int notApplicablePromotion = storeQuantity % (typePromotion + 1) + userQuantity - storeQuantity;
            withoutPromotion.put(item.getName(), notApplicablePromotion);
            promotionService.setPromotionItemQuantity(item.getName(), storeQuantity / (typePromotion+1));
        }
    }

    public int getQuantityWithoutPromotion(String itemName) {
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
}
