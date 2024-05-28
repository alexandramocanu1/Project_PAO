package persistence;

import model.Event;
import service.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String eventId = resultSet.getString("event_id");
                String name = resultSet.getString("name");
                String date = resultSet.getString("date");
                String location = resultSet.getString("location");
                int availableTickets = resultSet.getInt("available_tickets");
                events.add(new Event(eventId, name, date, location, availableTickets));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public Event findEventById(String eventId) {
        Event event = null;
        String query = "SELECT * FROM events WHERE event_id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String date = resultSet.getString("date");
                String location = resultSet.getString("location");
                int availableTickets = resultSet.getInt("available_tickets");
                event = new Event(eventId, name, date, location, availableTickets);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    public void updateEvent(Event event) {
        String query = "UPDATE events SET available_tickets = ? WHERE event_id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, event.getAvailableTickets());
            statement.setString(2, event.getEventId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
