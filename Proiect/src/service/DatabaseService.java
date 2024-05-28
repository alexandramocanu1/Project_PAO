package service;

import model.User;
import model.Venue;
import model.Event;
import model.Ticket;
import model.Review;
import model.Reservation;
import model.Discount;
import persistence.*;

import java.util.List;

public class DatabaseService {

    private UserRepository userRepository = new UserRepository();
    private VenueRepository venueRepository = new VenueRepository();
    private EventRepository eventRepository = new EventRepository();
    private TicketRepository ticketRepository = new TicketRepository();
    private ReviewRepository reviewRepository = new ReviewRepository();
    private ReservationRepository reservationRepository = new ReservationRepository();
    private DiscountRepository discountRepository = new DiscountRepository();

    public void displayUsers() {
        List<User> users = userRepository.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void displayVenues() {
        List<Venue> venues = venueRepository.getAllVenues();
        for (Venue venue : venues) {
            System.out.println(venue);
        }
    }

    public void displayEvents() {
        List<Event> events = eventRepository.getAllEvents();
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public void displayTickets() {
        List<Ticket> tickets = ticketRepository.getAllTickets();
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public void displayReviews() {
        List<Review> reviews = reviewRepository.getAllReviews();
        for (Review review : reviews) {
            System.out.println(review);
        }
    }

    public void displayReservations() {
        List<Reservation> reservations = reservationRepository.getAllReservations();
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public void displayDiscounts() {
        List<Discount> discounts = discountRepository.getAllDiscounts();
        for (Discount discount : discounts) {
            System.out.println(discount);
        }
    }
}
