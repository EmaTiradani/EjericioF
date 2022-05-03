package model;

import Utils.WaitSimulator;
import fees.ComputeFee;
import fees.Utils.FeeUtility;
import model.repository.NonPersistentTicketsRepository;
import model.repository.TicketsRepository;


import java.util.ArrayList;

class JSkiRentingModelImpl implements JSkiRentingModel {
  //private ArrayList<Ticket> repo = new ArrayList<Ticket>();//TODO
  private TicketsRepository repo = new NonPersistentTicketsRepository();
  private ComputeFee computeFee = ComputeFee.getInstance();
  private ArrayList<JSkiRentingModelListener> listeners = new ArrayList<>();

  JSkiRentingModelImpl() { }

  @Override
  public void addListener(JSkiRentingModelListener listener) {

  }

  @Override public void calculatePrice(/*JSkiRentingPriceUpdateListener listener,*/ int minutes) {

    float price = computeFee.finalPrice(minutes);
    simulatedStoreinRepo(new Ticket(price, minutes));


    //TODO le avisa a la vista que realizo un cambio
    notifyListeners();
    //listener.didUpdateParkingPrice(price);
  }

  private void notifyListeners(){
    for(JSkiRentingModelListener listener : listeners){
      listener.didComputeFee();
    }
  }

  private void simulatedStoreinRepo(Ticket ticket){
    //Simulates the times it takes to store this in an external repo!!!
    new Thread(() -> {
        WaitSimulator.simulateLongWait();
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
    for(Ticket ticket: tickets) // TODO (creo que esta eh)
      formatedTickets += ticket.id + ": " + ticket.totalPrice + "$, " + ticket.minutesUsed + "mins\n";
    return formatedTickets;
  }

}
