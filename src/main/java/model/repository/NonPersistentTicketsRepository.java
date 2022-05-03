package model.repository;

import model.Ticket;

import java.util.ArrayList;

public class NonPersistentTicketsRepository implements TicketsRepository{

    ArrayList<Ticket> storage = new ArrayList<>();

    @Override
    public boolean storeTicket(Ticket ticket) {
        storage.removeIf(storedTicket -> storedTicket.hasSameID(ticket));
        storage.add(0, ticket);
        return true;
    }

    @Override
    public Ticket retrieveTicket(int ticketID) {
        return null;
    }

    public ArrayList<Ticket> getTickets(){
        return storage;
    }

    public void add(Ticket ticket){
        storage.add(ticket);
    }
}
