package persistence;

import model.RegularUser;
import service.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegularUserRepository {
    private static final String INSERT_REGULAR_USER_SQL = "INSERT INTO RegularUsers (user_id, subscription_type) VALUES (?, ?)";
    private static final String SELECT_REGULAR_USER_BY_ID = "SELECT * FROM RegularUsers WHERE user_id = ?";
    private static final String SELECT_ALL_REGULAR_USERS = "SELECT * FROM RegularUsers";
    private static final String DELETE_REGULAR_USER_SQL = "DELETE FROM RegularUsers WHERE user_id = ?";
    private static final String UPDATE_REGULAR_USER_SQL = "UPDATE RegularUsers SET subscription_type = ? WHERE user_id = ?";

    public void insertRegularUser(RegularUser regularUser) throws SQLException {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REGULAR_USER_SQL)) {
            preparedStatement.setInt(1, regularUser.getId());
            preparedStatement.setString(2, regularUser.getSubscriptionType());
            preparedStatement.executeUpdate();
        }
    }

    public RegularUser selectRegularUser(int id) throws SQLException {
        RegularUser regularUser = null;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REGULAR_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String subscriptionType = rs.getString("subscription_type");
                regularUser = new RegularUser(id, null, null, null, null, null, subscriptionType);
            }
        }
        return regularUser;
    }

    public List<RegularUser> selectAllRegularUsers() throws SQLException {
        List<RegularUser> regularUsers = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REGULAR_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String subscriptionType = rs.getString("subscription_type");
                regularUsers.add(new RegularUser(id, null, null, null, null, null, subscriptionType));
            }
        }
        return regularUsers;
    }

    public boolean deleteRegularUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_REGULAR_USER_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateRegularUser(RegularUser regularUser) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_REGULAR_USER_SQL)) {
            statement.setString(1, regularUser.getSubscriptionType());
            statement.setInt(2, regularUser.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
