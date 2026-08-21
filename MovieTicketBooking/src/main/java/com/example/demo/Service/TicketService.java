package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Exception.TicketNotFoundException;
import com.example.demo.model.Ticket;

@Service
public class TicketService {

    private List<Ticket> tickets = new ArrayList<>(Arrays.asList(

        new Ticket(101, "Avengers", "Sai", "A1", 250),
        new Ticket(102, "RRR", "Ravi", "B2", 300),
        new Ticket(103, "Pushpa", "Kiran", "C3", 280),
        new Ticket(104, "Bahubali", "Arun", "D4", 350)

    ));

    // 1. Get all tickets
    public List<Ticket> getAllTickets() {

        return tickets;
    }

    // 2. Add ticket
    public Ticket addTicket(Ticket ticket) {

        tickets.add(ticket);

        return ticket;
    }

    // 3. Search ticket by ID
    public Ticket getTicketById(int ticketId) {

        return tickets.stream()
                .filter(ticket -> ticket.getTicketId() == ticketId)
                .findFirst()
                .orElseThrow(() ->
                    new TicketNotFoundException(
                        "Ticket ID not found : " + ticketId
                    )
                );
    }

    // 4. Update ticket
    public Ticket updateTicket(
            int ticketId,
            Ticket updatedTicket) {

        Ticket ticket = getTicketById(ticketId);

        ticket.setMovieName(updatedTicket.getMovieName());
        ticket.setCustomerName(updatedTicket.getCustomerName());
        ticket.setSeatNumber(updatedTicket.getSeatNumber());
        ticket.setTicketPrice(updatedTicket.getTicketPrice());

        return ticket;
    }

    // 5. Delete ticket
    public boolean deleteTicket(int ticketId) {

        Ticket ticket = getTicketById(ticketId);

        tickets.remove(ticket);

        return true;
    }
}