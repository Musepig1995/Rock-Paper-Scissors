/**
 * Class is one of the enemies able to be faced, it works on the previous results of the user
 * to see what they have played in the past and gain an idea as to what it should choose.
 * Addition to this class in the future could involve pattern searching in the data from the results
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Entities;

import java.util.Random;

import com.harrison.assignment.Storage.ResultStorage;


public class IntelligentAI extends Entity {

	/**
	 * Represents the access to the previous results.
	 * Uses {@link ResultStorage} to view any previous results that entities have had with the user.
	 */
	private ResultStorage store = new ResultStorage();
	
	/**
	 * Represents the current node in the {@link ResultStorage}.
	 * This indicates where the entity should look for this set of {@link Result}.
	 */
	private int currentNode = 0;

	/**
	 * Creates the entity and gives it a name
	 */
	public IntelligentAI() {
		super("Intelligent");

	}

	/**
	 * Sets the result depending on previous values that the user has used
	 * on this combination of results.
	 * If none exist for this combination then choose a random value
	 */
	public void setResult(){
		int choice;
		if(store.isIndex(currentNode)){
			choice = getRandomChoice(store.getResultRock(currentNode), store.getResultPaper(currentNode), store.getResultScissor(currentNode));
			setChoice(choice);
		}else{
			Random random = new Random();
			choice = random.nextInt(3) + 1;
			setChoice(choice);
		}

	}

	/**
	 * Gets a random chance for each value depending on the weight of each choice
	 * The user may have chosen rock 8 times, and chosen both paper and scissors once
	 * this would give rock 80% to be picked and 10% each to the others
	 * 
	 * @param  rock indicates how many rocks have been chosen
	 * @param  paper indicates how many papers have been chosen
	 * @param  scissors indicates how many scissors have been chosen
	 * @return the choice chosen, 1 = rock, 2 = paper, 3 = scissors
	 */
	private int getRandomChoice(int rock, int paper, int scissors){

		int sum;
		sum = rock + paper + scissors;
		Random random = new Random();

		int randomValue = random.nextInt(sum) + 1;

		if(randomValue <= rock){
			//if most likely is rock then choose paper
			return 2;
		} else if(randomValue > rock && randomValue <= paper+rock){
			//if most likely is paper then choose scissors
			return 3;
		} else {
			//if most likely is scissors then choose rock
			return 1;
		}
	}

	/**
	 * Moves the node that the Entity is currently looking at depending 
	 * on the previous result
	 * 
	 * @param result indicates the result of the previous round, 1 = lose
	 * 2 = draw, 3 = win
	 */
	private void moveNode(int result){

		if(result == 1){
			currentNode = (3 * currentNode) + 1;
		} else if (result == 2){

			currentNode = (3* currentNode) + 2;

		} else {
			currentNode = (3 * currentNode) + 3;
		}

	}





	@Override
	public void update(int result) {
		moveNode(result);

	}









}
