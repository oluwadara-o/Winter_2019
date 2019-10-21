package simpleButtonApplet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SimpleButtonAppletPanel extends JPanel implements ActionListener {
	
	JButton hiButton;
	JLabel label;
	/** Create and display a JPanel containing a JLabel and JButton. */
	public SimpleButtonAppletPanel() {
		//Create a label panel
		label = new JLabel("Hello?");
		JPanel labelPanel = new JPanel();
		labelPanel.add(label);

		//Create Button panel
		hiButton = new JButton("Say Hi");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(hiButton);
		hiButton.addActionListener(this);
		
		//put two panels into one main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		add(labelPanel);
		add(buttonPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == hiButton) {
			label.setText("There you are!");
			
		}
		// TODO Auto-generated method stub
		
	}
}
