package store;

import store.controller.StoreController;
import store.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        StoreController storeController = new StoreController(inputView);

        storeController.run();
    }
}
