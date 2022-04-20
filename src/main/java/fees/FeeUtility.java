package fees;

import java.util.Comparator;
import java.util.List;

public class FeeUtility {
    public static String feeString(List<StandardFee> feesSet) {

        sortFeesAscending(feesSet);

        StringBuilder result = new StringBuilder();
        for (StandardFee fee : feesSet) {

            result
                    .append(minutesToHsMin(fee.getTimeFraction()))
                    .append(" - $")
                    .append(fee.getFractionPrice())
                    .append("\n");

        }
        return result.toString();
    }

    public static String minutesToHsMin(Integer minutes) {

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

    public static void sortFeesAscending(List<StandardFee> feesSet){
        feesSet.sort(new Comparator<StandardFee>() {
            @Override public int compare(StandardFee o1, StandardFee o2) {
                return o1.getTimeFraction() - o2.getTimeFraction();
            }
        });
    }

    public static void sortFeesDescending(List<StandardFee> feesSet){
        feesSet.sort(new Comparator<StandardFee>() {
            @Override public int compare(StandardFee o1, StandardFee o2) {
                return o2.getTimeFraction() - o1.getTimeFraction();
            }
        });
    }
}
