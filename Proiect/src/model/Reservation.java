package model;

import service.AuditService;
import java.util.Date;

public class Reservation {
    private String reservationId;
    private String userId;
    private String eventId;
    private int numberOfTickets;
    private Date reservationDate;

    public Reservation(String reservationId, String userId, String eventId, int numberOfTickets, Date reservationDate) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.eventId = eventId;
        this.numberOfTickets = numberOfTickets;
        this.reservationDate = reservationDate;
        AuditService.logAction("ReservationCreated");
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getEventId() {
        return eventId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
        AuditService.logAction("setReservationId");
    }

    public void setUserId(String userId) {
        this.userId = userId;
        AuditService.logAction("setUserId");
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
        AuditService.logAction("setEventId");
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
        AuditService.logAction("setNumberOfTickets");
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
        AuditService.logAction("setReservationDate");
    }

    public void displayReservationInfo() {
        System.out.println("Reservation Details:");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("User ID: " + userId);
        System.out.println("Event ID: " + eventId);
        System.out.println("Number of Tickets: " + numberOfTickets);
        System.out.println("Reservation Date: " + reservationDate);
        AuditService.logAction("displayReservationInfo");
    }
}
