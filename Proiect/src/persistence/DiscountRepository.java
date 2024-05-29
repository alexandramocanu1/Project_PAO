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
                String eventName = resultSet.getString("event_name");
                double discountPercentage = resultSet.getDouble("discount_percentage");
                discounts.add(new Discount(discountId, eventName, discountPercentage));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }
}
