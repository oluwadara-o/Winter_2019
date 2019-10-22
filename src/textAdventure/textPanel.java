package textAdventure;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
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
		
		sceneText =  new JTextArea(sceneInfo);
		JPanel scenePanel = new JPanel();
		scenePanel.add(sceneText);
		scenePanel.setPreferredSize(new Dimension(400,400));
		
		JPanel textEntryPanel = new JPanel();
		textField = new JTextField("echo");
		sendButton = new JButton("send");
		sendButton.addActionListener(this);
		textEntryPanel.add(textField);
		textEntryPanel.add(sendButton);
		textEntryPanel.setLayout(new BoxLayout(textEntryPanel,BoxLayout.X_AXIS));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.add(scenePanel);
		mainPanel.add(textEntryPanel);
		add(mainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton) {
			String updatedSceneInfo = sceneInfo +"\n"+ textField.getText();
			sceneInfo =updatedSceneInfo;
			sceneText.setText(updatedSceneInfo);
			
		}
	}

}
