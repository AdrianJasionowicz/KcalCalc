package repository;

import entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void addData(User user) {
        users.add(user);
    }

    public String getUsername(String username) {
       //return getUserByUsername(username).getName();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getUsername();
            }
        }
        return null;
    }

    public Optional<User> getUserByUsername(String username) {
        for (User user : users) {
            if (user.getLogin().equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();//todo refactor !!!!
    }
    public List<User> getAllUsers() {
        return users;
    }

}
