package textAdventure;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class display {
	
	private static void createAndDisplayGui() {
		JFrame frame = new JFrame("Text Adventure");
		// Exit application if window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(300,300)); //smalles size

		//Create the panel 
		JPanel mainPanel = new textPanel();
		//mainPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		
		
		frame.add(mainPanel); //add to the frame
		frame.pack(); // Set component sizes and layout
		frame.setVisible(true); // Display t
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
