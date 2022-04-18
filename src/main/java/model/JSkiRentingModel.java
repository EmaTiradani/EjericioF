package model;

import controller.JSkiRentingPriceUpdateListener;

public interface JSkiRentingModel {

  void calculatePrice(JSkiRentingPriceUpdateListener listener, int minutes);

  String getFormatedFees();

  String getFormatedTickets();

}
