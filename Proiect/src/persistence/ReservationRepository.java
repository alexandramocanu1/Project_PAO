package persistence;

import model.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    public Reservation findReservationById(String reservationId) {
        return reservations.stream()
                .filter(reservation -> reservation.getReservationId().equals(reservationId))
                .findFirst()
                .orElse(null);
    }

    public void updateReservation(Reservation reservation) {
        int index = findIndexById(reservation.getReservationId());
        if (index >= 0) {
            reservations.set(index, reservation);
        }
    }

    public void deleteReservation(String reservationId) {
        int index = findIndexById(reservationId);
        if (index >= 0) {
            reservations.remove(index);
        }
    }

    private int findIndexById(String reservationId) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId().equals(reservationId)) {
                return i;
            }
        }
        return -1;
    }
}
