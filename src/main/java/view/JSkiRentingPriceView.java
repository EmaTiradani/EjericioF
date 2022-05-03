package view;

import javax.swing.*;

import controller.JSkiRentingController;
import controller.JSkiRentingPriceControllerImpl;
import model.JSkiRentingModel;
import model.JSkiRentingModelListener;
import model.JSkiRentingModule;

public class JSkiRentingPriceView implements BaseView{
  private JButton calculateBtn;
  private JLabel priceLbl;
  public JPanel content;
  private JTextArea feesTextArea;
  private JSpinner spinnerHs;
  private JSpinner spinnerMins;
  private JTabbedPane tabbedPane1;
  private JTextArea transactionsTextArea;

  private JSkiRentingPriceControllerImpl parkingPriceController;
  private JSkiRentingModel JSkiRentingModel = JSkiRentingModule.getInstance().getParkingModel();

  public JSkiRentingPriceView(JSkiRentingPriceControllerImpl parkingPriceController) {

    this.parkingPriceController = parkingPriceController;

    initListeners();
    updatePriceField();


  }

  private void initListeners() {
    //calculateBtn.addActionListener(e -> requestPrice());
    calculateBtn.addActionListener(actionEvent -> parkingPriceController.onEventCalculate(Integer.parseInt(transactionsTextArea.getText())));

    JSkiRentingModel.addListener(new JSkiRentingModelListener() {
      @Override
      public void didComputeFee() {
        updatePriceField();
      }
    });

    tabbedPane1.addChangeListener(changeEvent -> {
      if(tabbedPane1.getSelectedIndex() == 1) transactionsTextArea.setText(JSkiRentingModel.getFormatedTickets());
    });
  }

  private void updatePriceField() {
    feesTextArea.setText(JSkiRentingModel.getFormatedFees());
  }

  /*public void updatePriceResult(float price) {
    priceLbl.setText("$" + price);
  }*/

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

  @Override
  public void startWaitingStatus() {

  }

  @Override
  public void stopWaitingStatus() {

  }


}
