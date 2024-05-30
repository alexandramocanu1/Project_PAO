package view;

import model.*;
import persistence.*;
import service.DatabaseService;

import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;


public class ConsoleApp {
    private static UserRepository userRepository = new UserRepository();
    private static DatabaseService databaseService = new DatabaseService();
    private static EventRepository eventRepository = new EventRepository();
    private static Scanner scanner = new Scanner(System.in);
    private static User loggedInUser = null;

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consuma newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("Welcome to the Ticketing System!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void login() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = userRepository.findUserByNameAndPassword(username, password);
        if (user != null) {
            loggedInUser = user;
            System.out.println("Login successful!");
            if ("admin".equals(user.getRole())) {
                showAdminMenu();
            } else {
                showUserMenu();
            }
        } else {
            System.out.println("Username or password incorrect!");
        }
    }

    private static void register() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        String role = "USER"; // Set default role to USER
        if ("Alexandra".equalsIgnoreCase(username)) {
            role = "ADMIN"; // Only "Alexandra" can be ADMIN
        }

        User user = new User(0, username, email, password, fullName, role);
        userRepository.addUser(user);
        System.out.println("Registration successful! Please login to continue.");
    }

    private static void showUserMenu() {
        while (true) {
            System.out.println("User Menu:");
            System.out.println("1. View available events");
            System.out.println("2. Buy ticket");
            System.out.println("3. Cancel ticket");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consuma newline

            switch (option) {
                case 1:
                    viewAvailableEvents();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    loggedInUser = null;
                    return;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void showAdminMenu() {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. View available events");
            System.out.println("2. Add event");
            System.out.println("3. Manage users");
            System.out.println("4. Manage venues");
            System.out.println("5. Manage tickets");
            System.out.println("6. Manage reservations");
            System.out.println("7. Manage discounts");
            System.out.println("8. Logout");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consuma newline

            switch (option) {
                case 1:
                    viewAvailableEvents();
                    break;
                case 2:
                    addEvent();
                    break;
                case 3:
                    manageUsers();
                    break;
                case 4:
                    manageVenues();
                    break;
                case 5:
                    manageTickets();
                    break;
                case 6:
                    manageReservations();
                    break;
                case 7:
                    manageDiscounts();
                    break;
                case 8:
                    loggedInUser = null;
                    return;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void viewAvailableEvents() {
        System.out.println("Viewing available events...");
        List<Event> events = databaseService.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (Event event : events) {
                event.displayEventInfo();
            }
        }
    }

    private static void addEvent() {
        System.out.print("Enter event name: ");
        String name = scanner.nextLine();
        System.out.print("Enter event type: ");
        String type = scanner.nextLine();
        System.out.print("Enter event date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter venue ID: ");
        int venueId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Event event = new Event(0, name, type, LocalDate.parse(date), venueId);
        eventRepository.addEvent(event);
        System.out.println("Event added successfully!");
    }

    private static void manageUsers() {
        System.out.println("Manage users:");
        List<User> users = userRepository.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        // Mai multă logică pentru gestionarea utilizatorilor poate fi adăugată aici
    }

    private static void manageVenues() {
        while (true) {
            System.out.println("Manage Venues:");
            System.out.println("1. Add venue");
            System.out.println("2. View venues");
            System.out.println("3. Update venue");
            System.out.println("4. Delete venue");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addVenue();
                    break;
                case 2:
                    viewVenues();
                    break;
                case 3:
                    updateVenue();
                    break;
                case 4:
                    deleteVenue();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addVenue() {
        System.out.print("Enter venue name: ");
        String name = scanner.nextLine();
        System.out.print("Enter venue address: ");
        String address = scanner.nextLine();
        System.out.print("Enter venue capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Venue venue = new Venue(0, name, address, capacity);
        VenueRepository venueRepository = new VenueRepository();
        venueRepository.addVenue(venue);
        System.out.println("Venue added successfully!");
    }

    private static void manageTickets() {
        TicketRepository ticketRepo = new TicketRepository();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Manage Tickets:");
            System.out.println("1. Add ticket");
            System.out.println("2. View tickets");
            System.out.println("3. Update ticket");
            System.out.println("4. Delete ticket");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter event ID: ");
                    int eventId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter event name: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    Ticket newTicket = new Ticket(eventId, eventName, 0, userId, price);
                    ticketRepo.addTicket(newTicket);
                    System.out.println("Ticket added successfully!");
                    break;
                case 2:
                    List<Ticket> tickets = ticketRepo.getAllTickets();
                    if (tickets.isEmpty()) {
                        System.out.println("No tickets available.");
                    } else {
                        for (Ticket ticket : tickets) {
                            ticket.displayTicketInfo();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter ticket ID to update: ");
                    int updateTicketId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new event ID: ");
                    int newEventId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new event name: ");
                    String newEventName = scanner.nextLine();
                    System.out.print("Enter new user ID: ");
                    int newUserId = scanner.nextInt();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    Ticket updatedTicket = new Ticket(newEventId, newEventName, updateTicketId, newUserId, newPrice);
                    ticketRepo.updateTicket(updatedTicket);
                    System.out.println("Ticket updated successfully!");
                    break;
                case 4:
                    System.out.print("Enter ticket ID to delete: ");
                    int deleteTicketId = scanner.nextInt();
                    if (ticketRepo.deleteTicket(deleteTicketId)) {
                        System.out.println("Ticket deleted successfully!");
                    } else {
                        System.out.println("Ticket not found!");
                    }
                    break;
                case 5:
                    return; // Exit to admin menu
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }


    private static void manageReservations() {
        ReservationRepository reservationRepo = new ReservationRepository();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Manage Reservations:");
            System.out.println("1. Add reservation");
            System.out.println("2. View reservations");
            System.out.println("3. Update reservation");
            System.out.println("4. Delete reservation");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter user ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter event ID: ");
                    String eventId = scanner.nextLine();
                    System.out.print("Enter number of tickets: ");
                    int numberOfTickets = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Reservation newReservation = new Reservation("0", userId, eventId, numberOfTickets, null);
                    reservationRepo.addReservation(newReservation);
                    System.out.println("Reservation added successfully!");
                    break;
                case 2:
                    List<Reservation> reservations = reservationRepo.getAllReservations();
                    if (reservations.isEmpty()) {
                        System.out.println("No reservations available.");
                    } else {
                        for (Reservation reservation : reservations) {
                            reservation.displayReservationInfo();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter reservation ID to update: ");
                    String updateReservationId = scanner.nextLine();
                    System.out.print("Enter new user ID: ");
                    String newUserId = scanner.nextLine();
                    System.out.print("Enter new event ID: ");
                    String newEventId = scanner.nextLine();
                    System.out.print("Enter new number of tickets: ");
                    int newNumberOfTickets = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Reservation updatedReservation = new Reservation(updateReservationId, newUserId, newEventId, newNumberOfTickets, null);
                    reservationRepo.updateReservation(updatedReservation);
                    System.out.println("Reservation updated successfully!");
                    break;
                case 4:
                    System.out.print("Enter reservation ID to delete: ");
                    String deleteReservationId = scanner.nextLine();
                    if (reservationRepo.deleteReservation(deleteReservationId)) {
                        System.out.println("Reservation deleted successfully!");
                    } else {
                        System.out.println("Reservation not found!");
                    }
                    break;
                case 5:
                    return; // Exit to admin menu
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }


    private static void manageDiscounts() {
        DiscountRepository discountRepo = new DiscountRepository();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Manage Discounts:");
            System.out.println("1. Add discount");
            System.out.println("2. View discounts");
            System.out.println("3. Update discount");
            System.out.println("4. Delete discount");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine()); // Citim întregul rând și convertim în întreg

            switch (choice) {
                case 1:
                    System.out.print("Enter discount code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter discount percentage: ");
                    double percentage = Double.parseDouble(scanner.nextLine()); // Citim și convertim la dublu
                    Discount newDiscount = new Discount(null, code, percentage);
                    discountRepo.addDiscount(newDiscount);
                    System.out.println("Discount added successfully!");
                    break;
                case 2:
                    List<Discount> discounts = discountRepo.getAllDiscounts();
                    if (discounts.isEmpty()) {
                        System.out.println("No discounts available.");
                    } else {
                        for (Discount discount : discounts) {
                            discount.displayDiscountInfo();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter discount ID to update: ");
                    String updateDiscountId = scanner.nextLine();
                    System.out.print("Enter new discount code: ");
                    String newCode = scanner.nextLine();
                    System.out.print("Enter new discount percentage: ");
                    double newPercentage = Double.parseDouble(scanner.nextLine()); // Citim și convertim la dublu
                    Discount updatedDiscount = new Discount(updateDiscountId, newCode, newPercentage);
                    discountRepo.updateDiscount(updatedDiscount);
                    System.out.println("Discount updated successfully!");
                    break;
                case 4:
                    System.out.print("Enter discount ID to delete: ");
                    String deleteDiscountId = scanner.nextLine();
                    if (discountRepo.deleteDiscount(deleteDiscountId)) {
                        System.out.println("Discount deleted successfully!");
                    } else {
                        System.out.println("Discount not found!");
                    }
                    break;
                case 5:
                    return; // Exit to admin menu
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }


    private static void buyTicket() {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine();
        Event event = eventRepository.findEventById(eventId);
        if (event != null) {
            loggedInUser.buyTicket(event);
            System.out.println(loggedInUser.getUsername() + " bought a ticket for " + event.getName());
        } else {
            System.out.println("Event not found!");
        }
    }

    private static void cancelTicket() {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine();
        Event event = eventRepository.findEventById(eventId);
        if (event != null) {
            loggedInUser.cancelTicket(event);
            System.out.println(loggedInUser.getUsername() + " cancelled a ticket for " + event.getName());
        } else {
            System.out.println("Event not found!");
        }
    }

    private static void viewVenues() {
        VenueRepository venueRepository = new VenueRepository();
        List<Venue> venues = venueRepository.getAllVenues();
        if (venues.isEmpty()) {
            System.out.println("No venues available.");
        } else {
            for (Venue venue : venues) {
                System.out.println(venue.toString());
            }
        }
    }

    private static void updateVenue() {
        System.out.print("Enter venue ID to update: ");
        int venueId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        VenueRepository venueRepository = new VenueRepository();
        Venue venue = venueRepository.findVenueById(venueId);
        if (venue != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new address: ");
            String address = scanner.nextLine();
            System.out.print("Enter new capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine();
            venue.setName(name);
            venue.setAddress(address);
            venue.setCapacity(capacity);
            venueRepository.updateVenue(venue);
            System.out.println("Venue updated successfully!");
        } else {
            System.out.println("Venue not found!");
        }
    }

    private static void deleteVenue() {
        System.out.print("Enter venue ID to delete: ");
        int venueId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        VenueRepository venueRepository = new VenueRepository();
        if (venueRepository.deleteVenue(venueId)) {
            System.out.println("Venue deleted successfully!");
        } else {
            System.out.println("Venue not found!");
        }
    }

}
