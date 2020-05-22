import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Info extends NotificationBroadcasterSupport implements InfoMBean {
    int totalPoints;
    int hitPoints;
    int missedInRowPoints;
    int sequenceNumber;

    public Info() {
        totalPoints = 0;
        hitPoints = 0;
        missedInRowPoints = 0;
        sequenceNumber = 1;
    }

    public int getTotalPointsCount() {
        return totalPoints;
    }

    public int getHitPointsCount() {
        return hitPoints;
    }

    public void addResult(boolean result) {
        totalPoints += 1;

        if (result) {
            missedInRowPoints = 0;
            ++hitPoints;
        } else {
            ++missedInRowPoints;

            if (missedInRowPoints == 2) {
                // https://docs.oracle.com/javase/7/docs/api/javax/management/AttributeChangeNotification.html
                Notification n = new AttributeChangeNotification(
                        this,                       // source
                        sequenceNumber++,           // ord number
                        System.currentTimeMillis(), // timestamp
                        "Missed 2 points in a row", // message
                        "missedInRowPoints",        // var name
                        "int",                      // var type
                        3,                          // oldValue - An object representing value of the attribute before the change.
                        4                           // newValue - An object representing value of the attribute after the change.
                        );

                missedInRowPoints = 0;
                sendNotification(n);
            }
        }
    }
}