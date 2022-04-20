package fees;

import java.util.ArrayList;
import java.util.List;

public class Fee {

  private int timeFraction;
  private float fractionPrice;
  private List<Discount> discounts = new ArrayList<>();

  public Fee(int timeFraction, float fractionPrice) {
    this.timeFraction = timeFraction;
    this.fractionPrice = fractionPrice;
  }

  public int getTimeFraction() {
    return timeFraction;
  }

  public float getFractionPrice() {
    return applyDiscounts(fractionPrice);
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
