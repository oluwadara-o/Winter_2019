package solar_system_test;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
/**
 * A panel that add buttons to an animation panel
 * @author Dara
 *
 */
public class SolarSystemGuiPanel extends JPanel implements ActionListener {
	private SkyPanel animPanel; // panel containing animation
	private JButton smallButton;
	private JButton bigButton;
	private JButton exitButton;
	/** Create JPanel containing animation panel and buttons. */
	public SolarSystemGuiPanel() {
		super();
		setPreferredSize(new Dimension(950,950));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//Create skypanel
		animPanel = new SkyPanel(900,900);
		
		//Create buttons and add funtionality
		smallButton = new JButton("Small Screen");
		bigButton = new JButton("Big Screen");
		exitButton = new JButton("Exit");
		smallButton.addActionListener(this);
		bigButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		//Creat putton panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		//Add buttons to the panel
		buttonPanel.add(smallButton);
		buttonPanel.add(bigButton);
		buttonPanel.add(exitButton);
		
		//add panels to GUI
		add(buttonPanel);
		add(animPanel);
	}
	/** Respond to button clicks */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==smallButton) startSmall();
		else if (e.getSource()==bigButton) startBig();
		else if (e.getSource()==exitButton) System.exit(0);
	}
	
	/** Start a smaller version of the animation */
	public void startSmall() {
		animPanel.smallanimation();
		}
	
	/** Start a larger verison of the animation*/
	public void startBig() {
		setPreferredSize(new Dimension(950,950));
		animPanel.biganimation();
		}
}