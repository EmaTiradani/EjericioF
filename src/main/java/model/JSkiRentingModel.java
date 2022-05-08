package model;

public interface JSkiRentingModel {

  void addListener(JSkiRentingModelListener listener);

  void calculatePrice(int minutes);

  String getFormatedFees();

  String getFormatedTickets();

  float getFinalPrice();
}
