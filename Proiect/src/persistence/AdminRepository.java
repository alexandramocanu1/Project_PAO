package persistence;

import model.Admin;
import service.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    private static final String INSERT_ADMIN_SQL = "INSERT INTO Admins (user_id, level) VALUES (?, ?)";
    private static final String SELECT_ADMIN_BY_ID = "SELECT * FROM Admins WHERE user_id = ?";
    private static final String SELECT_ALL_ADMINS = "SELECT * FROM Admins";
    private static final String DELETE_ADMIN_SQL = "DELETE FROM Admins WHERE user_id = ?";
    private static final String UPDATE_ADMIN_SQL = "UPDATE Admins SET level = ? WHERE user_id = ?";

    public void insertAdmin(Admin admin) throws SQLException {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_SQL)) {
            preparedStatement.setInt(1, admin.getId());
            preparedStatement.setString(2, admin.getLevel());
            preparedStatement.executeUpdate();
        }
    }

    public Admin selectAdmin(int id) throws SQLException {
        Admin admin = null;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String level = rs.getString("level");
                admin = new Admin(id, null, null, null, null, null, level);
            }
        }
        return admin;
    }

    public List<Admin> selectAllAdmins() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADMINS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String level = rs.getString("level");
                admins.add(new Admin(id, null, null, null, null, null, level));
            }
        }
        return admins;
    }

    public boolean deleteAdmin(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateAdmin(Admin admin) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_SQL)) {
            statement.setString(1, admin.getLevel());
            statement.setInt(2, admin.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
