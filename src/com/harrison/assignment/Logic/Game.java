/**
 * Class holds the game logic behind the GUI, links together the enemy AI and also the 
 * players access to the game logic
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Logic;
import com.harrison.assignment.Entities.Entity;
import com.harrison.assignment.Entities.Player;
import com.harrison.assignment.Storage.ResultStorage;


public class Game {

	/**
	 * Represents the total rounds that this {@link Game} will have.
	 * A value between 1-10.
	 */
	private int totalRounds;
	
	/**
	 * Represents the current round that this {@link Game} is on.
	 */
	private int currentRound = 1;
	
	/**
	 * Represents the amount of times that the user {@link Entity} has won.
	 */
	private int userScore = 0;
	
	/**
	 * Represents the amount of times that the enemy {@link Entity} has won.
	 */
	private int enemyScore = 0;
	
	/**
	 * Represents the amount of times that the player and enemy {@link Entity} have drew.
	 */
	private int draws = 0;
	
	/**
	 * Represents the {@link Player} in this {@link Game}
	 */
	private Player player;
	
	/**
	 * Represents the enemy in this {@link Game}
	 */
	private Entity enemy;
	
	/**
	 * Used as the medium to store the outcome of the {@link Game} in the {@link Result} text file. 
	 */
	private ResultStorage store = new ResultStorage();

	/**
	 * Constructs the game and creates both the enemy and player entities
	 * Constructs the {@link Game} with the amount of <code>rounds</code>
	 * and the type of {@link Entity}
	 * 
	 * @param rounds indicates how many rounds are to be played
	 * @param enemy indicates the type of Entity
	 */
	public Game(int rounds, Entity enemy){


		this.totalRounds = rounds;
		this.enemy = enemy;
		player = new Player("Player");
	}

	/**
	 * Plays one round of the game, updates any information regarding game logic.
	 * Starts off the {@link Game} logic depending on what the player's <code>choice</code>
	 * is.
	 * 
	 * @param choice indicates the choice of the user, 1 = rock, 2 = paper, 3 = scissors
	 */
	public void playRound(int choice){


		player.setResult(choice);
		enemy.setResult();
		store.addResult(currentRound - 1, choice, getResultId());
		enemy.update(getResultId());

		currentRound++;
		updateScore();

	}

	/**
	 * Gets the choice of the user
	 * 
	 * @return the users choice, 1 = rock, 2 = paper, 3 = scissors
	 */
	public int getUserChoice(){

		return player.getChoice();
	}

	/**
	 * Gets the choice of the enemy
	 * 
	 * @return the enemies choice, 1 = rock, 2 = paper, 3 = scissors
	 */
	public int getEnemyChoice(){

		return enemy.getChoice();

	}

	/**
	 * Gets the users score, how many times they've won
	 * 
	 * @return the users score
	 */
	public int getUserScore(){
		return userScore;
	}

	/**
	 * Gets the enemies score, how many times they've won
	 * 
	 * @return the enemies score
	 */
	public int getEnemyScore(){
		return enemyScore;
	}

	/**
	 * Gets the amount of times there has been a draw
	 * 
	 * @return the amount of draws
	 */
	public int getDraws(){
		return draws;
	}

	/**
	 * Gets the round the game is currently on
	 * 
	 * @return the current round
	 */
	public int getCurrentRound(){
		return currentRound;
	}

	/**
	 * Gets the total amount of rounds to be played
	 * 
	 * @return the total rounds to be played
	 */
	public int getTotalRounds(){
		return totalRounds;
	}

	/**
	 * Gets the difficulty of the enemy
	 * 
	 * @return the difficulty
	 */
	public String getDifficulty(){

		return enemy.getName();
	}

	/**
	 * Updates the score of the game depending on the previous results
	 */
	private void updateScore(){

		if(getResultId() == 3){

			userScore++;
		} else if(getResultId() == 1){

			enemyScore++;
		} else if(getResultId() == 2){

			draws++;
		}

	}

	/**
	 * Updates any results or encounter files
	 * Updates both {@link Encounter} and {@link Result} objects for this {@link Entity}.
	 */
	public void updateEnemyScore(){

		if(getFinishResult() == 1){
			//user wins
			enemy.setGamesLost(enemy.getGamesLost() + 1);
		} else if(getFinishResult() == 2){
			//draw
			enemy.setGamesDrew(enemy.getGamesDrew() + 1);
		} else if(getFinishResult() == 3) {
			enemy.setGamesWon(enemy.getGamesWon() + 1);
		} else {
			return;
		}

		store.storeValues(); //updates results files
		enemy.updateGameStats(); //updates encounter files
	}

	/**
	 * Gets the result after the entire game is finished
	 * 
	 * @return the result, 1 = user wins, 2 = draw, 3 = user loses
	 */
	public int getFinishResult(){

		if(userScore > enemyScore){
			return 1;
		} else if(userScore == enemyScore){
			return 2;
		} else {
			return 3;
		}

	}

	/**
	 * Finds out if the user won that round depending on the choice of both entities
	 * 
	 * @return true if player won that round, false if not
	 */
	private boolean playerWin(){

		if(player.getChoice() == 1 && enemy.getChoice() == 3){
			return true;
		} else if (player.getChoice() == 2 && enemy.getChoice() == 1){
			return true;
		} else if (player.getChoice() == 3 && enemy.getChoice() == 2){
			return true;
		} else 
			return false;
	}

	/**
	 * Finds out if the user drew that round
	 * 
	 * @return true if they drew, false if not
	 */
	private boolean playerDraw(){

		if(player.getChoice() == enemy.getChoice()){
			return true;
		} else {

			return false;
		}

	}

	/**
	 * Gets a messages relevent to the user for that round
	 * 
	 * @return a message for that round
	 */
	public String getOutcome(){

		if(playerWin()){
			return "You Won!";
		} else if(playerDraw()){
			return "A Draw!";
		} else {
			return "You Lose!";
		}

	}

	/**
	 * Gets a numbered value for the result of that round
	 * 
	 * @return the result value, 3 = win, 2 = draw, 1 = lose
	 */
	public int getResultId(){
		if(playerWin()){
			return 3;
		} else if(playerDraw()){
			return 2;
		} else {
			//if player loses
			return 1;
		}
	}

}
