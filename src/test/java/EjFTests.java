import controller.JSkiRentingPriceController;
import fees.ComputeFee;
import org.junit.Before;
import org.junit.Test;
import view.JSkiRentingPriceView;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class EjFTests {
    JSkiRentingPriceController parkingPriceController;
    JSkiRentingPriceView JSkiRentingPriceView;



    public void setUpNonMonday() {
        ComputeFee.getInstance().resetSingleton();
        ComputeFee.getInstance().setDate(new Date(1022, 4, 5));
        parkingPriceController = new JSkiRentingPriceController();
        JSkiRentingPriceView = new JSkiRentingPriceView(parkingPriceController);
        parkingPriceController.setParkingPriceView(JSkiRentingPriceView);
    }

    public void setUpMonday()  {
        ComputeFee.getInstance().resetSingleton();
        ComputeFee.getInstance().setDate(new Date(1022, 4, 4));
        parkingPriceController = new JSkiRentingPriceController();
        JSkiRentingPriceView = new JSkiRentingPriceView(parkingPriceController);
        parkingPriceController.setParkingPriceView(JSkiRentingPriceView);
    }

    @Test
    public void testBasicPrice0mins(){
        setUpNonMonday();
        parkingPriceController.onEventCalculate(0);
        assertEquals("$0.0", JSkiRentingPriceView.getShowedPrice());
    }

    @Test
    public void testBasicPrice15mins(){
        setUpNonMonday();
        parkingPriceController.onEventCalculate(15);
        assertEquals("$300.0", JSkiRentingPriceView.getShowedPrice());
    }

    @Test
    public void testBasicPrice1hs(){
        setUpNonMonday();
        parkingPriceController.onEventCalculate(1 * 60);
        assertEquals("$1000.0", JSkiRentingPriceView.getShowedPrice());
    }

    @Test
    public void testBasicPrice3hs(){
        setUpNonMonday();
        parkingPriceController.onEventCalculate(3 * 60);
        assertEquals("$2500.0", JSkiRentingPriceView.getShowedPrice());
    }

    @Test
    public void testBasicPrice5hs(){
        setUpNonMonday();
        parkingPriceController.onEventCalculate(5 * 60);
        assertEquals("$4000.0", JSkiRentingPriceView.getShowedPrice());
    }

    @Test
    public void testComboPrice1hs15mins(){
        setUpNonMonday();
        parkingPriceController.onEventCalculate(1 * 60 + 15);
        assertEquals("$1300.0", JSkiRentingPriceView.getShowedPrice());
    }

    @Test
    public void testComboPrice2hs15mins(){
        setUpNonMonday();
        parkingPriceController.onEventCalculate(2 * 60 + 15);
        assertEquals("$2300.0", JSkiRentingPriceView.getShowedPrice());
    }

    @Test
    public void testComboPrice4hs30mins(){
        setUpNonMonday();
        parkingPriceController.onEventCalculate(4 * 60 + 30);
        assertEquals("$4000.0", JSkiRentingPriceView.getShowedPrice());
    }

    @Test
    public void testComboPrice3LeftoverMins(){
        setUpNonMonday();
        parkingPriceController.onEventCalculate(1 * 60 + 33);
        assertEquals("$1900.0", JSkiRentingPriceView.getShowedPrice());
    }

    @Test
    public void testCombo9hsPriceOverflow(){
        setUpNonMonday();
        parkingPriceController.onEventCalculate(9 * 60);
        assertEquals("$7500.0", JSkiRentingPriceView.getShowedPrice());
    }

    @Test
    public void testCombo9hsPriceOverflowMonday(){
        setUpMonday();
        parkingPriceController.onEventCalculate(9 * 60);
        assertEquals("$6000.0", JSkiRentingPriceView.getShowedPrice());
    }


}
