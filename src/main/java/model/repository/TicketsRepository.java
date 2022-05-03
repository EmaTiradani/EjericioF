package model.repository;

import model.Ticket;

import java.util.ArrayList;

public interface TicketsRepository {

    public boolean storeTicket(Ticket ticket);

    public Ticket retrieveTicket(int ticketID);

    public void add(Ticket ticket);

    public ArrayList<Ticket> getTickets();
}
