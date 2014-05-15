import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Represents the grid(x-y plane) of paper
 */
public class Plane {
	public Plane(){
		edges = new ArrayList<Edge>();
		plane = new HashMap<Integer, Integer>();
		points = new ArrayList<Point>();
		edgesHM = new HashMap<Point, Point>();
	}
	
	/**
	 * Adds a point to the x/y plane
	 * @param x x-coordinate of point to add
	 * @param y y-coordinate of point to add
	 */
	public Point addPoint(int x, int y) {
		Point newPoint = new Point(x, y);
		plane.put(x, y);
		boolean pointExists = false;
		// add new point to collection of points, as long as it hasn't been "seen" already
		for (Point p : points) {
			if ((p.getX() == x) && (p.getY() == y)) {
				// the point already exists
				pointExists = true;
			}
		}
		if (!(pointExists)) {
			points.add(newPoint);
		}
		return newPoint;
	}
	
	/**
	 * Add edge (linkage) between 2 points
	 * @param p1 first point to add
	 * @param p2 second point to add
	 */
	public void addEdge(Point p1, Point p2) {
		Edge e = new Edge(p1, p2);
		edges.add(e);
		edgesHM.put(p1, p2);
		/*System.out.print("added edge from: ");
		p1.print();
		System.out.print(" to ");
		p2.print();
		System.out.println();
	*/}
	
	/**
	 * Print all points in the plane
	 * Used for testing
	 */
	public void printPoints() {
		for (Point p: points) {
			p.print();
			System.out.println();
		}
	}
	
	/**
	 * Print all edges in the plane
	 * Used for testing
	 */
	public void printEdges() {
		for (Edge e: edges) {
			e.print();
			System.out.println();
		}
	}
	
	/**
	 * Return edges of a plane
	 * @return
	 */
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}
	/**
	 * Get points in a plane
	 * @return
	 */
	public ArrayList<Point> getPoints() {
		return this.points;
	}
	public boolean searchEdges(Point from, Point to) {
		// iterate over hashmap and see if it contains from/ to pair
		Iterator<Point> keySetIterator = edgesHM.keySet().iterator();

		while(keySetIterator.hasNext()){
		  Point key = keySetIterator.next();
		  Point value = edgesHM.get(key);
		  if (key.getX() == from.getX() && key.getY() == from.getY()){
			  value = edgesHM.get(key);
			if ((value.getX() == to.getX()) &&  (value.getY() == to.getY())){
				  return true;
		  	} else {
		  	}
		  } else if (value.getX() == from.getX() && value.getY() == from.getY()) {
			  if (key.getX() == to.getX() && key.getY() == to.getY()){
				  return true;
			  } else {
			  }
		  }
		}

		return false;
	}
	
	/**
	 * Get number of points in the plane
	 * @return
	 */
	public int getNumPoints() {
		return points.size();
	}
	/**
	 * Get number of edges in the plane
	 * @return
	 */
	public int getNumEdges() {
		return edges.size();
	}
	
	public ArrayList<Point> getNeighbours(Point point) {
		Iterator<Point> keySetIterator = edgesHM.keySet().iterator();
		ArrayList<Point> neighbours = new ArrayList<Point>();
		while(keySetIterator.hasNext()){
		  Point key = keySetIterator.next();
		  Point value = edgesHM.get(key);
			if ((key.getX() == point.getX()) && (key.getY() == point.getY())){
				neighbours.add((value));
		  	}  else if ((value.getX() == point.getX()) && (value.getY() == point.getY())) {
			  neighbours.add(key);
		  }
		}
		return neighbours;
	}
	/**
	 * Find distance between 2 points
	 * @param p1
	 * @param p2
	 * @return
	 */
	public double calculateDistance(Point p1, Point p2) {
		double length = Math.abs(p1.getX()-p2.getX());
		double height = Math.abs(p1.getY()-p2.getY());
		double distance = Math.sqrt((length*length) + (height*height));
		return distance;
	}
	private HashMap<Integer, Integer> plane;
	private ArrayList<Point> points;
	private ArrayList<Edge> edges;
	private HashMap<Point, Point> edgesHM;
}
