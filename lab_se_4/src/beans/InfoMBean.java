import javax.management.NotificationBroadcaster;

public interface InfoMBean extends NotificationBroadcaster {
    int getTotalPointsCount();
    int getMissedPointsCount();
    void addResult(boolean result);
}