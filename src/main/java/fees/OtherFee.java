package fees;

public class OtherFee extends Fee {

  public OtherFee(int timeFraction, float fractionPrice) {
    super(timeFraction, fractionPrice);
  }

  @Override public float getFractionPrice() {
    return super.getFractionPrice() * 0.8f;
  }
}
