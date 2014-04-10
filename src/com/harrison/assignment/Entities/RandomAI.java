/**
 * Class is one of the enemies able to be faced, it works simply by choosing any of the three
 * values avaliable with an equal chance.
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Entities;
import java.util.Random;


public class RandomAI extends Entity {

	/**
	 * Creates this entity and gives it a name
	 */
	public RandomAI(){
		super("Random");
	}


	/**
	 * Generates a random number corresponding to the choice, 1 = rock, 2 = paper, 3 = scissors
	 */
	public void setResult(){

		Random random = new Random();
		setChoice(random.nextInt(3) + 1);
	}


	@Override
	public void update(int result) {
		// TODO Auto-generated method stub

	}






}
