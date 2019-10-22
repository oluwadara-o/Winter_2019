package textAdventure;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A panel that contains the information of the scene
 * @author Dara
 *
 */

public class textPanel extends JPanel implements ActionListener{
	String sceneInfo = "You are echo, for lyting to the gods \n"
						+ " you have been cursed to repeat the last words you heard \n"
						+ "you can speak by typing speak in the text box \n"
						+ "you can investigate objects by typing investigate X \n"
						+ "you can pick up items by typing investigate Y";
	//String objectsInfo;
	private JTextField textField;
	private JButton sendButton;
	private JTextArea sceneText;
	
	public textPanel() {
		//the text displayed on scene
		sceneText =  new JTextArea(sceneInfo);
		JScrollPane scenePanel = new JScrollPane(sceneText);
		scenePanel.setPreferredSize(new Dimension(400,400));
		
		//create a panel to type and enter text
		JPanel textEntryPanel = new JPanel();
		textField = new JTextField("Type here");
		textEntryPanel.add(textField);
		sendButton = new JButton("send");
		sendButton.addActionListener(this);
		textEntryPanel.add(sendButton);
		textEntryPanel.setLayout(new BoxLayout(textEntryPanel,BoxLayout.X_AXIS));
		
		//add all panels together
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.add(scenePanel);
		mainPanel.add(textEntryPanel);
		add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton) {
			String typedText = textField.getText();
			String action;
			//chose what to do depending on what was typed in
			if(typedText.isEmpty()) {
				//do nothing if testfield is empty
			}else if (typedText.matches("speak")) {
				action = "you say nothing";
				updateScript(action, typedText);
				System.out.println(typedText);
			}else {
				action = "you don't know how to " + typedText;
				updateScript(action, typedText);
				System.out.println("Invalid Comamnd");
			}
			
		}
	}
	
	/*
	 * Update the text display based on the action taken
	 */
	public void updateScript(String action, String typedText) {
		//add try catch block
		sceneInfo = sceneInfo +"\n Action:"+ typedText +"\n" + action;
		sceneText.setText(sceneInfo);
	}

}
