package service;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    // username → password
    private static final Map<String, String> users = new HashMap<>();

    // default admin user
    static {
        users.put("admin", "123");
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // already exists
        }
        users.put(username, password);
        return true;
    }

    public boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
