package fees;

import java.util.Calendar;

public class MondaysDiscount implements Discount {

    private static Calendar calendar = ComputeFee.getInstance().calendar;

    public float applyDiscount(float fractionPrice){
        return getDiscount()*fractionPrice;
    }

    public static float getDiscount(){

        int day = calendar.get(Calendar.DAY_OF_WEEK);

        if(day == Calendar.MONDAY) return  0.8f;
        else return 1f;
    }
}
