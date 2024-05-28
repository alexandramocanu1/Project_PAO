package persistence;

import model.Reservation;
import service.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationRepository {

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String reservationId = resultSet.getString("reservation_id");
                String userId = resultSet.getString("user_id");
                String eventId = resultSet.getString("event_id");
                int numberOfTickets = resultSet.getInt("number_of_tickets");
                Date reservationDate = resultSet.getDate("reservation_date");
                reservations.add(new Reservation(reservationId, userId, eventId, numberOfTickets, reservationDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
