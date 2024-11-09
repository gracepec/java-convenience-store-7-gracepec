package store.controller;

import store.service.StoreService;
import store.view.OutputView;

public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService1) {
        this.storeService = storeService1;
    }

    public void printStoreProducts() {
        String productFileName = "products.md";
        storeService.loadProductsFromFile(productFileName);

        OutputView.printProductList(storeService.getProducts());
    }
}
