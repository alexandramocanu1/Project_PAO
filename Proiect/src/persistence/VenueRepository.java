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
        String query = "SELECT * FROM Venues";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int venueId = resultSet.getInt("venue_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                int capacity = resultSet.getInt("capacity");
                venues.add(new Venue(venueId, name, address, capacity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }

    public void addVenue(Venue venue) {
        String sql = "INSERT INTO Venues (name, address, capacity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, venue.getName());
            stmt.setString(2, venue.getAddress());
            stmt.setInt(3, venue.getCapacity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Venue findVenueById(int venueId) {
        Venue venue = null;
        String query = "SELECT * FROM Venues WHERE venue_id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, venueId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                int capacity = resultSet.getInt("capacity");
                venue = new Venue(venueId, name, address, capacity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venue;
    }

    public void updateVenue(Venue venue) {
        String query = "UPDATE Venues SET name = ?, address = ?, capacity = ? WHERE venue_id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, venue.getName());
            statement.setString(2, venue.getAddress());
            statement.setInt(3, venue.getCapacity());
            statement.setInt(4, venue.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteVenue(int venueId) {
        String query = "DELETE FROM Venues WHERE venue_id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, venueId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

}
