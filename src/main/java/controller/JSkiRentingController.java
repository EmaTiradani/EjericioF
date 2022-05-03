package controller;

import view.JSkiRentingPriceView;

public interface JSkiRentingController {

    void onEventCalculate(int minutes);

    void setParkingPriceView(JSkiRentingPriceView JSkiRentingPriceView);


}
