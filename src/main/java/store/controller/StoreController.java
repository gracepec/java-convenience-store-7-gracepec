package store.controller;

import store.service.OrderProductsService;
import store.service.StoreService;
import store.view.OutputView;

public class StoreController {
    private final StoreService storeService;
    private final OrderProductsService orderProductsService;

    public StoreController(StoreService storeService1, OrderProductsService orderProductsService) {
        this.storeService = storeService1;
        this.orderProductsService = orderProductsService;
    }

    public void welcomeStorePrintProducts() {
        OutputView.printWelcome();

        updateStoreProducts();

        OutputView.printProductList(storeService.getProducts());
    }

    public void updateStoreProducts() {
        if (storeService.getProducts().isEmpty()) {
            storeService.loadProductsFromFile("products.md");
            return;
        }
        storeService.updateProducts(orderProductsService);
    }
}
