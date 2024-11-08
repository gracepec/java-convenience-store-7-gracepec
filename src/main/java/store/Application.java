package store;

import store.controller.StoreController;
import store.service.InputService;
import store.service.StoreService;

public class Application {
    public static void main(String[] args) {
        InputService inputService = new InputService();
        StoreService storeService = new StoreService();
        StoreController storeController = new StoreController(inputService, storeService);

        storeController.run();
    }
}
