package persistence;

import model.Reservation;
import service.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservations";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String reservationId = resultSet.getString("reservation_id");
                String userId = resultSet.getString("user_id");
                String eventId = resultSet.getString("event_id");
                int numberOfTickets = resultSet.getInt("number_of_tickets");
                reservations.add(new Reservation(reservationId, userId, eventId, numberOfTickets, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO Reservations (user_id, event_id, number_of_tickets) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reservation.getUserId());
            stmt.setString(2, reservation.getEventId());
            stmt.setInt(3, reservation.getNumberOfTickets());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE Reservations SET user_id = ?, event_id = ?, number_of_tickets = ? WHERE reservation_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reservation.getUserId());
            stmt.setString(2, reservation.getEventId());
            stmt.setInt(3, reservation.getNumberOfTickets());
            stmt.setString(4, reservation.getReservationId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteReservation(String reservationId) {
        String sql = "DELETE FROM Reservations WHERE reservation_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reservationId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
