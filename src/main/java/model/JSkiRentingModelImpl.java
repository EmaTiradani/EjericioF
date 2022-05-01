package model;

import Utils.WaitSimulator;
import controller.JSkiRentingPriceUpdateListener;
import fees.ComputeFee;
import fees.Utils.FeeUtility;


import java.util.ArrayList;

class JSkiRentingModelImpl implements JSkiRentingModel {
  private ArrayList<Ticket> repo = new ArrayList<Ticket>();
  private ComputeFee computeFee = ComputeFee.getInstance();

  JSkiRentingModelImpl() { }

  @Override public void calculatePrice(JSkiRentingPriceUpdateListener listener, int minutes) {

    float price = computeFee.finalPrice(minutes);
    simulatedStoreinRepo(new Ticket(price, minutes));

    listener.didUpdateParkingPrice(price);
  }

  private void simulatedStoreinRepo(Ticket ticket){
    //Simulates the times it takes to store this in an external repo!!!
    new Thread(() -> {
        WaitSimulator.simulateLongWait();
        repo.add(ticket);
    }).start();

  }

  @Override public String getFormatedFees() {
    return FeeUtility.feeString(computeFee.getFees());
  }

  @Override
  public String getFormatedTickets() {
    String formatedTickets = "";
    for(Ticket ticket: repo)
      formatedTickets += ticket.id + ": " + ticket.totalPrice + "$, " + ticket.minutesUsed + "mins\n";
    return formatedTickets;
  }

}
