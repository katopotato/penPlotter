import java.awt.*;
import java.awt.geom.*;
import javax.swing.Icon;
public class Rectangle implements Icon{
	public Rectangle(int x, int y, int width, int height) {
		this.height = height;
	    this.width = width;
		this.xPos = x;
		this.yPos = y;
	}
	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		Shape rectangle = new Rectangle2D.Double(xPos, yPos,width, height);
		
		Shape rectangle1 = new Rectangle2D.Double(xPos, yPos + yPos,width, height);
		g2.setColor(Color.BLUE);
		g2.fill(rectangle);
		g2.draw(rectangle);

		g2.fill(rectangle1);
		g2.draw(rectangle1);
	}
	private int xPos;
	private int yPos;
	private int height;
	private int width;
}
