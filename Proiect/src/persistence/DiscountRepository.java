package persistence;

import model.Discount;
import service.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepository {

    public List<Discount> getAllDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        String query = "SELECT * FROM Discounts";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String discountId = resultSet.getString("discount_id");
                double percentage = resultSet.getDouble("percentage");
                discounts.add(new Discount(discountId, null, percentage));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    public void addDiscount(Discount discount) {
        String sql = "INSERT INTO Discounts (code, percentage) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, discount.getDiscountId());
            stmt.setDouble(2, discount.getDiscountPercentage());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDiscount(Discount discount) {
        String sql = "UPDATE Discounts SET code = ?, percentage = ? WHERE discount_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, discount.getDiscountId());
            stmt.setDouble(2, discount.getDiscountPercentage());
            stmt.setString(3, discount.getDiscountId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteDiscount(String discountId) {
        String sql = "DELETE FROM Discounts WHERE discount_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, discountId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
