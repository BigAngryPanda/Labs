public class Point {
	double x;
	double y;
	double r;
	boolean hit;

	public Point(double point_x, double point_y, double radius) throws IllegalArgumentException {
		if (radius < 0.0) {
			throw new IllegalArgumentException("Radius must not be negative number");
		}

		x = point_x;
		y = point_y;
		r = radius;
		hit = isInArea(x, y, r);
	}

	private boolean isInArea(double x, double y, double r) {
		return firstSegment(x, y, r) || secondSegment(x, y, r) || thirdSegment(x, y, r);
	}

	// x^2 + y^2 = r^2
	private boolean firstSegment(double x, double y, double r) {
		if (inInterval(x, 0.0, r) && inInterval(y, 0.0, Math.sqrt(r*r - x*x))) {
			return true;
		}
		else {
			return false;
		}
	}

	// y = x/2 + r/2
	private boolean secondSegment(double x, double y, double r) {
		if (inInterval(x, -r, 0.0) && inInterval(y, 0.0, 0.5*x + 0.5*r)) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean thirdSegment(double x, double y, double r) {
		if (inInterval(x, 0.0, r) && inInterval(y, -r, 0.0)) {
			return true;
		}
		else {
			return false;
		}
	}

	// lol ub (actually upper bound)
	private static boolean inInterval(double val, double lb, double ub) {
		if (val >= lb && val <= ub) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
    public String toString() {
        return String.format("Point(x: %f, y: %f, r: %f) Hit: %b", x, y, r, hit);
    }
}