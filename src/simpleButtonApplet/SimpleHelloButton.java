package simpleButtonApplet;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * Simple Swing application that contains a JButton
 */
public class SimpleHelloButton implements SwingConstants{
	
	private static void createAndDisplayGui() {
		JFrame frame = new JFrame("Button Example");
		// Exit application if window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(300,300)); //smalles size

		//Create the panel 
		JPanel mainPanel = new SimpleButtonAppletPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		
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