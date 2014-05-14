import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
public class PenPlotter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner(new FileReader(args[0]));
			// create a new plane
			Plane plane = new Plane();
			// initialise all points
			while (sc.hasNextLine()) {
				String currentLine = sc.nextLine();
				Point p1 = plane.addPoint(Character.getNumericValue(currentLine.charAt(X1)), Character.getNumericValue(currentLine.charAt(Y1)));
				Point p2 = plane.addPoint(Character.getNumericValue(currentLine.charAt(X2)), Character.getNumericValue(currentLine.charAt(Y2)));
				plane.addEdge(p1, p2);
			}
			
			System.out.println("Testing draw");
			Draw draw = new Draw(plane);
			draw.draw();
			sc.close();
		} catch (FileNotFoundException e){}
	}
	private static final int X1 = 13;
	private static final int Y1 = 15;
	private static final int X2 = 21;
	private static final int Y2 = 23;
}
