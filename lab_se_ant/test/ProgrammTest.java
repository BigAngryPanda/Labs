import org.junit.Test;

import static org.junit.Assert.*;

public class ProgrammTest {
	private static Point zero = new Point(0.0, 0.0, 0.0);

	private static Point p_1 = new Point(1.0, 1.0, 2.0);
	private static Point p_2 = new Point(1.0, 1.0, 1.0);

	private static Point p_3 = new Point(-0.2, 0.1, 1.0);
	private static Point p_4 = new Point(-1.2, 0.1, 1.0);

	private static Point p_5 = new Point(1.0, -1.0, 2.0);
	private static Point p_6 = new Point(1.0, -10.0, 2.0);

	@Test
	public void testFirstAreaHit() {
		assertTrue(p_1.isInArea());
	}

	@Test
	public void testFirstAreaMiss() {
		assertFalse(p_2.isInArea());
	}

	@Test
	public void testSecondAreaHit() {
		assertTrue(p_3.isInArea());
	}

	@Test
	public void testSecondAreaMiss() {
		assertFalse(p_4.isInArea());
	}

	@Test
	public void testThirdAreaHit() {
		assertTrue(p_5.isInArea());
	}

	@Test
	public void testThirdAreaMiss() {
		assertFalse(p_6.isInArea());
	}

	@Test
	public void testZero() {
		assertTrue(zero.isInArea());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExeption() {
		Point wrong = new Point(1.0, 1.0, -1.0);
	}

	@Test
	public void testCorrectConstructor() {
		Point right = new Point(1.0, 1.0, 1.0);
	}

	@Test
	public void testTricky() {
		Point tricky = new Point(1.0, 1.0, 0.0);
	}
}