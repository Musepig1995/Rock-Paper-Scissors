/**
 * Class is used as the main screen for the game, it allows the user to be able to choose
 * their value from the three set values; rock, paper or scissors. Then it shows the outcome 
 * with the choosen value from the enemy. The stats behind this is then shown on the right
 * of the frame. 
 * Sound_on and Sound_off images are taken from Google's android icons
 * Reference: Google
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Screen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.harrison.assignment.Entities.IntelligentAI;
import com.harrison.assignment.Entities.RandomAI;
import com.harrison.assignment.Entities.StateAI;
import com.harrison.assignment.Logic.Game;
import com.harrison.assignment.Main.Main;
import com.harrison.assignment.Sound.Sound;


public class GameScreen extends JFrame{

	/**
	 * ID of the Frame.
	 */
	private static final long serialVersionUID = 6500251023513057982L;
	
	/**
	 * Represents the Rock image.
	 */
	private final ImageIcon ROCK_IMAGE = Main.createImageIcon("/res/Images/Rock.jpg", "Rock");
	
	/**
	 * Represents the Paper image.
	 */
	private final ImageIcon PAPER_IMAGE = Main.createImageIcon("/res/Images/Paper.jpg", "Paper");
	
	/**
	 * Represents the Scissors image.
	 */
	private final ImageIcon SCISSORS_IMAGE = Main.createImageIcon("/res/Images/Scissors.jpg", "Scissor");
	
	/**
	 * Represents the Sound enabled image.
	 */
	private final ImageIcon SOUND_ON = Main.createImageIcon("/res/Images/Sound_on.png", "sound on");
	
	/**
	 * Represents the Sound disabled image.
	 */
	private final ImageIcon SOUND_OFF = Main.createImageIcon("/res/Images/Sound_off.png", "sound off");
	
	/**
	 * Represents the Height of the Frame.
	 */
	private final int HEIGHT = 600;
	
	/**
	 * Represents the Width of the Frame.
	 */
	private final int WIDTH = 800;
	
	/**
	 * Used as the game logic for this Screen.
	 * Uses {@link Game} to play the actual game, allows us to trigger
	 * rounds on {@link ChoiceListener}
	 */
	private Game game;
	
	/**
	 * Used as the sound player for any sound effects
	 * 
	 * @see {@link Sound}
	 */
	private Sound soundPlayer = new Sound();
	
	/**
	 * Used as the {@link JPanel} that holds the users and enemies choices.
	 */
	private JPanel outcomePane; 
	
	/**
	 * The label that represents the Rock choice.
	 */
	private JLabel lblRockChoice;
	
	/**
	 * The label that represents the Paper choice.
	 */
	private JLabel lblPaperChoice;
	
	/**
	 * The label that represents the Scissors choice.
	 */
	private JLabel lblScissorsChoice;
	
	/**
	 * The label that represents the round the user is on.
	 */
	private JLabel lblRound;
	
	/**
	 * The label that represents the amount of times the enemy has won.
	 */
	private JLabel lblEnemyScore;
	
	/**
	 * The {@link JLabel} that represents the amount of times the user has won.
	 */
	private JLabel lblUserScore;
	
	/**
	 * The {@link JLabel} that represents the amount of times a draw has occured.
	 */
	private JLabel lblDraws;
	
	/**
	 * The {@link JLabel} that represents if the sound is enabled or not.
	 */
	private JLabel lblSound;
	
	/**
	 * A value that indicates if the user has made a choice or not.
	 */
	private boolean choiceDisplayed = false;
	
	/**
	 * Represents whether the screen should attempt to play sound.
	 */
	private boolean playSound = true;

	/**
	 * Create the frame and populate it with the choices and the stats panel
	 * 
	 * @param difficulty denotes the type of game played, 1 = {@link RandomAI}, 2 = {@link StateAI}, 3 = {@link IntelligentAI}.
	 */
	public GameScreen(int difficulty, int rounds) {

		setDifficulty(difficulty, rounds);

		createScreen();

		add(createContentPane());


	}


	/**
	 * Instantiates the game with the given difficulty
	 * Instantiates the {@link Game} with the given difficulty to identify the {@link Entity} 
	 * to be used. 
	 * 
	 * @param difficulty denotes the type of game played, 1 = {@link RandomAI}, 2 = {@link StateAI}, 3 = {@link IntelligentAI}.
	 * @param rounds
	 */
	private void setDifficulty(int difficulty, int rounds){

		if(difficulty == 1){
			game = new Game(rounds, new RandomAI());

		}else if(difficulty == 2){
			game = new Game(rounds, new StateAI());

		} else if(difficulty == 3){
			game = new Game(rounds, new IntelligentAI());
		} else {
			game = null;
			System.out.println("Error! Wrong value given: " + difficulty);
		}
	}



	/**
	 * Creates the stats labels container, gives inital values.
	 * Creates the stats {@link JPanel} and gives the inital values, these
	 * are generally not taken from {@link Game} to help usability.
	 * 
	 * @return the JPanel containing all of the stats
	 */
	private JPanel createStatsPane(){

		JPanel stats = new JPanel();

		stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));

		//pane width can be changed, however it seems that the height cannot.
		stats.setMaximumSize(new Dimension(150,0));
		stats.setPreferredSize(stats.getMaximumSize());

		lblRound = new JLabel("Round: 1/" + game.getTotalRounds());
		JLabel lblDifficulty = new JLabel("Difficulty: " + game.getDifficulty()); //never changes so we can create here
		lblUserScore = new JLabel("Your Score: 0");
		lblEnemyScore = new JLabel("Enemy Score: 0");
		lblDraws = new JLabel("Draws: 0");


		stats.add(lblRound);
		stats.add(lblDifficulty);
		stats.add(lblUserScore);
		stats.add(lblEnemyScore);
		stats.add(lblDraws);


		stats.setBorder(BorderFactory.createLineBorder(Color.black));

		return stats;
	}

	/**
	 * Creates the main panel and adds all the relevent components to it
	 * Creates the {@link JPanel} that is to be used for the main arrangement 
	 * of the {@link JFrame} components.
	 * 
	 * @return the Panel to be added to the frame
	 */
	private JPanel createContentPane(){
		JPanel contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new BorderLayout());

		contentPane.add(createChoicePane(), BorderLayout.SOUTH);
		contentPane.add(createStatsPane(), BorderLayout.EAST);
		contentPane.add(createOutcomePane(), BorderLayout.CENTER);

		contentPane.add(createSoundLabel(), BorderLayout.WEST);


		return contentPane;
	}

	/**
	 * Creates the Panel holding the choices that are available to the user.
	 * Creates the {@link JPanel} and populates it with the three {@link JLabel} that
	 * are avaliable to the user.
	 * 
	 * @return the {@link JPanel} holding the possible choices 
	 */
	private JPanel createChoicePane(){
		JPanel choicePane = new JPanel();

		choicePane.setLayout(new BoxLayout(choicePane, BoxLayout.X_AXIS));
		choicePane.setPreferredSize(new Dimension(800, 200));

		//creates the three options based on their respective images
		lblRockChoice = createChoiceLabel("Rock", ROCK_IMAGE);
		lblPaperChoice = createChoiceLabel("Paper", PAPER_IMAGE);
		lblScissorsChoice = createChoiceLabel("Scissors", SCISSORS_IMAGE);

		choicePane.add(Box.createRigidArea(new Dimension(50,0)));
		choicePane.add(lblRockChoice);
		choicePane.add(Box.createRigidArea(new Dimension(10, 0)));
		choicePane.add(lblPaperChoice);
		choicePane.add(Box.createRigidArea(new Dimension(10, 0)));
		choicePane.add(lblScissorsChoice);

		return choicePane;
	}

	/**
	 * Creates the sound label with its icon.
	 * Instantiates {@link #lblSound}, sets its inital {@link ImageIcon} and adds the listener.
	 * 
	 * @return the {@link JLabel} created in this method.
	 */
	private JLabel createSoundLabel(){

		lblSound = new JLabel();

		lblSound.setIcon(SOUND_ON);
		lblSound.setVerticalAlignment(SwingConstants.TOP);
		lblSound.addMouseListener(new SoundButtonListener());

		return lblSound;
	}

	/**
	 * Updates the sound labels image depending on inital state.
	 * Updates {@link #lblSound} depending on if the user wants sound to play or not.
	 * This is indicated by {@link #playSound}.
	 */
	private void updateSoundLabel(){

		if(playSound){
			lblSound.setIcon(SOUND_ON);
		} else {
			lblSound.setIcon(SOUND_OFF);
		}
	}

	/**
	 * Displays the screen to the user
	 */
	public void makeVisible(){

		setVisible(true);

	}

	/**
	 * Gives the round label a value.
	 * Sets the {@link #lblRound} text value, values taken from {@link Game}.
	 */
	private void setRound(){

		//if they have just played the last round they need to be able to view the screen
		//for a few more seconds thus should display the previous if this occurs
		if(game.getCurrentRound() > game.getTotalRounds()){
			lblRound.setText("Round: " + game.getTotalRounds() + "/" + game.getTotalRounds());
		} else {
			lblRound.setText("Round: " + game.getCurrentRound() + "/" + game.getTotalRounds());
		}

	}

	/**
	 * Gives the user score label a value.
	 * Sets the {@link #lblUserScore} text value, values taken from {@link Game}.
	 */
	private void setUserScore(){

		lblUserScore.setText("Your Score: " + game.getUserScore());

	}

	/**
	 * Gives the enemy score label a value.
	 * Sets the {@link #lblEnemyScore} text value, values taken from {@link Game}.
	 */
	private void setEnemyScore(){

		lblEnemyScore.setText("Enemy Score: " + game.getEnemyScore());	

	}

	/**
	 * Gives the draw label a value.
	 * Sets the {@link #lblDraws} text value, values taken from {@link Game}.
	 */
	private void setDraws(){

		lblDraws.setText("Draws: " + game.getDraws());
	}

	/**
	 * Creates the screen and its properties.
	 * Sets the {@link JFrame} default values, anything specific to the 
	 * screen itself when no components are involved should be updated here.
	 */
	private void createScreen(){

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(ROCK_IMAGE.getImage());
		setTitle("Rock, Paper, Scissors: AI Edition");
		setSize(WIDTH,HEIGHT);

	}

	/**
	 * Creates the container for the outcome.
	 * Creates the {@link JPanel} in which the user and enemies choice will be shown.
	 * 
	 * @return the JPanel with its format, labels are added later
	 */
	private JPanel createOutcomePane(){

		outcomePane = new JPanel();
		outcomePane.setBorder(new EmptyBorder(10, 50, 10, 10));


		outcomePane.setLayout(new FlowLayout());

		return outcomePane;
	}

	/**
	 * Creates a clickable label that represents a choice for the user.
	 * Uses a {@link JLabel} that is linked to {@link ChoiceListener} that allows it to be clickable.
	 * 
	 * @param text indicates the text to go under the image in the label
	 * @param image indicates the image that the label will hold
	 * @return a JLabel that represents a choice
	 */
	private JLabel createChoiceLabel(String text, ImageIcon image){

		JLabel label = new JLabel(text, image, JLabel.CENTER);

		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setName(text); //allows it to be referred to in Listener

		label.addMouseListener(new ChoiceListener());
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		return label;


	}

	/**
	 * Forces the {@link JFrame} to refresh making sure that all components are properly updated immediately.
	 */
	private void refreshScreen(){
		revalidate();
		repaint();

	}

	/**
	 * Removes all labels from the {@link #outcomePane} so that others can be added.
	 */
	private void removeOutcome(){
		outcomePane.removeAll();
		refreshScreen();
	}

	/**
	 * Creates a label that represents one of the chosen values by either the player or other entity.
	 * Creates a specific {@link JLabel} with an {@link ImageIcon} corresponding to the choice made.
	 * 
	 * @param choice indicates the value they chose, 1 = rock, 2 = paper, 3 = scissors
	 * @param userType indicates the type entities type, either user or enemy
	 * @param color indicates the colour of the border, this represents the outcome
	 * @return the JLabel that represents the chosen value
	 */
	private JLabel createOutcomeLabel(int choice, String userType, Color color){

		JLabel label;

		if(choice == 1){
			label = new JLabel(userType + ": Rock",ROCK_IMAGE,JLabel.CENTER);
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.setVerticalTextPosition(JLabel.BOTTOM);
			label.setBorder(BorderFactory.createLineBorder(color, 5));

		} else if (choice == 2){
			label = new JLabel(userType + ": Paper",PAPER_IMAGE, JLabel.CENTER);
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.setVerticalTextPosition(JLabel.BOTTOM);
			label.setBorder(BorderFactory.createLineBorder(color, 5));

		} else {
			label = new JLabel(userType + ": Scissors", SCISSORS_IMAGE, JLabel.CENTER);
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.setVerticalTextPosition(JLabel.BOTTOM);
			label.setBorder(BorderFactory.createLineBorder(color, 5));

		}

		return label;
	}

	/**
	 * Updates choice label to have a different border.
	 * Gives one of the choice {@link JLabel} a border of set {@link Color} with 
	 * a set <code>width</code>.
	 * 
	 * @param value indicates what label to give a border too
	 * @param color indicates the colour of the border
	 * @param width indicates the width of the border
	 */
	private void setChoiceBorder(int value, Color color, int width){

		if(value == 1){

			lblRockChoice.setBorder(BorderFactory.createLineBorder(color, width));

		}else if(value == 2){

			lblPaperChoice.setBorder(BorderFactory.createLineBorder(color, width));
		} else {

			lblScissorsChoice.setBorder(BorderFactory.createLineBorder(color, width));

		}

	}

	/**
	 * Gives every choice {@link JLabel} their normal border
	 */
	private void resetBorder(){

		setChoiceBorder(1, Color.black, 2);
		setChoiceBorder(2, Color.black, 2);
		setChoiceBorder(3, Color.black, 2);
	}

	/**
	 * Displays the user and enemies choice to the screen.
	 * Display the {@link #outcomePane} of the {@link GameScreen}, indicates whether
	 * the user won, lost or drew and what theirs and the enemies choices were.
	 * 
	 * @param userChoice users values, indicates what they picked
	 * @param enemyChoice enemies values, indicates what they picked
	 * @param result indicates the message to be shown to the user
	 */
	private void setOutcomePane(){


		int userChoice = game.getUserChoice();
		int enemyChoice = game.getEnemyChoice();
		String result = game.getOutcome();
		int resultId = game.getResultId();

		JLabel resultLabel = new JLabel(result);

		outcomePane.add(createOutcomeLabel(userChoice, "You", getUserColor(resultId)));
		outcomePane.add(Box.createRigidArea(new Dimension(0, 15)));
		outcomePane.add(resultLabel);
		outcomePane.add(Box.createRigidArea(new Dimension(0, 15)));
		outcomePane.add(createOutcomeLabel(enemyChoice, "Enemy", getEnemyColor(resultId)));
	}

	/**
	 * Converts the result for the {@link Player} as to what the colour of their {@link JLabel} will be
	 * 
	 * @param result indicates the result, 3 = win, 2 = draw, 1 = lose
	 * @return the colour representing the outcome for the user
	 */
	private Color getUserColor(int result){

		if(result == 3){
			return Color.green;
		} else if(result == 2){
			return Color.gray;
		} else {
			return Color.red;
		}
	}

	/**
	 * Converts the result for the enemy as to what the colour of their {@link JLabel} will be
	 * 
	 * @param result indicates the result, 3 = lose, 2 = draw, 1 = win
	 * @return the colour representing the outcome for the enemy
	 */
	private Color getEnemyColor(int result){

		if(result == 3){
			return Color.red;
		} else if(result == 2){
			return Color.gray;
		} else {
			return Color.green;
		}
	}

	/**
	 * Updates the screen to reflect game outcome.
	 * Updates every {@link JLabel} and the {@link #outcomePane}.
	 */
	private void updateScreen(){

		setOutcomePane();
		setUserScore();
		setEnemyScore();
		setDraws();
		setRound();
	}

	
	
	/**
	 * Used when the sound button is clicked, will disable sound if enabled, and vice versa.
	 * 
	 * @author Harrison
	 */
	private class SoundButtonListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {

			if(playSound){
				soundPlayer.attemptStop();
				playSound = false;
			} else {
				playSound = true;
			}
			updateSoundLabel();

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {


		}

	}

	/**
	 * Any mouse events regarding the choice labels is handled here
	 */
	private class ChoiceListener implements MouseListener{

		@Override
		/**
		 * Plays the game when one of the choice labels is clicked
		 */
		public void mouseClicked(MouseEvent event) {


			if(!choiceDisplayed){
				choiceDisplayed = true;; //prevents any other choices being clicked while outcome is shown
				play(event);
			}


		}

		@Override
		/**
		 * Acts as a hover effect to further show that the choice label is a clickable object
		 */
		public void mouseEntered(MouseEvent event) {


			if(!choiceDisplayed){

				//we gave each choice label a name so that we could identify them
				String value = "";
				value = event.getComponent().getName();

				//increases the border width to add focus to that current label
				if(value.equals("Rock")){
					setChoiceBorder(1,Color.black, 5);
				} else if(value.equals("Paper")){
					setChoiceBorder(2, Color.black, 5);
				} else if(value.equals("Scissors")){
					setChoiceBorder(3, Color.black,5);
				}
			}else {
				return;
			}
		}

		@Override
		/**
		 * Resets the border of the {@link JLabel} if the mouse leaves that choice {@link JLabel}.
		 */
		public void mouseExited(MouseEvent arg0) {
			if(!choiceDisplayed){
				resetBorder();
			} else {
				return;
			}
		}

		/**
		 * Starts the game cycle, this will update GUI components.
		 * Uses {@link Game} to play the game. 
		 * 
		 * @param event indicates which label was clicked, must be passed from {@link #mouseClicked(MouseEvent)}
		 */
		private void play(MouseEvent event){

			if(playSound){
				playChoiceSound();
				playRound(event, 2000); //sound is 2 seconds long therefore a 2 seconds gap before results shown is needed
				timedRoundEnd(5000);
			} else {
				playRound(event, 500); //results shown after half a second to give the illusion that its thinking
				timedRoundEnd(3500);
			}
		}

		/**
		 * Plays one round of the game.
		 * Starts the {@link Game} logic off, updates this clicked {@link JLabel} to have a 
		 * green border to indicate its been clicked.
		 * 
		 * @param event indicates which label was clicked, must be passed from {@link #mouseClicked(MouseEvent)}
		 */
		private void playRound(MouseEvent event, int timeMillis){

			//we gave each choice label a name so that we could identify them
			String value = "";
			value = event.getComponent().getName();

			if(value.equals("Rock")){
				game.playRound(1);
				setChoiceBorder(1, Color.green, 5);

			} else if(value.equals("Paper")){
				game.playRound(2);
				setChoiceBorder(2, Color.green, 5);
			} else if(value.equals("Scissors")) {
				game.playRound(3);
				setChoiceBorder(3, Color.green, 5);
			} else {

				//in theory it should never reach here but just in case.
				JOptionPane.showMessageDialog(null,
						"Something has gone wrong, please click one of the three options",
						"Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			timedUpdate(timeMillis);

		}

		/**
		 * Displays the outcome of the game to the player.
		 * After the final round of {@link Game} displays the outcome to the player
		 * using a {@link FinishDialog}, if enabled it will also play a sound.
		 * Will dispose this {@link GameScreen}
		 */
		private void endGame(){

			choiceDisplayed = true; //makes it so user cannot click any choics after the final round
			if(playSound){
				playFinishSound();
			}

			timedDispose(3000); //time until game screen disposes itself

			FinishDialog scoreScreen = new FinishDialog(game.getFinishResult());
			scoreScreen.showDialog();

		}

		/**
		 * Updates any {@link JLabel} that are in the stats {@link JPanel}. 
		 * Uses a {@link Timer} to do this so keep in mind any threading issues with other {@link Timer}
		 * used in the code. 
		 * 
		 * @param timeMillis indicates the time in milliseconds that you want the timer to go through before it calls the method
		 */
		private void timedUpdate(int timeMillis){

			Timer timer = new Timer(timeMillis ,new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {

					updateScreen();

					if(game.getCurrentRound() > game.getTotalRounds()){
						game.updateEnemyScore();
					}
				}
			});


			timer.setRepeats(false);
			timer.start();
		}

		/**
		 * Resets the screen after a given amount of milliseconds.
		 * Uses a {@link Timer} to do this so keep in mind any threading issues with other {@link Timer}
		 * used in the code. 
		 * 
		 * @param timeMillis indicates the time in milliseconds that you want the timer to go through before it calls the method
		 */
		private void timedRoundEnd(int timeMillis){

			Timer timer = new Timer(timeMillis ,new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					removeOutcome();
					resetBorder();
					choiceDisplayed = false;

					if(game.getCurrentRound() > game.getTotalRounds()){

						endGame();
					}
				}
			});

			timer.setRepeats(false);
			timer.start();
		}

		/**
		 * Disposes of this screen after a given amount of milliseconds.
		 * Uses a {@link Timer} to do this so keep in mind any threading issues with other {@link Timer}
		 * used in the code. 
		 * 
		 * @param timeMillis indicates the time in milliseconds that you want the timer to go through before it calls the method
		 */
		private void timedDispose(int timeMillis){

			//after 4 seconds it will close down the game window which gives enough time to see your result
			Timer timer = new Timer(timeMillis ,new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose(); //in theory should close down this window
				}
			});

			timer.setRepeats(false);
			timer.start();

		}



		/**
		 * Plays the respective sound for the outcome of the game.
		 * Uses {@link Sound} to play one of the possible sounds to indicate to the 
		 * player their outcome.
		 */
		private void playFinishSound(){
			
			soundPlayer = new Sound();
			
			if(playSound){
				if(game.getFinishResult() == 1){
					soundPlayer.play(Sound.WIN);
				} else if(game.getFinishResult() == 2){
					soundPlayer.play(Sound.DRAW);
				} else {
					soundPlayer.play(Sound.LOSE);
				} 
			}
		}

		/**
		 * Plays the "Rock, Paper, Scissors" sound.
		 * Uses {@link Sound} to play the choice sound whenever the player
		 * picks one of the possible options.
		 */
		private void playChoiceSound(){
			
			soundPlayer = new Sound();
			soundPlayer.play(Sound.CHOICE_MADE);
		}





		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {


		}



	}



}



