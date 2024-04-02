package controller.user;

import entity.user.User;
import service.user.UserService;

import java.util.Scanner;

public class UserController {
    private final UserService userService;
    Scanner scanner = new Scanner(System.in);


    public UserController(UserService userService) {
        this.userService = userService;
        userService.loadUsersFromFile();
    }

    public void authorization() {
        System.out.println("By zalozyc konto wystarczy podac nazwe uzytkownika i haslo reszte uzupelnisz pozniej!");
        boolean shouldEnd = true;
        do {
            System.out.println(" ");
            System.out.println("1. Zaloguj się: ");
            System.out.println("2. Zaloz nowe konto: ");
            System.out.println("3. Zaloguj sie jako 'Jan' ");
            System.out.println("6. Koniec");
            System.out.println("  ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    scanner.nextLine();

                    System.out.println("Podaj login");
                    String userName = scanner.nextLine();
                    System.out.println("Podaj haslo");
                    String password = scanner.nextLine();
                    boolean login = userService.login(userName, password);
                    if (login) {
                        shouldEnd = false;
                        System.out.println("Witaj " + userName);
                    } else {
                        System.out.println("Podano nieprawidlowe dane");
                    }
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Podaj login");
                    String newUsername = scanner.nextLine();
                    System.out.println("Podaj haslo");
                    String newPassword = scanner.nextLine();
                    userService.makeNewUser(newUsername, newPassword);
                    boolean loginUser = userService.login(newUsername, newPassword);
                    if (loginUser) {
                        shouldEnd = false;
                        userService.saveAllToFile();
                    }
                    break;
                case 3:
                    System.out.println("Twoje dane to Jan, 123 ");
                    userService.login("Jan", "123");

                default:
                    System.out.println("Błąd sprobuj jeszcze raz");
                    userChoice = scanner.nextInt();
                    break;
            }
        } while (shouldEnd);
    }

    public void userSettings() {
        boolean shouldEnd = true;

        do {
            System.out.println("  ");
            System.out.println("1. Uzupelnij swoje dane");
            System.out.println("2. Zmien login");
            System.out.println("3. Zmien haslo");
            System.out.println("9. Powrot do glownego menu");
            System.out.println("  ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    scanner.nextLine();
                    System.out.println("Podaj swoje imie: ");
                    String name = scanner.nextLine();
                    System.out.println("Podaj swoje nazwisko: ");
                    String surname = scanner.nextLine();
                    System.out.println("Podaj wage: ");
                    double weight = scanner.nextDouble();
                    System.out.println("Podaj wzrost w metrach");
                    double height = scanner.nextDouble();
                    System.out.println("Ile masz lat?");
                    int age = scanner.nextInt();
                    User updateData = new User(null, null, name, surname, weight, height, age);
                    userService.updateLoggedUser(updateData);
                    System.out.println("Wszystie dane podano! :) ");
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Podaj nowy login: ");
                    String setUsername = scanner.nextLine();
                    userService.setUsername(setUsername);
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.println("Podaj stare haslo: ");
                    String password = scanner.nextLine();
                    boolean yesChangePassword = false;
                    boolean canI = userService.login(userService.getLoggedUserOrThrow().getUsername(), password);
                    System.out.println(canI);
                    if (canI) {
                        yesChangePassword = true;
                    }
                    if (yesChangePassword) {
                        System.out.println("Podaj nowe haslo: ");
                        userService.getLoggedUserOrThrow().setPassword(scanner.nextLine());
                    } else {
                        System.out.println("blad");
                    }
                    break;
                case 9:
                    shouldEnd = false;
                    userService.saveAllToFile();
                    break;
                default:
                    System.out.println("Sprobuj  jeszcze raz");
                    userChoice = scanner.nextInt();
            }
        } while (shouldEnd);
    }
}
