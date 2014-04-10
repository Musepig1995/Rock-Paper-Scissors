/**
 * Class is used as the first screen you see, it allows you to choose the difficulty you wish
 * to face and then either play the game, see your previous encounters or simply quit the game.
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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import com.harrison.assignment.Main.Main;
import com.harrison.assignment.Storage.EncounterStorage;


public class MenuScreen extends JFrame {



	/**
	 * ID of the Frame.
	 */
	private static final long serialVersionUID = 6204313376068355180L;
	
	/**
	 * Represents the Height of the Frame.
	 */
	private final int HEIGHT = 450;
	
	/**
	 * Represents the Width of the Frame.
	 */
	private final int WIDTH = 350;
	
	/**
	 * Represents the minimum amount of rounds you can play.
	 */
	private final int ROUNDS_MIN = 1;
	
	/**
	 * Represents the maximum amount of rounds you can play.
	 */
	private final int ROUNDS_MAX = 10;
	
	/**
	 * Represents the default amount of rounds for {@link Game}, shown to user.
	 * Used for {@link #roundsSlider}
	 */
	private final int ROUNDS_INIT = (int) Math.floor((ROUNDS_MAX + ROUNDS_MIN) / 2);
	
	/**
	 * Represents the {@link RandomAI} option.
	 * Needs to be accessible for the {@link PlayListener}.
	 */
	private JRadioButton rdoRand;
	
	/**
	 * Represents the {@link StateAI} option.
	 * Needs to be accessible for the {@link PlayListener}.
	 */
	private JRadioButton rdoState;
	
	/**
	 * Represents the {@link IntelligentAI} option.
	 * Needs to be accessible for the {@link PlayListener}.
	 */
	private JRadioButton rdoIntel;
	
	/**
	 * Represents the amount of rounds in {@link Game}.
	 * Needs to be accessible for the {@link PlayListener}.
	 */
	private JSlider roundsSlider;


	/**
	 * Constructor creates the frame and adds all of the components to the frame
	 */
	public MenuScreen(){


		createScreen();
		add(createContentPane());

	}

	/**
	 * Creates the content pane which holds all components on this screen and specifically the labels at the top
	 * 
	 * @return a JPanel containing all of the components to be put on the frame
	 */
	private JPanel createContentPane(){

		JPanel contentPane = new JPanel();

		//we want any components that are added to this panel to be put on vertically
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		//gives a space around the content to make it not seemed so cluttered
		contentPane.setBorder(new EmptyBorder(20,20,20,20));

		//populates the panel with components
		createLabels(contentPane);
		contentPane.add(createOptionPane());

		return contentPane;
	}

	/**
	 * Creates the panel holding all the possible options to the user 
	 * 
	 * @return a JPanel containing all of the components regarding the options to the user 
	 */
	private JPanel createOptionPane(){

		JPanel optionPane = new JPanel();

		//we want any components that are added to this panel to be put on vertically
		optionPane.setLayout(new BoxLayout(optionPane, BoxLayout.Y_AXIS));

		//required to fix a bug where the bottom of the last button didn't show.
		optionPane.setMaximumSize(new Dimension(500,500));


		//populates the panel with components
		optionPane.add(createSlider());
		optionPane.add(createRadioButtons());
		optionPane.add(createButtons());
		return optionPane;

	}

	/**
	 * Makes this frame visible to the user
	 */
	public void showScreen(){
		setVisible(true);
	}

	/**
	 * Sets the different properties of the frame
	 */
	private void createScreen(){


		setSize(WIDTH,HEIGHT);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		//the icon that will be shown when minimised and on the top left corner of frame
		ImageIcon img = Main.createImageIcon("/res/Images/Rock.jpg", "Icon");
		setIconImage(img.getImage());
	}

	/**
	 * Creates the labels that display information about the game to the user
	 * 
	 * @param pane is the panel that the labels are added to
	 */
	private void createLabels(JPanel pane){
		JLabel lblTitle = new JLabel();

		Font titleFont = new Font("Arial", Font.BOLD, 24);
		lblTitle.setFont(titleFont);

		//wrapping text in <html> tags is a way to display the whole of this text in the label
		//otherwise a ... is displayed after ~200 pixels
		lblTitle.setText("<html>Welcome to Rock, Paper, Scissors: AI Edition!</html>");
		lblTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);


		JLabel lblDesc = new JLabel();
		lblDesc.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		//see the above use of <html> tags comment
		lblDesc.setText("<html>Can you win this classic game of Rock, Paper, Scissors against the different AI?!</html>");

		pane.add(lblTitle);
		pane.add(Box.createRigidArea(new Dimension(0, 20)));
		pane.add(lblDesc);
		pane.add(Box.createRigidArea(new Dimension(0, 20)));

		pane.setAlignmentX(Component.CENTER_ALIGNMENT);

	}

	/**
	 * Creates the radio buttons that refer to the difficulty level that the user wants to play at
	 * 
	 * @return gets the JPanel that the radio buttons are added to
	 */
	private JPanel createRadioButtons(){

		JPanel rdoPane = new JPanel();

		rdoPane.setLayout(new BoxLayout(rdoPane, BoxLayout.Y_AXIS));

		rdoRand = new JRadioButton();
		rdoRand.setText("Random AI");

		rdoState = new JRadioButton();
		rdoState.setText("State AI");

		rdoIntel = new JRadioButton();
		rdoIntel.setText("Intelligent AI");

		//by putting the radio buttons into a ButtonGroup only one of them can be selected.
		ButtonGroup difficultyGroup = new ButtonGroup();

		difficultyGroup.add(rdoRand);
		difficultyGroup.add(rdoState);
		difficultyGroup.add(rdoIntel);


		rdoPane.add(rdoRand);
		rdoPane.add(rdoState);
		rdoPane.add(rdoIntel);

		rdoPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		return rdoPane;

	}

	/**
	 * Creates the buttons that the user can use to access/quit the game
	 * 
	 * @return gets the JPanel that the buttons are added to
	 */
	private JPanel createButtons(){

		JPanel btnPane = new JPanel();
		btnPane.setLayout(new BoxLayout(btnPane, BoxLayout.Y_AXIS));
		btnPane.setMaximumSize(new Dimension(100, 200));

		JButton btnPlay = new JButton();
		btnPlay.setText("Play");

		JButton btnHistory = new JButton();
		btnHistory.setText("History");

		JButton btnQuit = new JButton();
		btnQuit.setText("Quit");

		//makes them all the same size
		btnPlay.setMaximumSize(new Dimension(100,30));
		btnHistory.setMaximumSize(new Dimension(100,30));
		btnQuit.setMaximumSize(new Dimension(100,30));

		//each button has their own action listener that does their respective events
		btnPlay.addActionListener(new PlayListener());
		btnQuit.addActionListener(new QuitListener());
		btnHistory.addActionListener(new HistoryListener());

		btnPane.add(Box.createRigidArea(new Dimension(0, 5)));
		btnPane.add(btnPlay);
		btnPane.add(Box.createRigidArea(new Dimension(0, 5)));
		btnPane.add(btnHistory);
		btnPane.add(Box.createRigidArea(new Dimension(0, 5)));
		btnPane.add(btnQuit);

		btnPane.setAlignmentX(Component.CENTER_ALIGNMENT);

		return btnPane;

	}

	/**
	 * Creates a slider that shows the range of rounds you can play
	 * 
	 * @return gets the JPanel that the Slider and label are added to
	 */
	private JPanel createSlider(){

		JPanel sliderPane = new JPanel();
		sliderPane.setLayout(new BoxLayout(sliderPane, BoxLayout.Y_AXIS));

		createSliderLabel(sliderPane);

		roundsSlider = new JSlider(JSlider.HORIZONTAL, ROUNDS_MIN, ROUNDS_MAX, ROUNDS_INIT);

		roundsSlider.setMaximumSize(new Dimension(250,50));

		roundsSlider.setMajorTickSpacing(1);
		roundsSlider.setPaintTicks(true);
		roundsSlider.setPaintLabels(true);

		sliderPane.setAlignmentX(Component.CENTER_ALIGNMENT);

		sliderPane.add(roundsSlider);



		return sliderPane;



	}

	/**
	 * Creates the label to go above the slider 
	 * 
	 * @param pane the panel that the label will be added to
	 */
	private void createSliderLabel(JPanel pane){

		JLabel roundsLabel = new JLabel("How many rounds?", JLabel.CENTER);

		roundsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		pane.add(roundsLabel);
	}

	/**
	 * Any action regarding the quit button is handled by this class
	 */
	private class QuitListener implements ActionListener {


		@Override
		public void actionPerformed(ActionEvent e) {
			//creates a dialog asked if the user wants to exit
			int option = JOptionPane.showConfirmDialog(null, 
					"Are you sure you want to exit?", 
					"Exit?", 
					JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION){
				//exits the entire application
				System.exit(0);
			}


		}

	}

	/**
	 * Any action regarding the Play button is handled in this class
	 */
	private class PlayListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GameScreen gameScreen;

			//creates the game with the specific type of difficulty chosen by user
			if(rdoRand.isSelected()){

				gameScreen = new GameScreen(1,roundsSlider.getValue());
				gameScreen.makeVisible();
			} else if(rdoState.isSelected()){
				gameScreen = new GameScreen(2,roundsSlider.getValue());
				gameScreen.makeVisible();
			} else if(rdoIntel.isSelected()){
				gameScreen = new GameScreen(3,roundsSlider.getValue());
				gameScreen.makeVisible();
			} else {
				//if they do not select a radio button they're told to do so
				JOptionPane.showMessageDialog(null,
						"Please choose a difficulty",
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	/**
	 * Any action regarding the History button is handled in this class
	 *
	 */
	private class HistoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			//checks to see if any previous encounters
			EncounterStorage history = new EncounterStorage();

			if(history.checkDataExists()){

				HistoryScreen historyScreen = new HistoryScreen();
				historyScreen.showScreen();

			} else {
				//if no previous encounter then it tells the user to play a game first
				JOptionPane.showMessageDialog(null,
						"You must play a game first",
						"Error",
						JOptionPane.ERROR_MESSAGE);

			}


		}

	}




}
