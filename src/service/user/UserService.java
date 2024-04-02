package service.user;

import entity.user.User;
import repository.UserRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

public class UserService {
    private UserRepository userRepository;
    private User loggedUser = null;

    public void loadUsersFromFile() {
        try {
            File file = new File("users.txt");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 7) {
                        String username = parts[0];
                        String password = parts[1];
                        String name = parts[2];
                        String surname = parts[3];
                        double weight = Double.parseDouble(parts[4]);
                        double height = Double.parseDouble(parts[5]);
                        int age = Integer.parseInt(parts[6]);
                        User user = new User(username, password, name, surname, weight, height, age);
                        userRepository.addData(user);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getLoggedUser() {
        return Optional.ofNullable(loggedUser);
    }

    public User getLoggedUserOrThrow() {
        if (getLoggedUser().isEmpty()) {
            throw new IllegalStateException("NO LOGGED USER!");
        }
        return loggedUser;
    }

    public boolean login(String username, String password) {
        Optional<User> optionalUser = userRepository.getUserByUsername(username);

        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();
        if (!loggedUser.getPassword().equals(password)) {
            return false;
        }

        loggedUser = user;
        return true;
    }

    public void makeNewUser(String username, String password) {
        User user = new User(username, password);
        userRepository.addData(user);
    }


    public void saveAllToFile() {
        try {
            File file = new File("users.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter writer = new PrintWriter(fileWriter);
            for (User user : userRepository.getAllUsers()) {
                if (!userExistsInFile(user)) {
                    String tekst = user.getLogin() + "," + user.getPassword() + "," + user.getName() + "," + user.getSurname() + "," + user.getWeight() + "," + user.getHeight() + "," + user.getAge();
                    writer.println(tekst);
                }
            }
            writer.close();
            fileWriter.close();
            System.out.println("Zapisano dane do pliku.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean userExistsInFile(User user) throws IOException {
        File file = new File("users.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 2 && parts[0].equals(user.getLogin())) {
                scanner.close();
                return true;
            }
        }
        scanner.close();
        return false;
    }

    public User fastUser(String usename, String password) {
        User user = new User(usename, password);
        return user;
    }

    public void updateLoggedUser(User updateData) {
        User logged = getLoggedUserOrThrow();
        logged.setName(updateData.getName());
        logged.setSurname(updateData.getSurname());
        logged.setWeight(updateData.getWeight());
        logged.setHeight(updateData.getHeight());
        logged.setAge(updateData.getAge());
        //todo pozostałe parametry
    }

    public void setUsername(String username) {
    userRepository.getUserByUsername(loggedUser.getUsername());

    }
}
/*

Exception in thread "main" java.lang.NullPointerException: Cannot invoke "entity.user.User.getPassword()" because "this.loggedUser" is null
at service.user.UserService.login(UserService.java:65)
at controller.user.UserController.authorization(UserController.java:38)
at controller.MainController.securityLogin(MainController.java:31)
at Main.main(Main.java:24)


public boolean login(String username, String password) {
        Optional<User> optionalUser = userRepository.getUserByUsername(username);

        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();
        if (!loggedUser.getPassword().equals(password)) {
            return false;
        }

        loggedUser = user;
        return true;
    }
 */