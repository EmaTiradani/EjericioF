package controller;

import fees.Discount;
import fees.Fee;
import fees.MondaysDiscount;
import model.JSkiRentingModel;
import model.JSkiRentingModule;
import fees.ComputeFee;
import view.JSkiRentingPriceView;


public class JSkiRentingPriceControllerImpl implements JSkiRentingController {

  private JSkiRentingModel JSkiRentingModel = JSkiRentingModule.getInstance().getParkingModel();
  private JSkiRentingPriceView JSkiRentingPriceView;
  private Thread taskThread;

  public JSkiRentingPriceControllerImpl() {
    initFees();
  }

  private void initFees() {

    ComputeFee.getInstance().addFee(new Fee(15, 300));
    ComputeFee.getInstance().addFee(new Fee(60, 1000));
    ComputeFee.getInstance().addFee(new Fee(5 * 60, 4000));
    ComputeFee.getInstance().addFee(new Fee(3 * 60, 2500));

    Discount discount = new MondaysDiscount();
    for (Fee fee: ComputeFee.getInstance().getFees()) {
      fee.addDiscount(discount);
    }
  }

  public void onEventCalculate(int minutes) {
    //JSkiRentingModel.calculatePrice(this, minutes);
    taskThread = new Thread(()-> {
      JSkiRentingPriceView.startWaitingStatus();//TODO
      JSkiRentingModel.calculatePrice(minutes);
      JSkiRentingPriceView.stopWaitingStatus();//TODO
    });
    taskThread.start();
  }

  public void setParkingPriceView(JSkiRentingPriceView JSkiRentingPriceView) {
    this.JSkiRentingPriceView = JSkiRentingPriceView;
  }

  /*@Override public void didUpdateParkingPrice(float price) {
    //JSkiRentingPriceView.updatePriceResult(price);
    JSkiRentingPriceView.updatePriceField();
  }*/
}

//TODO modelo solo procesa y avisa