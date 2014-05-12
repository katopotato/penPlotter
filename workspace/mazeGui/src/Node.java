import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Each grid location of the maze is stored as a node. Nodes have x and y
 * coordinates, and whether or not there is a wall going downwards or right-ways
 * from the node
 * 
 * @author Nicola
 * 
 */
public class Node implements Icon {
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		down = false;
		right = false;
	}

	/**
	 * Gets x coordinate of node
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets y coordinate of node
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns whether or there is a wall vertically
	 * 
	 * @return True if there is a wall, false if there is a gap
	 */
	public boolean isDown() {
		return down;
	}

	/**
	 * Create or remove a wall vertically
	 * 
	 * @param down
	 *            True to create, false to remove
	 */
	public void setDown(boolean down) {
		this.down = down;
	}

	/**
	 * Returns whether or there is a wall horizontally
	 * 
	 * @return True if there is a wall, false if there is a gap
	 */
	public boolean isRight() {
		return right;
	}

	/**
	 * Create or remove a wall horizontally
	 * 
	 * @param right
	 *            True to create, false to remove
	 */
	public void setRight(boolean right) {
		this.right = right;
	}

	/**
	 * prints out the node as a combination of pipes and underscores. A pipe on
	 * the left if there is a wall vertically, or a space if otherwise; an
	 * underscore on the right if there is a wall horizontally, a space if
	 * otherwise.
	 */
	public void printNode(JFrame window, int x) {
		if (right && down) {
			//window.add(wallLeftShape);
			//window.add(wallBottomShape);
			System.out.print("|_");
		} else if (right && !down) {
			//window.add(seaShape);
			//window.add(wallBottomShape);
			System.out.print(" _");
		} else if (!right && down) {
			//window.add(wallLeftShape);
			//window.add(seaShape);
			System.out.print("| ");
		} else {
			//window.add(seaShape);
			System.out.print("  ");
		}
	}
	
	public void displayNode (int x, JPanel panel, int xPos, int yPos, GridBagConstraints c, int gridWidth) {
		c.fill = GridBagConstraints.HORIZONTAL;
		
		if (right && down) {
			JLabel topLeft = new JLabel(new SeaIcon(5));
			c.gridx = xPos;
			c.gridy = yPos;
			panel.add(topLeft, c);
			JLabel bottomLeft = new JLabel(new SeaIcon(5));
			c.gridx = xPos;
			c.gridy = yPos + 1;
			panel.add(bottomLeft, c);
			
			JLabel topRight = new JLabel (new LandIcon(5));
			c.gridx = xPos + 1;
			c.gridy = yPos;
			panel.add(topRight, c);
			
			JLabel bottomRight = new JLabel(new SeaIcon(5));
			c.gridx = xPos + 1;
			c.gridy = yPos + 1;
			panel.add(bottomRight, c);
			System.out.print("|_");
		} else if (right && !down) {
			JLabel topLeft = new JLabel(new LandIcon(5));
			c.gridx = xPos;
			c.gridy = yPos;
			panel.add(topLeft, c);
			JLabel bottomLeft = new JLabel(new LandIcon(5));
			c.gridx = xPos;
			c.gridy = yPos + 1;
			panel.add(bottomLeft, c);
			
			JLabel topRight = new JLabel (new LandIcon(5));
			c.gridx = xPos + 1;
			c.gridy = yPos;
			panel.add(topRight, c);
			
			JLabel bottomRight = new JLabel(new SeaIcon(5));
			c.gridx = xPos + 1;
			c.gridy = yPos + 1;
			panel.add(bottomRight, c);
			System.out.print(" _");
		} else if (!right && down) {
			JLabel topRight = new JLabel (new LandIcon(5));
			c.gridx = xPos + 1;
			c.gridy = yPos;
			panel.add(topRight, c);
			
			JLabel bottomRight = new JLabel(new LandIcon(5));
			c.gridx = xPos + 1;
			c.gridy = yPos + 1;
			panel.add(bottomRight, c);
			JLabel topLeft = new JLabel(new SeaIcon(5));
			c.gridx = xPos;
			c.gridy = yPos;
			panel.add(topLeft, c);
			JLabel bottomLeft = new JLabel(new SeaIcon(5));
			c.gridx = xPos;
			c.gridy = yPos + 1;
			panel.add(bottomLeft, c);
			System.out.print("| ");
		} else {
			JLabel topRight = new JLabel (new LandIcon(5));
			c.gridx = xPos + 1;
			c.gridy = yPos;
			panel.add(topRight, c);
			JLabel bottomRight = new JLabel(new LandIcon(5));
			c.gridx = xPos + 1;
			c.gridy = yPos + 1;
			panel.add(bottomRight, c);
			JLabel topLeft = new JLabel(new LandIcon(5));
			c.gridx = xPos;
			c.gridy = yPos;
			panel.add(topLeft, c);
			JLabel bottomLeft = new JLabel(new LandIcon(5));
			c.gridx = xPos;
			c.gridy = yPos + 1;
			panel.add(bottomLeft, c);
			System.out.print("  ");
		}
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
		Graphics2D g2 = (Graphics2D) g;
		Shape rectangle = new Rectangle2D.Double(x, y,width, height);
		
		g2.setColor(Color.CYAN);
		g2.fill(rectangle);
		g2.draw(rectangle);
		
	}
	private int height;
	private int width;
	private boolean down;
	private boolean right;
	private int x;
	private int y;
}
