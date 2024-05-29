package view;

import model.Event;
import model.User;
import persistence.UserRepository;
import persistence.EventRepository;
import service.DatabaseService;

import java.util.List;
import java.util.Scanner;

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
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = userRepository.findUserByNameAndPassword(name, password);
        if (user != null) {
            loggedInUser = user;
            System.out.println("Login successful!");
            showUserMenu();
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

        User user = new User(0, username, email, password, fullName);
        userRepository.addUser(user);
        System.out.println("Registration successful! Please login to continue.");
    }

    private static void showUserMenu() {
        while (true) {
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
                    loggedInUser = null;
                    return;
                case 12:
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

    private static void buyTicket() {
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consuma newline
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
        scanner.nextLine(); // Consuma newline
        Event event = eventRepository.findEventById(eventId);
        if (event != null) {
            loggedInUser.cancelTicket(event);
            System.out.println(loggedInUser.getUsername() + " cancelled a ticket for " + event.getName());
        } else {
            System.out.println("Event not found!");
        }
    }
}
