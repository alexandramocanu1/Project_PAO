package view;

import model.Event;
import model.User;
import model.Ticket;
import persistence.EventRepository;
import service.AuditService;
import service.DatabaseService;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    private Scanner scanner = new Scanner(System.in);
    private EventRepository eventRepository = new EventRepository();
    private DatabaseService databaseService = new DatabaseService();
    private User currentUser;

    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        app.run();
    }

    public void run() {
        boolean loggedIn = false;
        while (true) {
            if (!loggedIn) {
                System.out.println("Welcome to the Ticketing System!");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                int option = readOption(3);
                switch (option) {
                    case 1:
                        login();
                        loggedIn = true;
                        break;
                    case 2:
                        register();
                        loggedIn = true;
                        break;
                    case 3:
                        System.out.println("Exiting the application. Goodbye!");
                        System.exit(0);
                }
            } else {
                showMenu();
                int option = readOption(12);
                executeOption(option);
            }
        }
    }

    private void login() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        currentUser = new User(name, email);
        System.out.println("Login successful!");
    }

    private void register() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        currentUser = new User(name, email);
        System.out.println("Registration successful!");
    }

    private void showMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. View available events");
        System.out.println("2. Buy ticket");
        System.out.println("3. Cancel ticket");
        System.out.println("4. View users");
        System.out.println("5. View venues");
        System.out.println("6. View events");
        System.out.println("7. View tickets");
        System.out.println("8. View reviews");
        System.out.println("9. View reservations");
        System.out.println("10. View discounts");
        System.out.println("11. Logout");
        System.out.println("12. Exit");
    }

    private int readOption(int maxOption) {
        int option = -1;
        while (option < 1 || option > maxOption) {
            System.out.print("Enter your choice: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option! Please enter a number.");
            }
        }
        return option;
    }

    private void executeOption(int option) {
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
                databaseService.displayUsers();
                break;
            case 5:
                databaseService.displayVenues();
                break;
            case 6:
                databaseService.displayEvents();
                break;
            case 7:
                databaseService.displayTickets();
                break;
            case 8:
                databaseService.displayReviews();
                break;
            case 9:
                databaseService.displayReservations();
                break;
            case 10:
                databaseService.displayDiscounts();
                break;
            case 11:
                currentUser = null;
                System.out.println("Logged out successfully!");
                break;
            case 12:
                System.out.println("Exiting the application. Goodbye!");
                System.exit(0);
        }
    }

    private void viewAvailableEvents() {
        System.out.println("Viewing available events...");
        List<Event> events = eventRepository.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (Event event : events) {
                event.displayEventInfo();
            }
        }
    }

    public void showAllEvents() {
        System.out.println("All Events:");
        for (Event event : eventRepository.getAllEvents()) {
            System.out.println("Event ID: " + event.getEventId());
            System.out.println("Name: " + event.getName());
            System.out.println("Date: " + event.getDate());
            System.out.println("Location: " + event.getLocation());
            System.out.println("Available Tickets: " + event.getAvailableTickets());
            System.out.println();
        }
    }

    private void buyTicket() {
        System.out.print("Enter event ID: ");
        String eventId = scanner.nextLine();
        Event event = eventRepository.findEventById(eventId);
        if (event != null) {
            if (event.getAvailableTickets() > 0) {
                // Decrement available tickets
                event.setAvailableTickets(event.getAvailableTickets() - 1);
                eventRepository.updateEvent(event);
                Ticket ticket = new Ticket(event.getEventId(), event.getName(), "TICKET_" + System.currentTimeMillis(), currentUser.getUserId());
                ticket.displayTicketInfo();
                AuditService.logAction("buyTicket");
            } else {
                System.out.println("Sorry, no tickets available for this event.");
            }
        } else {
            System.out.println("Event not found!");
        }
    }

    private void cancelTicket() {
        System.out.print("Enter event ID: ");
        String eventId = scanner.nextLine();
        Event event = eventRepository.findEventById(eventId);
        if (event != null) {
            // Increment available tickets
            event.setAvailableTickets(event.getAvailableTickets() + 1);
            eventRepository.updateEvent(event);
            AuditService.logAction("cancelTicket");
            System.out.println("Ticket canceled successfully!");
        } else {
            System.out.println("Event not found!");
        }
    }
}
