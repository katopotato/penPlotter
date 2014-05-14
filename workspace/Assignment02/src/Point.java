/*
 * Point class to store x and y co ordinates respectively
 */
public class Point {
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
		this.h = (x + y)/2;
	}
	/**
	 * Get x-coordinate of point
	 * @return x coordinate of point 
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * Get y-coordinate of point
	 * @return y coordinate of point 
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Get heuristic of point
	 * @return heuristic of point 
	 */
	public int getH() {
		return this.h;
	}
	/**
	 * Print coordinates of a point
	 * used for testing
	 */
	public void print() {
		System.out.print("(" + getX() + ", "+ getY() + ")" + "   hn:" + getH());
	}
	
	/**
	 * Determine whether 2 points are equal
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean comparePoint (Point other) {
		if ((this.getX() == other.getX()) && (this.getY() == other.getY())) {
			return true;
		}
		return false;
	}
	private int x;
	private int y;
	private int h; // heuristic of current point from origin
}
