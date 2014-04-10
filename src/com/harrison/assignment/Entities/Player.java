/**
 * Class is the players role in the game, this holds their choice after choosing on the GameScreen.
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Entities;

public class Player extends Entity{

	/**
	 * Creates the player with their name
	 * 
	 * @param name indicates the users name
	 */
	public Player(String name){

		super(name);

	}



	/**
	 * Sets the users choice
	 * 
	 * @param choice indicates the choice, 1 = rock, 2 = paper, 3 = scissors
	 */
	public void setResult(int choice){
		setChoice(choice);
	}




	@Override
	public void update(int result) {

	}




	@Override
	public void setResult() {

	}




}
