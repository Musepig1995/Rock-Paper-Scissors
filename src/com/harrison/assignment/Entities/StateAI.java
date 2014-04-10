/**
 * Class is one of the enemies able to be faced, it works on the finite-state machine
 * idea in which each state has a corresponding percentage chance of what value it picks in regards
 * to the last round. After each round it moves state depending on the outcome. 
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Entities;
import java.util.Random;


public class StateAI extends Entity{

	/**
	 * Represents the entities current state in the finite state machine.
	 */
	private int state = 0;

	/**
	 * Creates the entity and gives it a name
	 */
	public StateAI(){

		super("State");

	}

	/**
	 * Generates its choice depending on what state it currently is in
	 * and what its choice in the previous was.
	 */
	public void setResult(){
		Random random = new Random();
		switch(state){
		case 0: 
			//first state generates completely random value
			setChoice(random.nextInt(3) + 1);
			break;
		case 1: 
			setChoice(getRandomChance(70, 20, 10));

			break;
		case 2: 
			setChoice(getRandomChance(40, 30, 30));
			break;
		case 3:
			setChoice(getRandomChance(50, 25, 25));
			break;
		case 4:
			setChoice(getRandomChance(50, 20, 30));
			break;
		}


	}

	/**
	 * Gets a random value from the given input.
	 * Gives a random value depending on the parameters passed, if 
	 * <code>chanceSame</code> is 100 then it will return the same value it
	 * used before.
	 * 
	 * @param  chanceSame the chance that it will pick the same value
	 * @param  chanceOpp the chance it will pick the opposite value
	 * @param  chanceDiff the chance it will pick the other value
	 * @return returns the value they will make, 1 = rock, 2 = paper, 3 = scissor 
	 */
	private int getRandomChance(int chanceSame, int chanceOpp, int chanceDiff){
		Random random = new Random();
		int temp = random.nextInt(100) + 1;

		if(temp <= chanceSame){
			return getChoice();
		} else if(temp > chanceSame && temp <= (chanceOpp+chanceSame)){
			return getDefeatingValue(getChoice());
		} else {
			return getVictoryValue(getChoice());
		}
	}

	/**
	 * this method is used to set this entities state depending
	 * on what the previous outcome is
	 * 
	 * @param lose if the result is a lost then go to this state
	 * @param draw if the result is a draw then go to this state
	 * @param win if the result is a win then go to this state
	 * @param result this is the result, 1 = lose, 2 = draw, 3 = win
	 */
	private void setState(int lose, int draw, int win, int result){

		if(result == 1){
			state = lose;
		} else if(result == 2){
			state = draw;
		} else {
			state = win;
		}

	}

	/**
	 * changes the state depending on what the current state is and what 
	 * the result of the previous round was
	 * 
	 * @param result indicates the result of the user, 1 = lose, 2 = draw, 3 = win
	 */
	private void changeState(int result){

		switch(state){

		case 0: 
			setState(3, 2, 1, result);
			break;
		case 1:
			setState(4,1,3, result);
			break;
		case 2: 
			setState(4,2,3, result);
			break;
		case 3:
			setState(1,4,3, result);
			break;
		case 4: 
			setState(2,2,3, result);
			break;
		}
	}

	@Override
	public void update(int result) {

		changeState(result);
	}





}
