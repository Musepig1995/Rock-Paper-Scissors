/**
 * Class is used as a container to capture the amount of times that a 
 * specific type of enemy has won, drew and lost.
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Storage;

public class Encounter {

	/**
	 * Represents the name of the {@link Entity} that these encounters are based on.
	 * Can be used as a unique identifier for the type of {@link Entity}.
	 */
	private String name = "";
	
	/**
	 * Represents the amount of games won.
	 */
	private int gamesWon = 0;
	
	/**
	 * Represents the amount of games lost.
	 */
	private int gamesLost = 0;
	
	/**
	 * Represents the amount of games drew.
	 */
	private int gamesDrew = 0;

	/**
	 * This constructor takes all the values of the Encounter and stores them
	 * 
	 * @param name indicates the name of the Entity
	 * @param gamesWon indicates the amount of times this Entity has lost, showing 
	 * how many times the user has won against them
	 * @param gamesDrew indicates the amount of times this Entity has drew, showing 
	 * how many times the user has drew against them
	 * @param gamesLost indicates the amount of times this Entity has won, showing 
	 * how many times the user has lost against them
	 */
	public Encounter(String name, int gamesWon, int gamesDrew, int gamesLost){

		this.name = name;
		this.gamesWon = gamesWon;
		this.gamesLost = gamesLost;
		this.gamesDrew = gamesDrew;

	}
	/**
	 * Gets the name of the Entity that this Encounter refers to
	 * 
	 * @return the name of the Entity
	 */
	public String getName(){

		return name;
	}

	/**
	 * Gets the amount of times the user has won against this Entity
	 * 
	 * @return the amount of times the user has won
	 */
	public int getGamesWon(){
		return gamesWon;
	}

	/**
	 * Gets the amount of times the user has drew against this Entity
	 * 
	 * @return the amount of times the user has drew
	 */
	public int getGamesDrew(){
		return gamesDrew;
	}

	/**
	 * Gets the amount of times the user has lost against this Entity
	 * 
	 * @return the amount of times the user has lost
	 */
	public int getGamesLost(){
		return gamesLost;
	}

	/**
	 * Gets the total amount of times that the user has played against this Entity
	 * 
	 * @return the total amount of times played
	 */
	public int getGamesPlayed(){
		return gamesWon + gamesDrew + gamesLost;
	}

	/**
	 * Sets the amount of times the user has won against this Entity
	 * 
	 * @param gamesWon the amount of times the user has won
	 */
	public void setGamesWon(int gamesWon){
		this.gamesWon = gamesWon;
	}

	/**
	 * Sets the amount of times the user has drew against this Entity
	 * 
	 * @param gamesDrew the amount of times the user has drew
	 */
	public void setGamesDrew(int gamesDrew){
		this.gamesDrew = gamesDrew;
	}

	/**
	 * Sets the amount of times the user has lost against this Entity
	 * 
	 * @param gamesLost the amount of times the user has lost 
	 */
	public void setGamesLost(int gamesLost){
		this.gamesLost = gamesLost;
	}



}
