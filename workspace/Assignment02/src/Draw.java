import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
/**
 * Draws the given plane
 * @author sara
 *
 */
public class Draw {
	public Draw(Plane plane) {
		// firstly add all the edges on a PointsToVisit array
		pointsToVisit = new ArrayList<AStarNode>();
		this.plane = plane;
		points = new ArrayList<Point>(plane.getPoints());
		pointsVisited = new ArrayList<Point>();
		edgeCount = 0;
		parentsVisited = new ArrayList<Point>();
		graphEdgesVisited = new ArrayList<Edge>();
		edgesVisited = new ArrayList<Edge>();
		neighbours = new ArrayList<Point>();
	}
	// performs the AstarSearch
	public void draw() {
		// origin is the starting point
		Point origin = new Point(0,0);
		boolean added = false; // keep track of adding to pointsToVisit
		for (Point p: points) {
			// add the point with heuristic then sort
			// create a new Edge
			if (p.getX() != origin.getX() && p.getY()!=origin.getY()){
			Edge e = new Edge(origin, p); // now have cost to current
				pointsToVisit.add(new AStarNode(p, origin, e.getCn())); //gives the cost, from the current node to dest p.
			}
		}
		Collections.sort(pointsToVisit, AStarNodeComparator);
		System.out.println("After sorting:");
		for (AStarNode a:pointsToVisit) {
			a.print();
			System.out.println();
		} 
		Point prevPoint = new Point(0, 0);
		// get the next point to visit
		System.out.println("plane.numedges:" + plane.getNumEdges());
		for (int i = 0; graphEdgesVisited.size() < plane.getNumEdges(); i ++) {
			System.out.println("\n\n\n\n");
			System.out.println("next point");
			System.out.println("graphedgesvisited:" + graphEdgesVisited.size());
			Point currPoint = (pointsToVisit.remove(0)).getPoint(); // this actually removes it
			if (currPoint.getX() == prevPoint.getX() && currPoint.getY() == prevPoint.getY()){
				currPoint = (pointsToVisit.remove(0)).getPoint();
				System.out.println("curr and pre are equal");
			}
			// add all neighbours of current node
			neighbours = plane.getNeighbours(currPoint); // connections
			System.out.println("The number of neighbours are:" + neighbours.size());
			for (Point n:neighbours) {
				n.print();
				System.out.println();
			}
			// add all of these neighbours
			pointsToVisit.clear();
			for (Point neighbour : neighbours) {
				if (!prevPoint.comparePoint(neighbour)) {
					pointsToVisit.add(new AStarNode(neighbour, currPoint, plane.calculateDistance(neighbour, currPoint)));
				}
			}
			// if points to visit is null:
			if (pointsToVisit.size() == 0) {
				/**
				 * TODO: all the edges/ possible connections of the currPoint
				 * have been explored
				 */
				neighbours = plane.getNeighbours(prevPoint);
				for (Point previousNeighbour:neighbours) {
					if (!previousNeighbour.comparePoint(currPoint)){
						System.out.print("adding the point:");
						currPoint.print();
						previousNeighbour.print();
						System.out.println("point added");
						pointsToVisit.add(new AStarNode(previousNeighbour, currPoint, plane.calculateDistance(currPoint, previousNeighbour)));
					}
				}
			}
			Collections.sort(pointsToVisit, AStarNodeComparator);
			System.out.println("Points to visit are:");
			for (AStarNode aStarNode : pointsToVisit) {
				aStarNode.print();
				System.out.println();
			}
			System.out.println("no more");
			
			/**
			 * Edge to have priority, if it is an edge
			 * make it zero heuristic
			 */
			
			/**
			 * TODO: fix implementation of searchEdges, 
			 * so that it only needs to be searched once, not twice
			 */
			for (Edge e : edgesVisited) {
				Point ePoint1 = e.getP1();
				Point ePoint2 = e.getP2();
				if ((ePoint1.getX() == currPoint.getX()) && (ePoint1.getY() == currPoint.getY()) &&
					(ePoint2.getX() == prevPoint.getX()) && (ePoint2.getY() == prevPoint.getY())) {
					currPoint = (pointsToVisit.remove(0)).getPoint();
					e.print();
					System.out.print("   has been visited already");
				} else if ((ePoint2.getX() == currPoint.getX()) && (ePoint2.getY() == currPoint.getY()) &&
						(ePoint1.getX() == prevPoint.getX()) && (ePoint1.getY() == prevPoint.getY())) {
					currPoint = (pointsToVisit.remove(0)).getPoint();
					e.print();
					System.out.print("   has been visited already");
				}
			}
			// make sure currPoint is not prevPoint OR
			// does not occur in visited
			parentsVisited.add(currPoint);
			System.out.print("Current point is:");
			currPoint.print();
			System.out.print("prev point is:");
			prevPoint.print();
			if(plane.searchEdges(currPoint, prevPoint)){
				// this edge has been visited
				// add to visited edges list
				graphEdgesVisited.add(new Edge(currPoint, prevPoint));
				System.out.println("edge is in graph");
			} 
			// search to see if edge has been added already
			for (Edge e : edgesVisited) {
				if ((e.getP1()).equals(currPoint) && (e.getP2()).equals(prevPoint)) {
					currPoint = (pointsToVisit.remove(0)).getPoint();
				} else if ((e.getP1()).equals(prevPoint) && (e.getP2()).equals(currPoint)) {
					currPoint = (pointsToVisit.remove(0)).getPoint();
				}
			}
			// have 2 check if 2 points are equal again
			if (currPoint.comparePoint(prevPoint)) {
				currPoint = (pointsToVisit.remove(0)).getPoint();
			}
			System.out.println();
			pointsVisited.add(currPoint);
			//pointsToVisit.clear();
			// add the node with the lowest cost
			// hash map it here, with its parent
			
			// add curr/prev to list of edges visited
			edgesVisited.add(new Edge(currPoint, prevPoint));
			System.out.println("The edges which have been visited already are:");
			for (Edge e : edgesVisited) {
				e.print();
				System.out.println();
			}
			prevPoint = currPoint;
			Collections.sort(pointsToVisit, AStarNodeComparator);
			// if curr and top is in edges, inc. edgeCount
			System.out.println("points to visit are:");
				for (AStarNode a:pointsToVisit) {
					a.print();
					System.out.println();
				} 
			
		}
	}
	private ArrayList<Point> neighbours;
	private ArrayList<Edge> edgesVisited;
	private ArrayList<Edge> graphEdgesVisited;
	private ArrayList<Point> parentsVisited;
	private int edgeCount;
	private ArrayList<Point> pointsVisited;
	private ArrayList<Point> points;
	private Plane plane;
	private ArrayList<AStarNode> pointsToVisit;
	private HashMap<Point, Point> pointsExplored; // point, parent
	public Comparator<AStarNode> AStarNodeComparator = new Comparator<AStarNode>() {
		public int compare (AStarNode p1, AStarNode p2) {
			return p1.compareTo(p2);
		}
	};
}
