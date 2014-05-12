import java.awt.*;

import javax.swing.*;
public class Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// parent window, message, window title, mesg
		//Icon car = new CarIcon(50);
		//JOptionPane.showMessageDialog(null, "", "Wovbberific maze", 
			//	JOptionPane.INFORMATION_MESSAGE, car);
		JFrame window = new JFrame();
		//window.getContentPane().setPreferredSize(new Dimension(750, 750));

		//JLabel car1 = new JLabel(car);
		//window.add(car1);
		Maze maze = new Maze(5, 5);
		//window.setLayout(new GridBagLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		maze.printMaze(window);
		//window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

}
