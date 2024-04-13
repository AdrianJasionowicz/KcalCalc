package repository;

import entity.user.User;

import java.util.*;

public class UserRepository {
    private List<User> users;
    private Map<String, User> usersMap;

    public UserRepository() {
        this.users = new ArrayList<>();
        this.usersMap = new HashMap<>();
    }

    public void addData(User user) {
        users.add(user);
        usersMap.put(user.getUsername(), user);

    }
public void deleteByUsername(String username) {
        usersMap.remove(username);
removeUserByUsername(username);
}
    public void removeUserByUsername(String username) {
        User removedUser = usersMap.remove(username);
        if (removedUser != null) {
            users.remove(removedUser);
        }

            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user.getUsername().equals(username)) {
                    iterator.remove();
                }
            }

    }
    public Optional<User> getUserByUsername(String username) {
        Optional<User> optionalUser = Optional.ofNullable(usersMap.get(username));

        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("No User found with this username");

        }
        return optionalUser;
    }

    public List<User> getAllUsers() {
        return users;
    }
}
/*

 public Optional<User> getUserByUsername(String username) {
        for (User user : users) {

            if (user.getLogin().equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();//todo refactor !!!!
    }
 */