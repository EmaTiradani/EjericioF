package controller;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.JSkiRentingPriceControllerImpl;
import view.JSkiRentingPriceView;

public class Main {

  public static void main(String[] args) {

    JSkiRentingPriceControllerImpl parkingPriceController =
        new JSkiRentingPriceControllerImpl();

    JSkiRentingPriceView JSkiRentingPriceView =
        new JSkiRentingPriceView(parkingPriceController);

    parkingPriceController.setParkingPriceView(JSkiRentingPriceView);

    JFrame frame = new JFrame("");
    frame.setContentPane(JSkiRentingPriceView.content);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

  }
}
