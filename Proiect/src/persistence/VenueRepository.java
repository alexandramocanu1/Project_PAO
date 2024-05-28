package persistence;

import model.Venue;
import service.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenueRepository {

    public List<Venue> getAllVenues() {
        List<Venue> venues = new ArrayList<>();
        String query = "SELECT * FROM venues";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String venueId = resultSet.getString("venue_id");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                venues.add(new Venue(venueId, name, location));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }
}
