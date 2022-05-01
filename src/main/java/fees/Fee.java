package fees;

import java.util.ArrayList;
import java.util.List;

public class Fee {

  private int timeFractionInMinutes;
  private float timeFractionPrice;
  private List<Discount> discounts = new ArrayList<>();

  public Fee(int timeFraction, float fractionPrice) {
    this.timeFractionInMinutes = timeFraction;
    this.timeFractionPrice = fractionPrice;
  }

  public int getTimeFractionInMinutes() {
    return timeFractionInMinutes;
  }

  public float getTimeFractionPrice() {
    return applyDiscounts(timeFractionPrice);
  }

  public void addDiscount(Discount newDiscount){
    discounts.add(newDiscount);
  }

  private float applyDiscounts(float fractionPrice){
    for (Discount discount: discounts) {
      fractionPrice = discount.applyDiscount(fractionPrice);
    }
    return fractionPrice;
  }
}
