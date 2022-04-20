package fees;

public class StandardFee{

  private int timeFraction;
  private float fractionPrice;
  private Discount discount;

  public StandardFee(int timeFraction, float fractionPrice) {
    this.timeFraction = timeFraction;
    this.fractionPrice = fractionPrice;
  }


  public int getTimeFraction() {
    return timeFraction;
  }



  public float getFractionPrice() {
    return fractionPrice;
  }
}
