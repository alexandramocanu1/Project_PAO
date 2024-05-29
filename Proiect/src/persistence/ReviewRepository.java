package persistence;

import model.Review;
import service.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM Reviews";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String reviewId = resultSet.getString("review_id");
                String eventId = resultSet.getString("event_id");
                String userId = resultSet.getString("user_id");
                int rating = resultSet.getInt("rating");
                String comment = resultSet.getString("comment");
                reviews.add(new Review(reviewId, eventId, userId, rating, comment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
