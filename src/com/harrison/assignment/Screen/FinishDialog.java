/**
 * Class is used to tell the user the outcome of the game, if they win/draw/lose then it will
 * tell them this with their respective image and sound.
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Timer;

import com.harrison.assignment.Main.Main;


public class FinishDialog extends JDialog {

	/**
	 * Serial ID for {@link FinishDialog}
	 */
	private static final long serialVersionUID = 1367933246183519084L;
	
	/**
	 * Represents the Height of the {@link Dialog}
	 */
	private final int HEIGHT = 500;
	
	/**
	 * Represents the Width of the {@link Dialog}
	 */
	private final int WIDTH = 600;
	
	/**
	 * Represents the Win image.
	 */
	private final ImageIcon WIN_IMAGE = Main.createImageIcon("/res/Images/Win.jpg", "Rock");
	
	/**
	 * Represents the Draw image.
	 */
	private final ImageIcon DRAW_IMAGE = Main.createImageIcon("/res/Images/Draw.jpg", "Paper");
	
	/**
	 * Represents the Lose image.
	 */
	private final ImageIcon LOST_IMAGE = Main.createImageIcon("/res/Images/Lose.jpg", "Scissors");	

	/**
	 * Constructs the Dialog.
	 * Creates the {@link JDialog} with an {@link ImageIcon} depending on what the <code>result</code> is.
	 * 
	 * @param result indicates what the outcome is, 1 = win, 2 = draw, 3 = lose
	 */
	public FinishDialog(int result){

		setSize(WIDTH,HEIGHT);
		add(createLabel(result));
		disposeCountdown(3000);

	}

	/**
	 * Creates the label that holds the image corresponding to what the outcome was
	 * Creates the {@link JLabel} with an {@link ImageIcon} linked to whatever <code>result</result>
	 * was passed.
	 * 
	 * @param result indicates what the outcome was, 1 = win, 2 = draw, 3 = lost
	 * @return 		 the {@link JLabel} showing the outcome
	 */
	private JLabel createLabel(int result){
		JLabel label = new JLabel();
		if(result == 1){
			label.setIcon(WIN_IMAGE);
		} else if(result == 2){
			label.setIcon(DRAW_IMAGE);
		} else {
			label.setIcon(LOST_IMAGE);
		}
		return label;

	}

	private void disposeCountdown(int timeMillis){

		Timer timer = new Timer(timeMillis ,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});

		timer.setRepeats(false);
		timer.start();
	}

	/**
	 * Shows the dialog to the user
	 */
	public void showDialog(){
		setVisible(true);
	}


}
