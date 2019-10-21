package solar_system_test;

import java.awt.Polygon;
import java.awt.Shape;

import javax.swing.*;
/** Simple Swing application illustrating graphics. */
public class SolarSystem extends JPanel {
	static boolean bigscreen =true;
	
	/** Create and display a JFrame containing a LinesPanel. */
	private static void createAndDisplayGui() {
		JFrame frame = new JFrame("Solar System Animation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		//Create the panel with aimation + buttons
		JPanel mainPanel = new SolarSystemGuiPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		frame.add(mainPanel); //add to the frame
		
		frame.pack(); // Set component sizes and layout
		frame.setVisible(true); // Display the resulting frame
	}
	
	
	/** Call method to create and display GUI. */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndDisplayGui();
			}
		});
	}
	
	
	
}