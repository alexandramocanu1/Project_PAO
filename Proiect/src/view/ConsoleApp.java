package view;

import exceptions.InvalidDataException;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    private Scanner scanner = new Scanner(System.in);
    private List<Event> events = new ArrayList<>();
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
                int option = readOption(5);
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
        System.out.println("4. Logout");
        System.out.println("5. Exit");
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
                currentUser = null;
                System.out.println("Logged out successfully!");
                break;
            case 5:
                System.out.println("Exiting the application. Goodbye!");
                System.exit(0);
        }
    }

    private void viewAvailableEvents() {
        System.out.println("Viewing available events...");
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (Event event : events) {
                event.displayEventInfo();
            }
        }
    }

    private void buyTicket() {
        System.out.println("Buying ticket...");
    }

    private void cancelTicket() {
        System.out.println("Canceling ticket...");
    }
}
