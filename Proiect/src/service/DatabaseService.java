package service;

import persistence.*;

public class DatabaseService {

    public void displayUsers(UserRepository userRepository) {
        userRepository.getAllUsers().forEach(user -> user.displayUserInfo());
    }

    public void displayVenues(VenueRepository venueRepository) {
        venueRepository.getAllVenues().forEach(venue -> venue.displayVenueInfo());
    }

    public void displayEvents(EventRepository eventRepository) {
        eventRepository.getAllEvents().forEach(event -> event.displayEventInfo());
    }

    public void displayTickets(TicketRepository ticketRepository) {
        ticketRepository.getAllTickets().forEach(ticket -> ticket.displayTicketInfo());
    }

    public void displayReviews(ReviewRepository reviewRepository) {
        reviewRepository.getAllReviews().forEach(review -> review.displayReviewInfo());
    }

    public void displayReservations(ReservationRepository reservationRepository) {
        reservationRepository.getAllReservations().forEach(reservation -> reservation.displayReservationInfo());
    }

    public void displayDiscounts(DiscountRepository discountRepository) {
        discountRepository.getAllDiscounts().forEach(discount -> discount.displayDiscountInfo());
    }
}
