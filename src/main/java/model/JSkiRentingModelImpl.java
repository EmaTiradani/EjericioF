package model;

import Utils.WaitSimulator;
import fees.ComputeFee;
import fees.Utils.FeeUtility;
import model.repository.NonPersistentTicketsRepository;
import model.repository.TicketsRepository;


import java.util.ArrayList;

class JSkiRentingModelImpl implements JSkiRentingModel {
  private TicketsRepository repo = new NonPersistentTicketsRepository();
  private ComputeFee computeFee = ComputeFee.getInstance();
  private ArrayList<JSkiRentingModelListener> listeners = new ArrayList<>();
  private float finalPrice;

  JSkiRentingModelImpl() { }

  @Override
  public void addListener(JSkiRentingModelListener listener) {
    this.listeners.add(listener);
  }

  @Override public void calculatePrice(int minutes) {

    float price = computeFee.finalPrice(minutes);
    simulatedStoreInRepo(new Ticket(price, minutes));

    finalPrice=price;
    notifyListeners();
  }

  public float getFinalPrice(){
    return finalPrice;
  }

  private void notifyListeners(){
    for(JSkiRentingModelListener listener : listeners){
      listener.didComputeFee();
    }
  }

  private void simulatedStoreInRepo(Ticket ticket){
    //Simulates the times it takes to store this in an external repo!!!
    new Thread(() -> {
        WaitSimulator.simulateShortWait();
        repo.add(ticket); //TODO
    }).start();
  }

  @Override public String getFormatedFees() {
    return FeeUtility.feeString(computeFee.getFees());
  }

  @Override
  public String getFormatedTickets() {
    String formatedTickets = "";
    ArrayList<Ticket> tickets = repo.getTickets();
    for(Ticket ticket: tickets)
      formatedTickets += ticket.id + ": " + ticket.totalPrice + "$, " + ticket.minutesUsed + "mins\n";
    return formatedTickets;
  }

}
