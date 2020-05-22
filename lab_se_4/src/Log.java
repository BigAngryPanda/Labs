import java.util.ArrayList;
import java.util.List;

public class Log {
	List<Point> history;
	Info info;
	Interval interval;

	public Log() {
		history = new ArrayList<>();
		info = new Info();
		interval = new Interval();
	}

	public void update(Point p) {
		history.add(p);
		info.addResult(p.hit);
		interval.addPoint();
	}

	public void setInfo(Info i) {
        info = i;
    } 

    public void setInterval(Interval i) {
        interval = i;
    } 

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Point p: history) {
            sb.append(p.toString()).append('\n');
        }

        return sb.toString();
    }
}