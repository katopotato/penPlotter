
public class Edge {
	public Edge(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.cn = calculateCost(p1, p2);
	}
	/**
	 * Calculate cost (distance) between 2 points
	 * Uses Pythagoras' theorem to find distance
	 * dist = sqrt(x^2 + y^2)
	 * @param p1 first point
	 * @param p2 second point
	 * @return Straight line distance between p1 and p2
	 */
	private double calculateCost(Point p1, Point p2){
		double distance = 0;
		double x = Math.abs(p1.getX()-p2.getX());
		double y = Math.abs(p1.getY()-p2.getY());
		distance = Math.sqrt((x*x) + (y*y));
		return distance;
	}
	
	/**
	 * Return first point of edge
	 */
	public Point getP1() {
		return this.p1;
	}
	
	/**
	 * Return second point of edge
	 */
	public Point getP2() {
		return this.p2;
	}
	
	/**
	 * Return cost of edge
	 * @return
	 */
	public double getCn() {
		return this.cn;
	}
	
	/**
	 * Print an edge
	 * p1 => p2 cost
	 * Used for testing
	 */
	public void print() {
		getP1().print();
		System.out.print(" => ");
		getP2().print();
		System.out.print("  with cost:" + getCn());
		// add all of these edges onto list???
	}
	private Point p1;
	private Point p2;
	private double cn;
}
