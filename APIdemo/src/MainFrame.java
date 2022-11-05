import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements ActionListener {

	RestApi rest;
	JFrame frame;
	JPanel panel;
	JButton selButton;
	JTextField textField;
	JLabel instructions;
	JLabel idLabel;
	JLabel idValue;
	JLabel nameLabel;
	JLabel nameValue;
	JLabel descLabel;
	JTextArea descValue;
	JLabel releasedLabel;
	JLabel releasedValue;
	JLabel metacriticLabel;
	JLabel metacriticValue;
	Transcript transcript;
	
	public MainFrame() {
		
		rest = new RestApi();
		frame = new JFrame();
		panel = new JPanel();
		selButton = new JButton("Select");
		textField = new JTextField();
		instructions = new JLabel("Enter index and click Select button");
		idLabel = new JLabel("ID: ");
		idValue = new JLabel();
		nameLabel = new JLabel("Name: ");
		nameValue = new JLabel();
		descLabel = new JLabel("Description: ");
		descValue = new JTextArea();
		releasedLabel = new JLabel("Released: ");
		releasedValue = new JLabel();
		metacriticLabel = new JLabel("Metacritic: ");
		metacriticValue = new JLabel();
		
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("RAWG API Interface | Video Game Info");
		
		instructions.setBounds(580, 10, 200, 25);
		
		textField.setBounds(700, 40, 75, 25);
		
		selButton.setBounds(700, 70, 75, 40);
		selButton.addActionListener(this);
		
		idLabel.setBounds(10, 10, 50, 25);
		idValue.setBounds(100, 10, 50, 25);
		nameLabel.setBounds(10, 35, 50, 25);
		nameValue.setBounds(100, 35, 200, 25);
		descLabel.setBounds(10, 60, 75, 25);
		descValue.setBounds(100, 65, 550, 220);
		descValue.setFont(new Font("Arial", Font.BOLD, 12));
		descValue.setWrapStyleWord(true);
		descValue.setLineWrap(true);
		descValue.setOpaque(false);
		descValue.setEditable(false);
		descValue.setFocusable(false);
		releasedLabel.setBounds(10, 290, 75, 25);
		releasedValue.setBounds(100, 290, 200, 25);
		metacriticLabel.setBounds(10, 315, 75, 25);
		metacriticValue.setBounds(100, 315, 50, 25);
		
		panel.setLayout(null);
		panel.add(instructions);
		panel.add(textField);
		panel.add(selButton);
		panel.add(idLabel);
		panel.add(idValue);
		panel.add(nameLabel);
		panel.add(nameValue);
		panel.add(descLabel);
		panel.add(descValue);
		panel.add(releasedLabel);
		panel.add(releasedValue);
		panel.add(metacriticLabel);
		panel.add(metacriticValue);
		
		frame.add(panel);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == selButton) {
			if(textField.getText().isEmpty()) {
				System.out.println("Empty");
			}
			else {
				System.out.println("Select #" + textField.getText());
				
				try {
					transcript = rest.getGame(Integer.valueOf(textField.getText()));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				idValue.setText(transcript.getId());
				nameValue.setText(transcript.getName());
				descValue.setText(transcript.getDescription());
				releasedValue.setText(transcript.getReleased());
				metacriticValue.setText(transcript.getMetacritic());
			}
		}
	}
}
