import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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
		nodesVisited = new HashMap<Point, Point>();
		numOfNodesTraversed = 0;
	}
	// performs the AstarSearch
	public void draw() {
		Plane drawn = new Plane(); // keeps track of what has been drawn already
		// origin is the starting point
		Point origin = new Point(0,0);
		boolean remove = false; // keep track of adding to pointsToVisit
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
		AStarNode currPoint = pointsToVisit.remove(0); // get first point to remove, should be (2,2)
		neighbours = plane.getNeighbours(currPoint.getPoint()); // (4,4)
		for (Point p:neighbours) {
			AStarNode temp = new AStarNode (p, currPoint.getPoint(),plane.calculateDistance(currPoint.getPoint(), p));
			pointsToVisit.add(temp);
		}
		
		Collections.sort(pointsToVisit, AStarNodeComparator);
		while (graphEdgesVisited.size() != plane.getNumEdges()) {
			Collections.sort(pointsToVisit, AStarNodeComparator);
			System.out.print("\n\n\n");
			prevPoint = currPoint.getPoint();
			currPoint = pointsToVisit.remove(0);
			
			// check that we aren't
			nodesVisited.put(currPoint.getPoint(), prevPoint);
			edgesVisited.add(new Edge (prevPoint, currPoint.getPoint()));
			System.out.print("curr point:");
			(currPoint.getPoint()).print();
			System.out.println();
			System.out.print("prev point");
			prevPoint.print();
			System.out.println();
			pointsVisited.add(prevPoint); // add previous point to points seen
			// check that prevPoint and currPoint form an edge on the graph
			if(plane.searchEdges(prevPoint, currPoint.getPoint())) {
				// add to graph edges visited
				System.out.println("Visited a graph edge1");
				graphEdgesVisited.add(new Edge(prevPoint, currPoint.getPoint()));
			}
			// check if prevpoint and currpoint form an edge visited already
			//Edge edgeTemp = new Edge(prevPoint, currPoint.getPoint());
			System.out.println("Points to visit are:");
			for (AStarNode t:pointsToVisit) {
				t.print();
			}
			if (!drawn.searchEdges(prevPoint, currPoint.getPoint())) {
				drawn.addEdge(prevPoint, currPoint.getPoint());	
			} else { //  implication; edge has been drawn already
				System.out.println();
				System.out.println("KKKKKKKKK");
				System.out.println();
				if (pointsToVisit.size() != 0) {
					currPoint = pointsToVisit.remove(0);
				} else {
					// no more nodes left in points to visit
					// keep back tracking until we find a one reachable
					System.out.println("previous nodes are:");
					System.out.println("numOfNodesTraversed=" + numOfNodesTraversed + " size:" + pointsVisited.size());
					pointsVisited.get(numOfNodesTraversed).print();
					System.out.println();
					pointsVisited.get(numOfNodesTraversed-1).print();
					System.out.println();

					pointsVisited.get(numOfNodesTraversed-2).print();
					System.out.println();
					// have to keep backtracking until we can find a neighbour that has nodes
					// able to be visited
					/**
					 * TODO:
					  from here delete ones that have been visited already etc.
					 * -> delete current nodes
					 * -> delete all edges which have been visited already
					 * from possible points, take in nodesVisited
					 * }
					 */
					boolean found = false;
					 for (int j = 0; !found; j++) {
						  	Point possibleNext = pointsVisited.get(numOfNodesTraversed - j);
						  	ArrayList<Point> possiblePoints = plane.getNeighbours(possibleNext);
						  	for (Point p:possiblePoints) {
								// recurse all the edges
								for (Edge e: edgesVisited) {
									if (possibleNext.comparePoint(e.getP1())) {
										// current possible point has an edge explored
										if (possiblePoints.contains(e.getP2())) {
											possiblePoints.remove(e.getP2());
											break;
										}
									}
								}
							}
						  	if (possiblePoints.size() > 0) {
						  		found = true;
						  		System.out.println("Possible points are:");
						  		for (Point a:possiblePoints) {
						  			a.print();
						  			System.out.println();
						  		}
						  	}
					 }
				}
			}
			
			neighbours.clear();
			neighbours = plane.getNeighbours(currPoint.getPoint()); 
			pointsToVisit.clear();
			for (Point p:neighbours){
				AStarNode temp = new AStarNode (p, currPoint.getPoint(),plane.calculateDistance(currPoint.getPoint(), p));
				// ensure current point is not added
				if (!temp.compare(currPoint.getPoint())) {
					pointsToVisit.add(temp);
				}
			}
			// if any graph edges have been visited
			if (!graphEdgesVisited.isEmpty()) {
				// remove any points from points to visit that will create an edge
				// that has already been visited
				for (Edge e : graphEdgesVisited) {
					Point from = e.getP1();
					Point to = e.getP2();
					System.out.print("from");
					from.print();
					System.out.println();
					System.out.println();
					System.out.print("to");
					to.print();
					System.out.println();
					int j = 0;
					// check this against all the points to visit
					for (AStarNode t : pointsToVisit) {
						if ((((t.getPoint()).getX() == from.getX()) && ((t.getPoint()).getY() == from.getY())) && 
								((currPoint.getPoint()).getX() == to.getX() && (currPoint.getPoint()).getY() == to.getY()
								)) {
							System.out.print("the edge has been visited already");
							e.print();
							remove = false;
							for (AStarNode s : pointsToVisit) {
								if (s.compare(from)){
									remove = true;
									break;
								}
								j ++;
							}
						} else if ((((t.getPoint()).getX() == to.getX()) && ((t.getPoint()).getY() == to.getY())) && 
								((currPoint.getPoint()).getX() == from.getX() && (currPoint.getPoint()).getY() == from.getY()
								)) {
							System.out.print("AAAAAathe edge has been visited already");
							// if the point occurs then delete it
							
							remove = false;
							for (AStarNode s : pointsToVisit) {
								if (s.compare(to)){
									remove = true;
									break;
								}
								j ++;
							}
							e.print();
						} 
					}
					System.out.println("A");
					if (remove && pointsToVisit.size()!= 0) {
						pointsToVisit.remove(j);
					}
				}
			}
			boolean usedPrev = false;
			System.out.println("points to visit:");
			for (AStarNode n:pointsToVisit) {
				n.print();
				System.out.println();
			}
			if (pointsToVisit.size() == 0) {
				usedPrev = true;
				System.out.println("visit neighbours of prev");
				neighbours = plane.getNeighbours(prevPoint);
				// and from here, delete curr point
				pointsToVisit.clear();
				for (Point p : neighbours) {
					AStarNode nodeToVisit = new AStarNode(p, currPoint.getPoint(), plane.calculateDistance(p, currPoint.getPoint()));
					// ensure point is not equal
					if (!nodeToVisit.compare(currPoint.getPoint())) {
						pointsToVisit.add(nodeToVisit);
					}
				}
				// ensure that we dont add curr Point
				if (pointsToVisit.contains(currPoint.getPoint())) {
					pointsToVisit.remove(currPoint.getPoint());
				}
			}
			for (AStarNode n:pointsToVisit) {
				n.print();
				System.out.println();
			}
			System.out.println();
			remove = false;
			AStarNode pointToRemove = null;
			// check that points To Visit does not contain a traversed edge
			if (edgesVisited.size() != 0 && !usedPrev){
				for (Edge e : edgesVisited) {
					Point start = e.getP1(); //(4,7) - curr point
					Point end = e.getP2(); // (4, 4) 
					for (AStarNode k : pointsToVisit) {
						if ((k.getPoint()).comparePoint(start) || 
								((k.getPoint()).comparePoint(end))) {
							System.out.print("removing points");
							k.getPoint().print();
							System.out.println();
							start.print();
							System.out.println();
							end.print();
							System.out.println();
							//remove = true;
							pointToRemove = k;
						}
					}
				}
			}
			if (remove) {
				pointsToVisit.remove(pointToRemove);
			}
			System.out.println("the next points to visit areL:");
			for (AStarNode d : pointsToVisit) {
				d.print();
				System.out.println();
			}

			numOfNodesTraversed ++; // increase number of nodes seen
		}
		
		
		System.out.print("previous point");
		prevPoint.print();
		System.out.println();
		// pick the one with the lowest cost
		System.out.print("Neighbours of:");
		(currPoint.getPoint()).print();
		System.out.println();
		
		System.out.println("\n\n");
		Collections.sort(pointsToVisit, AStarNodeComparator);
		System.out.println("next points to visit are:");
		for (AStarNode n: pointsToVisit) {
			n.print();
			System.out.println();
		}


		System.out.println("path so far:");
		Iterator<Point> keySetIterator = nodesVisited.keySet().iterator();
		while(keySetIterator.hasNext()){
			  Point key = keySetIterator.next();
			  Point value = nodesVisited.get(key);
			  value.print();
			  System.out.print ("  =>  ");
			  key.print();
			  System.out.println();
		}
		
		for (Edge e:edgesVisited) {
			e.print();
			System.out.println();
		}
		System.out.println();
		drawn.printEdges();
	}
	private int numOfNodesTraversed;
	private HashMap<Point, Point> nodesVisited; // node - parent
	private ArrayList<Point> neighbours; // ie. connected edges
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
