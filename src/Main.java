import controller.MainController;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {



        MainController mainController = new MainController();
        mainController.securityLogin();
        mainController.run();
    }
}