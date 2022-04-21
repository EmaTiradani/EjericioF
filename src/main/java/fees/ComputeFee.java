package fees;

import fees.Utils.FeeUtility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ComputeFee {

  private List<Fee> feesSet = new ArrayList<>();
  Calendar calendar = Calendar.getInstance();
  private static ComputeFee instance;

  private ComputeFee() { }

  public void setDate(Date d){
    calendar.setTime(d);
  }

  public static ComputeFee getInstance() {
    if (instance == null) {
      instance =  new ComputeFee();
    }
    return instance;
  }

  //This method calculates the final price, using the the fee data in the list
  public float price(int minutes) {

    float price = 0;
    int minutesToPrice = minutes;

    FeeUtility.sortFeesDescending(feesSet);

    // apply best fee in order. For example, 2hs 15min will apply 2 x 1hr fee + 1 x 15min fee
    for (Fee fee : feesSet) {
      if (minutesToPrice >= fee.getTimeFraction()) {
        int units = minutesToPrice / fee.getTimeFraction();
        price += units * fee.getFractionPrice();
        minutesToPrice -= units * fee.getTimeFraction();
      }
    }

    // Leftover minutes. For example, 1hs 3min, minutesToPrice = 3.
    // We need to charge an extra minimum fee (last one in the list)
    price = chargeLeftoverMinutes(price, minutesToPrice);
    
    // fix overprice. For example, 11hs = $660, but 12hs = $600
    price = fixOverprice(price, minutes);

    //This part was made by Estefanio, we dont get it but it totally works!!!

    return price;
  }

  private float fixOverprice(float price, int minutes){

    FeeUtility.sortFeesAscending(feesSet);

    for (Fee fee : feesSet) {

      // check price for full time fee. Example, 2hs 10min, check 3hs price
      int t = (int) Math.ceil((float)minutes / (float) fee.getTimeFraction());
      float p = fee.getFractionPrice() * t;

      if (t > 0 && p < price) price = p;
    }
    return price;
  }

  private float chargeLeftoverMinutes(float price, int minutesToPrice) {
    if (minutesToPrice > 0) {
      price += feesSet.get(feesSet.size() - 1).getFractionPrice();
    }
    return price;
  }

  public void addFee(Fee fee) {
    feesSet.add(fee);
  }

  public List<Fee> getFees(){
    return feesSet;
  }

  public String feeString() {
    return FeeUtility.feeString(feesSet); //TODO
  }

  public void resetSingleton() {
    feesSet.clear();
    calendar = Calendar.getInstance();
  }
}
