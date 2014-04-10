/**
 * Class is used as the starting point of the entire program, it calls on the MenuScreen to allow
 * the users to make their choice.
 * I have tried to base my code structure around the concepts raised in the book 
 * "Clean Code" by Robert C. Martin 2008 - 
 * Reduce parameter amount, short meaningful methods, communication to the reader limited to 
 * variable and method names, comment only as a last resort.
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Main;
import javax.swing.ImageIcon;

import com.harrison.assignment.Screen.MenuScreen;


public class Main {

	/**
	 * Beginning of the application, shows the menu screen to the user
	 * Shows the {@link MenuScreen} to the user.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String args[]){

		MenuScreen menuScreen = new MenuScreen();
		menuScreen.showScreen();
	}

	/**
	 * Taken from Java Trails, creates an ImageIcon
	 * Reference - http://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
	 * Method is static to allow every class to use it 
	 * Would create own class if i had more time.
	 * 
	 * @param  path indicates the path to this resource
	 * @param  description indicates the description of this image
	 * @return the ImageIcon generated
	 */
	public static ImageIcon createImageIcon(String path,
			String description) {

		java.net.URL imgURL = Main.class.getResource(path);
		if (imgURL != null) {

			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}


}
