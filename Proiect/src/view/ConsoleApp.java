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
        // Logica pentru adăugarea unui eveniment
        System.out.println("Add event functionality to be implemented.");
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
        // Logica pentru gestionarea locațiilor
        System.out.println("Manage venues functionality to be implemented.");
    }

    private static void manageTickets() {
        // Logica pentru gestionarea biletelor
        System.out.println("Manage tickets functionality to be implemented.");
    }

    private static void manageReservations() {
        // Logica pentru gestionarea rezervărilor
        System.out.println("Manage reservations functionality to be implemented.");
    }

    private static void manageDiscounts() {
        // Logica pentru gestionarea reducerilor
        System.out.println("Manage discounts functionality to be implemented.");
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
