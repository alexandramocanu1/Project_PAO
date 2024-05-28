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

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String userId = resultSet.getString("user_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                users.add(new User(userId, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
