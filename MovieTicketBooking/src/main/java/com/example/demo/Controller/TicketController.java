package com.example.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.TicketService;
import com.example.demo.model.Ticket;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // 1. Get all tickets
    @GetMapping
    public List<Ticket> getAllTickets() {

        return ticketService.getAllTickets();
    }

    // 2. Add ticket
    @PostMapping
    public Ticket addTicket(
            @Valid @RequestBody Ticket ticket) {

        return ticketService.addTicket(ticket);
    }

    // 3. Search ticket by ID
    @GetMapping("/{ticketId}")
    public Ticket getTicketById(
            @PathVariable int ticketId) {

        return ticketService.getTicketById(ticketId);
    }

    // 4. Update ticket
    @PutMapping("/{ticketId}")
    public Ticket updateTicket(
            @PathVariable int ticketId,
            @Valid @RequestBody Ticket ticket) {

        return ticketService.updateTicket(
                ticketId,
                ticket
        );
    }

    // 5. Delete ticket
    @DeleteMapping("/{ticketId}")
    public String deleteTicket(
            @PathVariable int ticketId) {

        ticketService.deleteTicket(ticketId);

        return "Ticket deleted successfully";
    }
}