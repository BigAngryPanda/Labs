import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Info extends NotificationBroadcasterSupport implements InfoMBean {
    public int totalPoints;
    public int missedPoints;
    public int missedInRowPoints;
    public int sequenceNumber;

    public Info() {
        totalPoints = 0;
        missedPoints = 0;
        missedInRowPoints = 0;
        sequenceNumber = 1;
    }

    public int getTotalPointsCount() {
        return totalPoints;
    }

    public int getMissedPointsCount() {
        return missedPoints;
    }

    public void addResult(boolean result) {
        totalPoints += 1;

        if (result) {
            missedInRowPoints = 0;
        } else {
            missedPoints += 1;
            missedInRowPoints += 1;

            if (missedInRowPoints == 2) {
                Notification n = new AttributeChangeNotification(
                        this,
                        sequenceNumber++,
                        System.currentTimeMillis(),
                        "Missed 2 points in a row",
                        "missedInRowPoints",
                        "int",
                        3,
                        4
                        );

                missedInRowPoints = 0;
                sendNotification(n);
            }
        }
    }
}