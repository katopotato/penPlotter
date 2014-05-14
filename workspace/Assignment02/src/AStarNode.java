/**
 * AStarNode to perform search
 * @author sara
 *
 */
public class AStarNode implements Comparable <AStarNode>{
	public AStarNode(Point from, Point to, double cn) {
		//this.hn = from.getH(); // have to recalculate heuristic to be from curr point
		//this.hn = (Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY()));
		this.cn = cn;
		this.total = cn + hn;
		this.point = from;
		System.out.println("Creating AStarNode with cn:" + this.cn + " total:" + this.total);
		System.out.print("from: ");
		from.print();
		//System
	}
	/**
	 * get point associated with current AStarNode
	 * @return
	 */
	public Point getPoint() {
		return this.point;
	}
	
	public void print() {
		getPoint().print();
		System.out.print("  this.nh:" + this.hn);
		System.out.print(" cn:" + this.cn);
		System.out.print("  total:" + this.total);
	}
	
	/**
	 * get cost of node
	 * @return
	 */
	public double getCn() {
		return this.cn;
	}
	
	/**
	 * Heuristic of AStarNode
	 * @return
	 */
	public double getHn() {
		return this.hn;
	}
	
	/**
	 * Get total for AStarNode
	 * @return
	 */
	public double getTotal() {
		return this.total;
	}
	private double cn;
	private static final double hn = 0;
	private double total;
	private Point point;
	@Override
	// changed from total
	public int compareTo(AStarNode other) {
		if (this.cn < other.getCn()) {
			return -1;
		}
		if (this.cn > other.getCn()) {
			return 1;
		}
		return 0;
	}
}
