package model;

import service.AuditService;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId; // Schimbat din String în int
    private String username;
    private String email;
    private String password;
    private String fullName;
    private List<Event> tickets;
    private String role;

    public User(int userId, String username, String email, String password, String fullName, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.tickets = new ArrayList<>();
        AuditService.logAction("UserCreated");
    }

    // Getters și Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Event> getTickets() {
        return tickets;
    }

    public void buyTicket(Event event) {
        tickets.add(event);
    }

    public void cancelTicket(Event event) {
        tickets.remove(event);
    }

    public void displayUserInfo() {
        System.out.println("User Details:");
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
        AuditService.logAction("displayUserInfo");
    }

    public int getId() {
        return userId;
    }
}
