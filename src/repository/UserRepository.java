package repository;

import entity.user.User;

import java.util.*;

public class UserRepository {
    private final List<User> users = new ArrayList<>();


    public void addData(User user) {
        users.add(user);
    }

    public void deleteByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                users.remove(user);

            }
        }
    }

    public Optional<User> getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return Optional.of(user);
            }
        } return Optional.empty();
    }

    public List<User> getAllUsers() {
        return users;
    }

}