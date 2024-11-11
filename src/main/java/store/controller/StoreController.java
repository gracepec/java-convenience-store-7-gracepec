package store.controller;

import store.service.OrderProductsService;
import store.service.StoreService;
import store.view.OutputView;

public class StoreController {
    private final StoreService storeService;
    private final OrderProductsService orderProductsService;
    private boolean isFirstLoad = true;

    public StoreController(StoreService storeService1, OrderProductsService orderProductsService) {
        this.storeService = storeService1;
        this.orderProductsService = orderProductsService;
    }

    public void welcomeStorePrintProducts() {
        OutputView.printWelcome();

        if (isFirstLoad) {
            storeService.loadProductsFromFile("products.md");
            isFirstLoad = false;
        } else {
            storeService.updateProducts(orderProductsService);
        }

        OutputView.printProductList(storeService.getProducts());
    }
}
