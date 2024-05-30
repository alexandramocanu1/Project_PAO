package persistence;

import model.Event;
import service.DatabaseConfig;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM Events";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int eventId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                Date eventDate = resultSet.getDate("event_date");
                int venueId = resultSet.getInt("venue_id");
                events.add(new Event(eventId, name, type, eventDate.toLocalDate(), venueId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public Event findEventById(int eventId) {
        Event event = null;
        String query = "SELECT * FROM Events WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                Date eventDate = resultSet.getDate("event_date");
                int venueId = resultSet.getInt("venue_id");
                event = new Event(eventId, name, type, eventDate.toLocalDate(), venueId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    public void updateEvent(Event event) {
        String query = "UPDATE Events SET name = ?, type = ?, event_date = ?, venue_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, event.getName());
            statement.setString(2, event.getType());
            statement.setDate(3, Date.valueOf(event.getEventDate()));
            statement.setInt(4, event.getVenueId());
            statement.setInt(5, event.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEvent(Event event) {
        String sql = "INSERT INTO events (name, type, event_date, venue_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getType());
            stmt.setDate(3, Date.valueOf(event.getEventDate()));
            stmt.setInt(4, event.getVenueId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
