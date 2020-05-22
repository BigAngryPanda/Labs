import javax.management.NotificationBroadcaster;

public interface InfoMBean extends NotificationBroadcaster {
    int getTotalPointsCount();
    int getHitPointsCount();
    void addResult(boolean result);
}