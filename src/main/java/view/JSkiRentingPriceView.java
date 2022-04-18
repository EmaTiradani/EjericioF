package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.JSkiRentingPriceController;
import model.JSkiRentingModel;
import model.JSkiRentingModule;

public class JSkiRentingPriceView {
  private JButton calculateBtn;
  private JLabel priceLbl;
  protected JPanel content;
  private JTextArea feesTextArea;
  private JSpinner spinnerHs;
  private JSpinner spinnerMins;
  private JTabbedPane tabbedPane1;
  private JTextArea transactionsTextArea;

  private JSkiRentingPriceController parkingPriceController;
  private JSkiRentingModel JSkiRentingModel = JSkiRentingModule.getInstance().getParkingModel();

  public JSkiRentingPriceView(JSkiRentingPriceController parkingPriceController) {

    this.parkingPriceController = parkingPriceController;

    initListeners();
    updatePriceField();


  }

  private void initListeners() {
    calculateBtn.addActionListener(e -> requestPrice());

    tabbedPane1.addChangeListener(changeEvent -> {
      if(tabbedPane1.getSelectedIndex() == 1) transactionsTextArea.setText(JSkiRentingModel.getFormatedTickets());
    });
  }

  private void updatePriceField() {
    feesTextArea.setText(JSkiRentingModel.getFormatedFees());
  }

  public void updatePriceResult(float price) {
    priceLbl.setText("$" + price);
  }

  public String getShowedPrice(){ return priceLbl.getText(); }

  private void requestPrice() {

    int hs = Integer.parseInt(spinnerHs.getValue().toString());
    if (hs < 0) {
      spinnerHs.setValue(0);
      hs = 0;
    }

    int mins = Integer.parseInt(spinnerMins.getValue().toString());
    if (mins < 0) {
      spinnerMins.setValue(0);
      mins = 0;
    }

    if (mins >= 60) {
      spinnerMins.setValue(mins % 60);
      mins = mins % 60;
    }

    parkingPriceController.onEventCalculate(hs * 60 + mins);
  }
}
