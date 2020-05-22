import java.util.ArrayList;
import java.util.List;

public class Interval implements IntervalMBean {

    long lastTime;
    List<Long> timeIntervals;

    public Interval() {
        lastTime = System.currentTimeMillis();
        timeIntervals = new ArrayList<>();
    }

    public String getAvgInterval() {
        long avgInterval = 0;

        for (long shotInterval : timeIntervals) {
            avgInterval += shotInterval;
        }

        return avgInterval / (timeIntervals.size()) + " ms";
    }

    public void addPoint() {
        long currentShotTime = System.currentTimeMillis();

        timeIntervals.add(currentShotTime - lastTime);
        
        lastTime = currentShotTime;
    }
}