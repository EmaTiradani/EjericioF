package model;

public class JSkiRentingModule {
  private static JSkiRentingModule instance;
  private JSkiRentingModel JSkiRentingModel;

  private JSkiRentingModule() {
    JSkiRentingModel =  new JSkiRentingModelImpl();
  }

  public static JSkiRentingModule getInstance() {
    if(instance == null) {
      instance =  new JSkiRentingModule();
    }
    return instance;
  }

  public JSkiRentingModel getParkingModel() {
    return JSkiRentingModel;
  }
}
