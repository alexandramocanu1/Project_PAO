package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId; // Schimbat din String în int
    private String username;
    private String email;
    private String password;
    private String fullName;
    private List<Event> tickets;

    public User(int userId, String username, String email, String password, String fullName) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.tickets = new ArrayList<>();
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

    public List<Event> getTickets() {
        return tickets;
    }

    public void buyTicket(Event event) {
        tickets.add(event);
    }

    public void cancelTicket(Event event) {
        tickets.remove(event);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public int getId() {
        return userId;
    }
}
