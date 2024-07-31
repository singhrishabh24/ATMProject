package com.atm;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private final Map<String, User> users;

    public UserDatabase() {
        users = new HashMap<>();
        // Sample users
        users.put("user1", new User("user1", "1234", 1000.00));
        users.put("user2", new User("user2", "5678", 500.00));
    }

    public boolean isValidUser(String userId, String pin) {
        User user = users.get(userId);
        if (user != null && user.checkPin(pin)) {
            return true;
        }
        return false;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}
