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

  public float finalPrice(int minutes) {

    float price = 0;
    int minutesToPrice = minutes;

    FeeUtility.sortFeesDescending(feesSet);

    for (Fee fee : feesSet) {
      if (minutesToPrice >= fee.getTimeFractionInMinutes()) {
        int units = minutesToPrice / fee.getTimeFractionInMinutes();
        price += units * fee.getTimeFractionPrice();
        minutesToPrice -= units * fee.getTimeFractionInMinutes();
      }
    }

    price = chargeLeftoverMinutes(price, minutesToPrice);
    price = fixOverprice(price, minutes);

    return price;
  }

  private float chargeLeftoverMinutes(float price, int minutesToPrice) {
    if (minutesToPrice > 0) {
      price += feesSet.get(feesSet.size() - 1).getTimeFractionPrice();
    }
    return price;
  }

  private float fixOverprice(float price, int minutes){

    FeeUtility.sortFeesAscending(feesSet);

    for (Fee fee : feesSet) {

      int rounding = (int) Math.ceil((float)minutes / (float) fee.getTimeFractionInMinutes());
      float priceRounded = fee.getTimeFractionPrice() * rounding;

      if (rounding > 0 && priceRounded < price) price = priceRounded;
    }
    return price;
  }

  public void addFee(Fee fee) {
    feesSet.add(fee);
  }

  public List<Fee> getFees(){
    return feesSet;
  }

  public void resetSingleton() {
    feesSet.clear();
    calendar = Calendar.getInstance();
  }
}
