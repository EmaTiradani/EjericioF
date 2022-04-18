package Utils;

public class WaitSimulator {
    public static long timeBase = 1000;

    public static void simulateShortWait() {
        try {
            Thread.sleep(timeBase);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void simulateLongWait() {
        try {
            Thread.sleep(timeBase * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
