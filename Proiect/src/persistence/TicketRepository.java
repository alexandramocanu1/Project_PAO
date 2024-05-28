package persistence;

import model.Ticket;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    private List<Ticket> tickets = new ArrayList<>();

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public Ticket findTicketById(String ticketId) {
        return tickets.stream()
                .filter(ticket -> ticket.getTicketId().equals(ticketId))
                .findFirst()
                .orElse(null);
    }

    public void updateTicket(Ticket ticket) {
        int index = findIndexById(ticket.getTicketId());
        if (index >= 0) {
            tickets.set(index, ticket);
        }
    }

    public void deleteTicket(String ticketId) {
        int index = findIndexById(ticketId);
        if (index >= 0) {
            tickets.remove(index);
        }
    }

    private int findIndexById(String ticketId) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getTicketId().equals(ticketId)) {
                return i;
            }
        }
        return -1;
    }
}
