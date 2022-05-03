package model;

import controller.JSkiRentingPriceUpdateListener;

public interface JSkiRentingModel {

  void addListener(JSkiRentingModelListener listener);

  void calculatePrice(/*JSkiRentingPriceUpdateListener listener,*/ int minutes);

  String getFormatedFees();

  String getFormatedTickets();

}
