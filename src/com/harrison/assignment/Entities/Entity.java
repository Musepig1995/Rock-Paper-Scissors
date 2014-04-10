/**
 * Class is used as a partial interface for both the users and enemies, some methods are able
 * to be adapted depending on the type of AI and some are left unchanged.
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Entities;
import com.harrison.assignment.Storage.Encounter;
import com.harrison.assignment.Storage.EncounterStorage;


public abstract class Entity {

	/**
	 * Indicates the choice of this {@link Entity}.
	 * Represents the choice as a <code>int</code> value.
	 * 1 = Rock, 2 = Paper, 3 = Scissors.
	 */
	private int choice = 0;
	
	/**
	 * Indicates the amount of games this {@link Entity} has played.
	 */
	private int gamesPlayed = 0;
	
	/**
	 * Indicates the amount of games this {@link Entity} has lost.
	 */
	private int gamesLost = 0;
	
	/**
	 * Indicates the amount of games this {@link Entity} has won.
	 */
	private int gamesWon = 0;
	
	/**
	 * Indicates the amount of games this {@link Entity} has drew.
	 */
	private int gamesDrew = 0;
	
	/**
	 * Indicates the name of this {@link Entity}.
	 * Is used as an identifier for the type of {@link Entity}
	 */
	private String name = "";
	
	/**
	 * Represents the previous {@link Encounter} that this Entity has had with either the user user or enemy.
	 */
	private Encounter encounters;

	/**
	 * Creates the entity, gives it a name and gets all previous encounters
	 * with this type of entity
	 * 
	 * @param name indicates the name that the entity can be identified by.
	 */
	public Entity(String name){

		this.name = name;

		fillEncounter();

	}

	/**
	 * Gets the choice made by the entity
	 * 
	 * @return the choice made by the entity, 1 = rock, 2 = paper, 3 = scissors
	 */
	public int getChoice(){

		return choice;
	}

	/**
	 * Gets previous encounters with this enemy.
	 * Gets the previous {@link Encounter} for this type of {@link Entity} from the encounters file.
	 */
	private void fillEncounter(){
		EncounterStorage history = new EncounterStorage();
		if(history.checkDataExists()){

			encounters = history.getEncounter(name);

			if(encounters != null){
				this.gamesWon = encounters.getGamesWon();
				this.gamesDrew = encounters.getGamesDrew();
				this.gamesLost = encounters.getGamesLost();
				this.gamesPlayed = encounters.getGamesPlayed();
			} else {
				encounters = new Encounter(this.name, 0,0,0);
			}
		} else {
			encounters = new Encounter(this.name, 0, 0, 0);
		}

	}

	/**
	 * Updates the Entity if needed
	 * 
	 * @param result indicates what the result of the previous round
	 */
	abstract public void update(int result);

	/**
	 * Gets the amount of times the user has played against this entity type
	 * 
	 * @return the amount of times the user has played this entity
	 */
	public int getGamesPlayed(){
		return gamesPlayed;

	} 

	/**
	 * Gets the amount of times the entity has won against the user
	 * 
	 * @return the amount of wins
	 */
	public int getGamesWon(){
		return gamesWon;
	}

	/**
	 * Gets the amount of times the entity has drew against the user
	 * 
	 * @return the amount of draws
	 */
	public int getGamesLost(){
		return gamesLost;
	}

	/**
	 * Gets the amount of times the entity has lost against the user
	 * 
	 * @return the amount of losts
	 */
	public int getGamesDrew(){
		return gamesDrew;
	}

	/**
	 * Gets the name of the Entity
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the choice of this entity
	 * 
	 * @param choice the value they picked, 1 = rock, 2 = paper, 3 = scissors
	 */
	public void setChoice(int choice){
		if(choice > 0 && choice < 4){
			this.choice = choice;
		} else {
			this.choice = -1;
		}
	}

	/**
	 * Sets the amount of times that the entity has won 
	 * 
	 * @param gamesWon amount of times won
	 */
	public void setGamesWon(int gamesWon){
		this.gamesWon = gamesWon;
		setGamesPlayed();
	}

	/**
	 * Sets the amount of times that the entity has lost
	 * 
	 * @param gamesLost amount of times lose
	 */
	public void setGamesLost(int gamesLost){
		this.gamesLost = gamesLost;
		setGamesPlayed();
	}

	/**
	 * Sets the amount of times that the entity has drew
	 * 
	 * @param gamesDrew amount of times drew
	 */
	public void setGamesDrew(int gamesDrew){
		this.gamesDrew = gamesDrew;
		setGamesPlayed();
	}

	/**
	 * Sets the amount of times this entity has played altogether
	 */
	private void setGamesPlayed(){
		this.gamesPlayed = gamesWon + gamesDrew + gamesLost;
	}

	/**
	 * Updates the encounter values in the files for this entity, if none exists then adds them
	 */
	public void updateGameStats(){

		encounters.setGamesWon(gamesWon);
		encounters.setGamesDrew(gamesDrew);
		encounters.setGamesLost(gamesLost);

		EncounterStorage history = new EncounterStorage();

		if(history.checkDataExists()){
			if(history.thisValueExists(name)){
				history.replaceEncounter(name, encounters);	
			} else {

				history.addEncounter(encounters);
			}
		} else {
			history.addEncounter(encounters);

		}
		history.storeData();


	}


	/**
	 * Gets the value that defeats a given values, for example paper beats rock, therefore if you
	 * pass rock into this method it will return paper
	 * 
	 * @param  choice indicates your choice, 1 = rock, 2 = paper, 3 = scissors
	 * @return the value that defeats your choice, 1 = rock, 2 = paper, 3 = scissors
	 */
	protected int getDefeatingValue(int choice){

		if(choice == 1){
			return 2;
		} else if(choice == 2){
			return 3;
		} else {
			return 1;
		}
	}

	/**
	 * Gets the value that loses to a given value, for example rock beats scissors, therefore if you
	 * pass rock into this method it will return scissors
	 * 
	 * @param  choice indicates your choice, 1 = rock, 2 = paper, 3 = scissors
	 * @return the value that you would win against, 1 = rock, 2 = paper, 3 = scissors
	 */
	protected int getVictoryValue(int choice){

		if(choice == 1){
			return 3;
		} else if(choice == 2){
			return 1;
		} else {
			return 2;
		}

	}


	/**
	 * Sets the entities choice however they do it
	 */
	abstract public void setResult();




}
