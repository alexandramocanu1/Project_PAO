package persistence;

import model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User findUserById(String userId) {
        return users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public void updateUser(User user) {
        int index = findIndexById(user.getUserId());
        if (index >= 0) {
            users.set(index, user);
        }
    }

    public void deleteUser(String userId) {
        int index = findIndexById(userId);
        if (index >= 0) {
            users.remove(index);
        }
    }

    private int findIndexById(String userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                return i;
            }
        }
        return -1;
    }
}
