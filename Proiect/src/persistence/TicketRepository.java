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
                String eventId = resultSet.getString("event_id");
                String eventName = resultSet.getString("event_name");
                String ticketId = resultSet.getString("ticket_id");
                String userId = resultSet.getString("user_id");
                tickets.add(new Ticket(eventId, eventName, ticketId, userId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
