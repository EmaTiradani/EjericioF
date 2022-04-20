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
      //applyFee(fee);
      if (minutesToPrice / fee.getTimeFraction() > 0) {
        int units = minutesToPrice / fee.getTimeFraction();
        price += units * fee.getFractionPrice();
        minutesToPrice -= units * fee.getTimeFraction();
      }
    }

    // Leftover minutes. For example, 1hs 3min, minutesToPrice = 3.
    // We need to charge an extra minimum fee (last one in the list)
    if (minutesToPrice > 0) {
      price += feesSet.get(feesSet.size() - 1).getFractionPrice();
    }


    // fix overprice. For example, 11hs = $660, but 12hs = $600

    FeeUtility.sortFeesAscending(feesSet);

    //This part was made by Estefanio, we dont get it but it totally works!!!
    for (Fee fee : feesSet) {

      // check price for full time fee. Example, 2hs 10min, check 3hs price
      int t = (int) Math.ceil((float)minutes / (float) fee.getTimeFraction());
      float p = fee.getFractionPrice() * t;

      if (t > 0 && p < price) price = p;
    }

    return price;
  }

  public void addFee(Fee fee) {
    feesSet.add(fee);
  }

  public List<Fee> getFees(){
    return feesSet;
  }
  /*public void addFee(int minutes, float price) {
    *//*
    TODO uno o dos parametros mas, con eso se va a poder armar un Fee especial que calcule... etc etc
     *//*

    feesSet.add(new Fee(minutes, price));
    *//*int day = calendar.get(Calendar.DAY_OF_WEEK);

    //Checks whether the day is not Monday to add the normal fee, else in the weekend use the discounted rate

    if (day == Calendar.MONDAY) {
      feesSet.add(new MondaysFee(minutes, price));
    } else {
      feesSet.add(new Fee(minutes, price));
    }*//*
  }*/

  public String feeString() {
    return FeeUtility.feeString(feesSet); //TODO
    /*FeeUtility.sortFeesAscending(feesSet);

    StringBuilder result = new StringBuilder();
    for (Fee fee : feesSet) {

      result
          .append(FeeUtility.minutesToHsMin(fee.getTimeFraction()))
          .append(" - $")
          .append(fee.getFractionPrice())
          .append("\n");

    }
    return result.toString();*/
  }

  /*private void sortFeesAscending(){
    feesSet.sort(new Comparator<Fee>() {
      @Override public int compare(Fee o1, Fee o2) {
        return o1.getTimeFraction() - o2.getTimeFraction();
      }
    });
  }*/

  /*private void sortFeesDescending(){
    feesSet.sort(new Comparator<Fee>() {
      @Override public int compare(Fee o1, Fee o2) {
        return o2.getTimeFraction() - o1.getTimeFraction();
      }
    });
  }*/

  /*private String minutesToHsMin(Integer minutes) {

    StringBuilder timeString = new StringBuilder();

    int hs = minutes / 60;

    if (hs > 0) {
      timeString.append(hs).append("hs ");
    }

    int leftoverMins = minutes % 60;

    if (leftoverMins > 0) {
      timeString.append(leftoverMins).append("min");
    }

    return timeString.toString();
  }
  */
  public void resetSingleton() {
    feesSet.clear();
    calendar = Calendar.getInstance();
  }
}
