package store.service;

import store.model.Order;
import store.validator.StoreValidator;

public class OrderService {
    public Order create(String orderString) {

        return parseOrder(orderString);
    }


    private Order parseOrder(String orderString) {
        // 문자열을 Order 객체로 파싱하는 로직 구현
        // 예시: "사이다-2,감자칩-1" -> Order 객체 생성
        return new Order(orderString);
    }
}
