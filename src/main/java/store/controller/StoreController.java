package store.controller;

import store.view.InputView;

public class StoreController {
    private final InputView inputView;

    public StoreController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        inputView.readUserProducts();
    }
}
