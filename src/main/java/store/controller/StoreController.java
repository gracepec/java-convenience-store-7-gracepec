package store.controller;

import store.service.InputService;
import store.service.StoreService;

public class StoreController {
    private final InputService inputService;
    private final StoreService storeService;

    public StoreController(InputService inputService,StoreService storeService1) {
        this.inputService = inputService;
        this.storeService = storeService1;
    }

    public void run() {
        String order = inputService.readUserOrder();

        if (storeService.checkPromotionQuantityRemaining(order)) {
            String answer = inputService.readPromotionConfirm();
        }
    }
}
