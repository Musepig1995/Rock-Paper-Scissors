/**
 * Class is used to hold many occurances of the Encounter class and to allow manipulation of
 * such data through this interface
 * In hindsight I should have extended ArrayList allowing access to the methods of that through
 * this class instead of having to have methods which duplicates the actions. Therefore
 * any future work could focus on this.
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Storage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class EncounterStorage {
	
	/**
	 * Represents the file that the encounters are stored in
	 */
	private final String FILE_NAME = "previous_encounters.txt";
	
	/**
	 * Used as the storage for all of the encounters taken from the file.
	 */
	private ArrayList<Encounter> encounters = new ArrayList<Encounter>();

	/**
	 * List of the possible names of entities, must be appended if a new {@link Entity} is added.
	 */
	private final String[] POSSIBLE_NAMES = {"Random", "State", "Intelligent"}; 
	
	/**
	 * Represents if an error has occured in reading from the file.
	 */
	private static boolean errorOccured = false; //static to fix a bug

	/**
	 * Creates a list of all the encounters with the different types of AI
	 */
	public EncounterStorage(){
		
		if(checkDataExists()){
			fillData();
		}


	}

	/**
	 * Identifies if this specific type of Entity has any previous encounters
	 * 
	 * @param  name indicates the type of Entity
	 * @return true if they have had previous encounter, false if not.
	 */
	public boolean thisValueExists(String name){

		for(int i = 0; i < encounters.size(); i++){
			if(encounters.get(i).getName().equals(name)){
				return true;
			}
		}
		return false;

	}
	/**
	 * Indicates if the Encounter at this specific index exists
	 * 
	 * @param  index indicates the index to be checked
	 * @return true if this Encounter exists, false if not.
	 */
	private boolean thisValueExists(int index){

		if(index >= encounters.size()){
			return false;
		} else if(index < 0){
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks to see if there any previous encounters to work with
	 * 
	 * @return true if there are previous encounters, false if not.
	 */
	public boolean checkDataExists(){
		File file = new File(FILE_NAME);
		
		if(file.exists()){
			if(checkFileEmpty()){
				return false;
			} else {
			return true;
			}
		}
		return false;

	}
	
	/**
	 * There is an issue of not being able to delete files, this checks if file is actually empty.
	 * Attempts to read the first line in the file to see if there is a value there.
	 * 
	 * @return true if file is empty, false if populated.
	 */
	private boolean checkFileEmpty(){
		File file = new File(FILE_NAME);
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			scanner.nextLine();
			scanner.close();
		} catch (FileNotFoundException e) {
		} catch (NoSuchElementException e) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the encounter of a specific type of Entity
	 * 
	 * @param name indicates the type of Entity by their name
	 * @return the Encounter value itself taken from the list, returns null if it doesn't exist
	 */
	public Encounter getEncounter(String name){

		for(int i = 0; i < encounters.size(); i++){

			if(encounters.get(i).getName().equals(name)){
				return encounters.get(i);
			}

		}

		return null; //null if the encounter of this type of Entity cannot be found


	}
	/**
	 * Gets the encounter at a specific index from the list, normally used if accessing all encounters
	 * 
	 * @param index indicates the index in the list of the specifc encounter
	 * @return the Encounter value itself found at this index
	 */
	public Encounter getEncounter(int index){

		if(thisValueExists(index)){
			return encounters.get(index);
		} else {
			return null;
		}



	}

	/**
	 * Gives access to methods for all Encounters in the list, can be used to find the size or access
	 * individual ones.  
	 * 
	 * @return the list of all encounters.
	 */
	public ArrayList<Encounter> getAllEncounters(){
		return encounters;
	}

	/**
	 * Gives access to the add method of the list of encounters, adds an encounter to the list
	 * 
	 * @param encounter indicates the Encounter object being added to the list
	 */
	public void addEncounter(Encounter encounter){
		encounters.add(encounter);
	}

	/**
	 * Replaces an Encounter in the list with a new one that may or may not have different values
	 * 
	 * @param name indicates the name of the type of Encounter that this is directed at
	 * @param newEncounter indicates the Encounter to replace the one in the list with
	 */
	public void replaceEncounter(String name, Encounter newEncounter){

		for(int i = 0; i < encounters.size(); i++){

			if(encounters.get(i).getName().equals(name)){

				encounters.get(i).setGamesWon(newEncounter.getGamesWon());
				encounters.get(i).setGamesDrew(newEncounter.getGamesDrew());
				encounters.get(i).setGamesLost(newEncounter.getGamesLost());

			}
		}


	}
	
	/**
	 * Removes every single {@link Encounter} from {@link #encounters}.
	 */
	public void removeAll(){
		
		while(!encounters.isEmpty()){
			encounters.remove(0);
		}
		
		storeData();
		
	}

	/**
	 * Gets the total amount of games that the user has played against every type of Entity
	 * 
	 * @return the amount of games played
	 */
	public int getTotalPlayed(){

		int sum = 0;

		for(int i = 0; i < encounters.size(); i++){

			sum += encounters.get(i).getGamesPlayed();

		}
		return sum;


	}

	/**
	 * Getst the total amount of times that the user has won a game against every type of Entity
	 * 
	 * @return the total amount of times the user has won
	 */
	public int getTotalWon(){


		int sum = 0;

		for(int i = 0; i < encounters.size(); i++){

			sum+= encounters.get(i).getGamesWon();

		}
		return sum;

	}

	/**
	 * Gets the amount of times that the user has drew against every type of Entity
	 * 
	 * @return the total amount of times the user has drew
	 */
	public int getTotalDrew(){


		int sum = 0;

		for(int i = 0; i < encounters.size(); i++){

			sum+= encounters.get(i).getGamesDrew();

		}
		return sum;

	}

	/**
	 * Gets the amount of times that the user has lost against every type of Entity
	 * 
	 * @return the total amount of times the user has lost
	 */
	public int getTotalLost(){


		int sum = 0;

		for(int i = 0; i < encounters.size(); i++){

			sum+= encounters.get(i).getGamesLost();

		}
		return sum;

	}


	/**
	 * Stores the list of Encounters in the encounter file
	 */
	public void storeData(){
		try{

			FileWriter fw = new FileWriter(FILE_NAME);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter fileWriter = new PrintWriter(bw);

			for(int i = 0; i < encounters.size(); i++){
				fileWriter.println(encounters.get(i).getName());
				fileWriter.println(encounters.get(i).getGamesWon());
				fileWriter.println(encounters.get(i).getGamesDrew());
				fileWriter.println(encounters.get(i).getGamesLost());
			}
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("IOException Error!");
			e.printStackTrace();
		}       
	}


	/**
	 * Attempts to read an integer value from a file.
	 * Uses a {@link NumberFormatException} to see if a non-number value is passed
	 * if so then returns 0 and notifies that there was a bug
	 * 
	 * @param  fileScanner indicates the {@link Scanner} object connected to the {@link File}
	 * @return the integer value of the record, will be 0 if an error
	 */
	private int attemptFileReadAmount(Scanner fileScanner){

		try{
			return Integer.parseInt(fileScanner.nextLine());
		} catch(NumberFormatException e){

			errorOccured = true;
			return 0; //file is corrupt therefore just assume 0
		}
	}

	/**
	 * Skips through this current encounter in the file.
	 * Because this is based on the name of the {@link Encounter} it still has 
	 * three values after it so we skip those three extra lines
	 * 
	 * @param fileScanner fileScanner indicates the {@link Scanner} object connected to the {@link File}
	 */
	private void skipThroughFile(Scanner fileScanner){

		fileScanner.nextLine();
		fileScanner.nextLine();
		fileScanner.nextLine();
	}

	/**
	 * Indicates whether an error has occured in reading the file
	 * @return a boolean value as to whether an error has occured
	 */
	public boolean getErrorOccured(){

		return errorOccured;
	}

	/**
	 * Attempts to read the name of an encounter.
	 * Uses the {@link #POSSIBLE_NAMES} to see if the name is linked to the one read, if it
	 * is unable to read it then it notifies that there was a bug in the file.
	 * 
	 * @param fileScanner indicates the {@link Scanner} object connected to the {@link File}
	 * @return a String value indicating the name of the encounter
	 */
	private String attemptFileReadName(Scanner fileScanner){

		String tempName = fileScanner.nextLine();

		for(int i = 0; i < POSSIBLE_NAMES.length; i++){
			if(tempName.equals(POSSIBLE_NAMES[i])){
				return tempName;
			} else if(POSSIBLE_NAMES[i].contains(tempName)){
				errorOccured = true;
				return POSSIBLE_NAMES[i];
			}
		}
		errorOccured = true;
		return "";

	}

	/**
	 * Loops through the encounters text file and attempts to populate the {@link #encounters} list.
	 */
	private void fillData(){

		File file = new File(FILE_NAME);
		int gamesWon = 0;
		int gamesDrew = 0;
		int gamesLost = 0;
		String name = "";


		try{

			Scanner fileScanner = new Scanner(file);


			while(fileScanner.hasNext()){

				//allows us to use these values as default if error occurs
				gamesWon = 0;
				gamesDrew = 0;
				gamesLost = 0;

				name = attemptFileReadName(fileScanner);
				
				if(name.isEmpty()){
					skipThroughFile(fileScanner);
				} else{
					
					gamesWon = attemptFileReadAmount(fileScanner);
					gamesDrew = attemptFileReadAmount(fileScanner);
					gamesLost = attemptFileReadAmount(fileScanner);

					encounters.add(new Encounter(name, gamesWon, gamesDrew, gamesLost));
				}


			}
			fileScanner.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(NoSuchElementException e){
			//warns that a bug occured, assumes the rest of the possible values are 0
			errorOccured = true;
			encounters.add(new Encounter(name, gamesWon, gamesDrew, gamesLost));
		}
		if(errorOccured){
			storeData(); //if the file was invalid before being read, it would now be sanitised 
			//so can be stored
		}




	}



}