/**
 * Class is used to show the previous encounters when using this game, it shows you as a player
 * how many games you have won/drew/lost and also individually against the different types
 * of AI.
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Screen;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.harrison.assignment.Main.Main;
import com.harrison.assignment.Storage.Encounter;
import com.harrison.assignment.Storage.EncounterStorage;

public class HistoryScreen extends JFrame {


	/**
	 * ID of the Frame
	 */
	private static final long serialVersionUID = 2631807748512525117L;
	
	/**
	 * Represents the Height of the Frame.
	 */
	private final int HEIGHT = 525;
	
	/**
	 * Represents the Width of the Frame.
	 */
	private final int WIDTH = 300;
	
	/**
	 * Used to access the previous encounters in the text file
	 * 
	 * @see {@link Encounter}
	 */
	private EncounterStorage history = new EncounterStorage();

	/**
	 * Constructor creates the frame and populates it with the components
	 */
	public HistoryScreen(){
		createScreen();

		add(createContent());

	}


	/**
	 * Sets the different properties of the frame.
	 */
	private void createScreen(){
		setIconImage(Main.createImageIcon("/res/Images/Rock.jpg", "Icon").getImage());
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);    
	}

	/**
	 * Displays the frame to the user, warns if any corrupt data
	 */
	public void showScreen(){

		setVisible(true);

		checkErrorsOccured();

	}
	
	/**
	 * Checks to see if any errors were found in the encounters file.
	 */
	private void checkErrorsOccured(){


		if(history.getErrorOccured()){
			displayWarning();
		}
	}
	
	/**
	 * Displays a warning to the user if any errors were found in the file.
	 */
	private void displayWarning(){

		JOptionPane.showMessageDialog(null,
				"Unfortunetly there seems to have been an error in reading the file, some information may" +
						"be missing.",
						"Error",
						JOptionPane.ERROR_MESSAGE);

	}


	/**
	 * Creates the components in which all the content is held
	 * 
	 * @return the Panel which holds all of the content
	 */
	private JPanel createContent(){

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		contentPane.add(createUserValues());
		contentPane.add(createEnemyValues());
		contentPane.add(Box.createRigidArea(new Dimension(0, 20)));
		contentPane.add(createClearButton());
		contentPane.setBorder(new EmptyBorder(10,10,10,10));

		return contentPane;

	}
	
	/**
	 * Creates the {@link JButton} component that is used to clear any previous encounters.
	 * 
	 * @return the {@link JButton}.
	 */
	private JButton createClearButton(){
		
		JButton button = new JButton();
		
		button.setText("Clear");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setPreferredSize(new Dimension(100,20));
		button.setMaximumSize(new Dimension(100, 20));
		button.addActionListener(new ClearListener());
		return button;
	}

	/**
	 * Creates the labels of the enemy encounters 
	 * 
	 * @return a JPanel containing values of the enemy encounters
	 */
	private JPanel createEnemyValues(){

		JPanel enemyPane = new JPanel();
		enemyPane.setLayout(new BoxLayout(enemyPane,BoxLayout.Y_AXIS));

		for(int i = 0; i < history.getAllEncounters().size(); i++){

			Encounter encounter = history.getEncounter(i);

			enemyPane.add(Box.createRigidArea(new Dimension(150,10)));
			enemyPane.add(createMainNameLabels(encounter.getName()));
			enemyPane.add(Box.createRigidArea(new Dimension(0,10)));
			enemyPane.add(createLabel("Played", encounter.getGamesPlayed()));
			enemyPane.add(createLabel("Won", encounter.getGamesLost()));
			enemyPane.add(createLabel("Drawn", encounter.getGamesDrew()));
			enemyPane.add(createLabel("Lost", encounter.getGamesWon()));

		}

		return enemyPane;

	}

	/**
	 * Creates the labels of the users total previous encounters
	 * 
	 * @return a JPanel containing the labels of the users total previous encounters
	 */
	private JPanel createUserValues(){

		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BoxLayout(userPanel,BoxLayout.Y_AXIS));

		Font titleFont = new Font("Arial", Font.BOLD, 24);
		JLabel lblPlayer = new JLabel();

		lblPlayer.setText("Player");
		lblPlayer.setFont(titleFont);
		lblPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);

		userPanel.add(lblPlayer);
		userPanel.add(Box.createRigidArea(new Dimension(0,10)));

		userPanel.add(createLabel("Total Games Played", history.getTotalPlayed()));
		userPanel.add(Box.createRigidArea(new Dimension(0,10)));

		userPanel.add(createLabel("Total Won", history.getTotalLost()));
		userPanel.add(createLabel("Total Drawn", history.getTotalDrew()));
		userPanel.add(createLabel("Total Lost", history.getTotalWon()));

		userPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		return userPanel;
	}

	/**
	 * Creates a label with the following format : "Message : Value"
	 * 
	 * @param message indicates the message, this could indicate what the value such as "Total Games Won"
	 * @param value indicates the value of this attribute, for example you may have won "32" times
	 * @return the label created from the message and value together
	 */
	private JLabel createLabel(String message, int value){

		JLabel label = new JLabel();
		label.setText(message + ": " +  value);

		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		return label;

	}

	/**
	 * Creates the label corresponding to the name of this Entity
	 * 
	 * @param difficulty indicates the type of entity
	 * @return a JLabel created for the specific entity, returns null if the difficulty
	 * does not match the avalible entities
	 */
	private JLabel createMainNameLabels(String difficulty){
		Font titleFont = new Font("Arial", Font.BOLD, 24);

		JLabel lblMainName = new JLabel(difficulty + " AI");
		lblMainName.setFont(titleFont);

		lblMainName.setAlignmentX(Component.CENTER_ALIGNMENT);


		return lblMainName;


	}
	
	/**
	 * Used by the clear button to allow the user to delete previous encounters
	 * @author Harrison
	 *
	 */
	private class ClearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int option = JOptionPane.showConfirmDialog(null, 
					"Are you sure you want to clear all data?", 
					"Clear Data?", 
					JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION){
				
				history.removeAll();
				dispose();
			}
			
		}
		
	}


}