package store;

import store.config.DIContainer;
import store.controller.MainController;

public class Application {
    public static void main(String[] args) {
        MainController mainController = DIContainer.createMainController();
        mainController.start();
    }
}
