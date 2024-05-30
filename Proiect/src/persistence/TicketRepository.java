package persistence;

import model.Ticket;
import service.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Tickets";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int eventId = resultSet.getInt("event_id");
                String eventName = resultSet.getString("event_name");
                int ticketId = resultSet.getInt("ticket_id");
                int userId = resultSet.getInt("user_id");
                double price = resultSet.getDouble("price");
                tickets.add(new Ticket(eventId, eventName, ticketId, userId, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        String sql = "INSERT INTO Tickets (event_id, event_name, user_id, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getEventId());
            stmt.setString(2, ticket.getEventName());
            stmt.setInt(3, ticket.getUserId());
            stmt.setDouble(4, ticket.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTicket(Ticket ticket) {
        String sql = "UPDATE Tickets SET event_id = ?, event_name = ?, user_id = ?, price = ? WHERE ticket_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getEventId());
            stmt.setString(2, ticket.getEventName());
            stmt.setInt(3, ticket.getUserId());
            stmt.setDouble(4, ticket.getPrice());
            stmt.setInt(5, ticket.getTicketId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteTicket(int ticketId) {
        String sql = "DELETE FROM Tickets WHERE ticket_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ticketId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
