package fees;

public class MondaysFee extends Fee {

  public MondaysFee(int timeFraction, float fractionPrice) {
    super(timeFraction, fractionPrice);
  }

  @Override public float getFractionPrice() {
    return super.getFractionPrice() * 0.8f;
  }
}
