package fees;

public class Fee {

  private int timeFraction;
  private float fractionPrice;

  public Fee(int timeFraction, float fractionPrice) {
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
