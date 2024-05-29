package persistence;

import model.User;
import service.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public void addUser(User user) {
        String query = "INSERT INTO Users (username, password, email, full_name, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFullName());
            statement.setString(5, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUserByNameAndPassword(String username, String password) {
        User user = null;
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userUsername = resultSet.getString("username");
                String userPassword = resultSet.getString("password");
                String userEmail = resultSet.getString("email");
                String userFullName = resultSet.getString("full_name");
                String userRole = resultSet.getString("role");
                user = new User(userId, userUsername, userEmail, userPassword, userFullName, userRole);
                System.out.println("User found: " + user.toString());
            } else {
                System.out.println("No user found with username: " + username + " and password: " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String fullName = resultSet.getString("full_name");
                String role = resultSet.getString("role");
                users.add(new User(userId, username, email, password, fullName, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void updateUser(User user) {
        String query = "UPDATE Users SET username = ?, password = ?, email = ?, full_name = ?, role = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFullName());
            statement.setString(5, user.getRole());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
