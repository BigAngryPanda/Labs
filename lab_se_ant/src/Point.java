class Point {
	double x;
	double y;
	double r;

	public Point(double point_x, double point_y, double radius) {
		if (radius < 0.0) {
			throw new IllegalArgumentException("Radius must not be negative number");
		}

		x = point_x;
		y = point_y;
		r = radius;
	}

	public boolean isInArea() {
		return firstSegment() || secondSegment() || thirdSegment();
	}

	// x^2 + y^2 = r^2
	private boolean firstSegment() {
		if (inInterval(x, 0.0, r) && inInterval(y, 0.0, Math.sqrt(r*r - x*x))) {
			return true;
		}
		else {
			return false;
		}
	}

	// y = x/2 + r/2
	private boolean secondSegment() {
		if (inInterval(x, -r, 0.0) && inInterval(y, 0.0, 0.5*x + 0.5*r)) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean thirdSegment() {
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
}
