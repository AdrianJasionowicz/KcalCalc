import controller.MainController;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        String content = "abc";
//        Optional<String> box = Optional.of("sdf");
        Optional<String> box = Optional.ofNullable(content);
//        Optional<String> box = Optional.empty();
        System.out.println(box);
        if (box.isPresent()) {
            String value = box.get();
            System.out.println(value);
        }

        box.ifPresent(text -> System.out.println(text) );





        MainController mainController = new MainController();
        mainController.securityLogin();
        mainController.run();
    }
}