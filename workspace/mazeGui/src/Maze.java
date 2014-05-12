import java.util.ArrayList;
import java.util.Random;


import java.awt.*;

import javax.swing.*;

/**
 * Creates a rectangular maze from a given width and height. Uses the method of
 * 'recursive division' in order to generate mazes. Mazes generated are random
 * every time, and have multiple routes to location, i.e. circular paths.
 * Currently prints maze to the console as a strings of pipes, |, and
 * underscores, _, but should be able to be converted to graphics without too
 * much trouble (I hope).
 * 
 * @author Nicola
 * 
 */
public class Maze {

	/**
	 * 
	 */
	public Maze(int mazeWidth, int mazeHeight) {
		maze = new ArrayList<Node>();
		width = mazeWidth;
		height = mazeHeight;
		makeMaze();
		divideMaze(1, width - 1, 0, height - 2);
	}

	/**
	 * Initialises maze by adding a node for every x,y location and storing it
	 * in an array list. Also links nodes on the sides and bottom of the maze to
	 * form the edges of the maze.
	 */
	private void makeMaze() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Node n = new Node(i, j);
				maze.add(n);
			}
		}
		// add links around edges
		for (Node n : maze) {
			if (n.getX() == height - 1)
				n.setRight(true);
			if ((n.getY() == 0))
				n.setDown(true);
		}
	}

	/**
	 * Divides the maze into four sections, then erases a part of the each wall
	 * to allow for passage. It will then call recursively on the sections.
	 * 
	 * @param startWidth
	 *            The left-most node included in the section
	 * @param endWidth
	 *            The right-most node included in the section
	 * @param startHeight
	 *            The top node included in the section
	 * @param endHeight
	 *            The bottom node included in the section
	 */
	private void divideMaze(int startWidth, int endWidth, int startHeight,
			int endHeight) {
		if (((endWidth - startWidth) < 0) || ((endHeight - startHeight) < 0))
			return;

		// the partition on the x-plane and the y-plane
		int x = getRandom(startHeight, endHeight);
		int y = getRandom(startWidth, endWidth);

		// Which part of the wall are erased
		int eraseX1 = getRandom(startHeight, x - 1);
		int eraseX2 = getRandom(x, endHeight);
		int eraseY1 = getRandom(startWidth - 1, y - 1);
		int eraseY2 = getRandom(y, endWidth);

		for (Node n : maze) {

			// if the x-coordinate is equal to where the x partition will be,
			// and it is within the y boundaries of the section
			if ((n.getX() == x) && (n.getY() <= endWidth)
					&& (n.getY() >= startWidth - 1)) {

				// if it is not equal to either of the spaces where the gap in
				// the wall will be
				if ((n.getY() != eraseY1) && (n.getY() != eraseY2))
					n.setRight(true);
				else
					n.setRight(false);
			}

			// similarly, if the y-coordinate is equal to where y partition will
			// be, and it is within the x boundaries of the section
			if ((n.getY() == y) && (n.getX() <= endHeight + 1)
					&& (n.getX() >= startHeight)) {

				// if it is not equal to either of the spaces where the gap in
				// the wall will be
				if ((n.getX() != eraseX1) && (n.getX() != eraseX2))
					n.setDown(true);
				else
					n.setDown(false);
			}
		}
		// printMaze(); //to step through each division, print the maze here
		divideMaze(startWidth, y - 1, startHeight, x - 1);
		divideMaze(y + 1, endWidth, startHeight, x - 1);
		divideMaze(startWidth, y - 1, x + 1, endHeight);
		divideMaze(y + 1, endWidth, x + 1, endHeight);
	}

	/**
	 * Prints out all the nodes of the maze to the console. Currently also
	 * prints the top and the right edges of the maze, as this is not included
	 * in the nodes. Also prints the maze from the top down! This means that the
	 * x and y coordinates are actually swapped around, but the width and height
	 * are still right. Hopefully we won't have this problem later...
	 */
	public void printMaze(JFrame window) {
		int y = 0;
		int x = 0;
		//window.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel = new JPanel(new GridBagLayout());
		
		//natural height, maximum width
		c.fill = GridBagConstraints.HORIZONTAL;


		for (int i = 0; i <= width*2; i++) { // print top part of maE
			System.out.print(" _");
			JLabel wallBotLeft = new JLabel(new SeaIcon(5));
			c.fill = GridBagConstraints.BOTH;
			c.gridx = i;
			c.gridy = 1;
			panel.add(wallBotLeft, c);
			
			JLabel wallBotRight = new JLabel(new SeaIcon(5));
			c.fill = GridBagConstraints.BOTH;
			c.gridx = i + 1;
			c.gridy = 1;
			panel.add(wallBotRight, c);
		
			JLabel wallTopLeft = new JLabel(new SeaIcon(5));
			c.fill = GridBagConstraints.BOTH;
			c.gridx = i;
			c.gridy = 0;
			panel.add(wallTopLeft, c);
			
			JLabel wallTopRight = new JLabel(new SeaIcon(5));
			c.fill = GridBagConstraints.BOTH;
			c.gridx = i+1;
			c.gridy = 0;
			panel.add(wallTopRight, c);
		}
		SeaIcon s2a = new SeaIcon(5); // probaly 10 is okay?
		JLabel s2b = new JLabel(s2a);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = width*2 + 1;
		c.gridy = y;
		panel.add(s2b, c);
		
		//window.add(panel);
		y ++;
		System.out.println();
		int xCounter = 0;
		for (Node n : maze) {
			if (n.getX() != xCounter) {
				// when the next node is on a new line,
				System.out.println("|"); // end of the line
				SeaIcon land = new SeaIcon(5);
				JLabel landLabel = new JLabel(land);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = x;
				c.gridy = y;
				panel.add(landLabel, c);
				SeaIcon land1 = new SeaIcon(5);
				JLabel landLabel1 = new JLabel(land1);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = x;
				c.gridy = y+ 1;
				panel.add(landLabel1, c);
				SeaIcon land2 = new SeaIcon(5);
				JLabel landLabel2 = new JLabel(land2);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = x + 1;
				c.gridy = y;
				panel.add(landLabel2, c);
				SeaIcon land3 = new SeaIcon(5);
				JLabel landLabel3 = new JLabel(land3);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = x + 1;
				c.gridy = y+ 1;
				panel.add(landLabel3, c);
				
				
				xCounter++;
				y +=2;
				x = 0;
			}
			//n.printNode(window, xCounter);
			n.displayNode(xCounter, panel, x, y, c, width);
			x += 2;
		}
		System.out.println("|"); // the last one
		SeaIcon land = new SeaIcon(5);
		JLabel landLabel = new JLabel(land);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		panel.add(landLabel, c);
		SeaIcon land1 = new SeaIcon(5);
		JLabel landLabel1 = new JLabel(land1);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x + 1;
		c.gridy = y;
		panel.add(landLabel1, c);
		SeaIcon land2 = new SeaIcon(5);
		JLabel landLabel2 = new JLabel(land2);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y+1;
		panel.add(landLabel2, c);
		SeaIcon land3 = new SeaIcon(5);
		JLabel landLabel3 = new JLabel(land3);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x+1;
		c.gridy = y+1;
		panel.add(landLabel3, c);
		window.add(panel);
	}

	/**
	 * Gets a random number between the first and the second number, but not
	 * including the second, i.e. [first, second)
	 * 
	 * @param first
	 *            The first integer in the range
	 * @param second
	 *            The second integer in the range
	 * @return A random integer between the two
	 */
	private int getRandom(int first, int second) {
		if (second - first <= 0) {
			return first;
		}
		Random r = new Random();
		int random = r.nextInt(second - first) + first;
		return random;
	}

	private ArrayList<Node> maze;
	private int width;
	private int height;
}
