/**
 * Class is used to hold many occurances of the Result class and to allow manipulation of
 * such data through this interface
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
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class ResultStorage {
	
	/**
	 * Represents the current index of the {@link Result}.
	 * Used to reduce the amount of computation required. 
	 */
	private int currentIndex = 0;
	
	/**
	 * Represents the constant file name that the results are stored.
	 * If changed here it will read/write from that file without error.
	 */
	private final String FILE_NAME = "result.txt";
	
	/**
	 * Represents the previous results from the text file.
	 */
	private ArrayList<Result> results = new ArrayList<Result>();
	
	/**
	 * Indicates if an error has occured in the reading of the text file.
	 */
	private boolean errorOccured = false;


	/**
	 * creates a list of all Results taken from the results file
	 */
	public ResultStorage(){
		fillData(); 
	}

	/**
	 * Checks to see if that combination of results has ever occured before
	 * 
	 * @param  index indicates the current index created from the combination of results
	 * @return return true if that index has ever been visited before, returns false if not
	 */
	public boolean isIndex(int index){
		//not all possible combinations are in the arraylist therefore we have to search through
		//to find if that one exists
		for(int i = 0; i < results.size(); i++){

			if(results.get(i).getIndex() == index){
				return true;
			} else if(results.get(i).getIndex() > index){

				//if the index accessed is higher that the aimed index, then there is no point 
				//looking any further
				return false;
			}
		}
		return false; 

	}

	/**
	 * Finds out how many times the user has choosen rock on this combination of results
	 * 
	 * @param  index indicates the current index generated from the combination of results
	 * @return returns the amount of times the user has choosen rock, returns 0 if this is the
	 * first time the user has had this combination of results.
	 */
	public int getResultRock(int index){

		for(int i = 0; i < results.size(); i++){

			if(results.get(i).getIndex() == index){
				return results.get(i).getRock();
			}
		}
		return 0;
	}

	/**
	 * Finds out how many times the user has choosen paper on this combination of results
	 * 
	 * @param  index indicates the current index generated from the combination of results
	 * @return returns the amount of times the user has choosen paper, returns 0 if this is the
	 * first time the user has had this combination of results.
	 */
	public int getResultPaper(int index){

		for(int i = 0; i < results.size(); i++){

			if(results.get(i).getIndex() == index){
				return results.get(i).getPaper();
			}
		}
		return 0;
	}

	/**
	 * Finds out how many times the user has choosen scissors on this combination of results
	 * 
	 * @param  index indicates the current index generated from the combination of results
	 * @return returns the amount of times the user has choosen scissors, returns 0 if this is the
	 * first time the user has had this combination of results.
	 */
	public int getResultScissor(int index){

		for(int i = 0; i < results.size(); i++){

			if(results.get(i).getIndex() == index){
				return results.get(i).getScissor();
			}
		}
		return 0;

	}


	/**
	 * Either adds a new entry to the result file, or updates it according to the
	 * users choice.
	 * 
	 * @param round signifies the current round in the current game
	 * @param choice signifies the users choice, 1 = rock, 2 = paper, 3 = scissors
	 * @param result signifies if the user won, lost or drew
	 */
	public void addResult(int round, int choice, int result){
		boolean found = false;
		for(int i = 0; i < results.size(); i++){

			if(results.get(i).getIndex() == currentIndex){

				results.get(i).addVisit(choice);
				found = true;
				break;
			}

		}
		if(!found){

			Result newResult = new Result();
			newResult.setIndex(currentIndex);
			newResult.addVisit(choice);
			results.add(newResult);

		}
		updateIndex(result);
	}

	/**
	 * Updates the current index depending on what the result of the last round was.
	 * 
	 * @param result indicates the result of the last round, 1 = lost, 2 = draw, 3 = win.
	 */
	private void updateIndex(int result){

		currentIndex = (3 * currentIndex) + result;

	}


	/**
	 * Sorts all result values and then stores into the respective file if it exists, if not then creates a file.
	 */
	public void storeValues(){
		sortData(results);
		try{


			FileWriter fw = new FileWriter(FILE_NAME);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter fileWriter = new PrintWriter(bw);
			for(int i = 0; i < results.size(); i++){
				fileWriter.println(results.get(i).getIndex());
				fileWriter.println(results.get(i).getRock());
				fileWriter.println(results.get(i).getPaper());
				fileWriter.println(results.get(i).getScissor());
			}
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("IOException Error!");
		}		

	}

	/**
	 * Sorts the result values by their index values
	 * 
	 * @param  input indicates the list wanting to be sorted
	 * @return the ordered list
	 */
	private ArrayList<Result> sortData(ArrayList<Result> input){


		if(input.size() <= 1){
			return input;
		}

		Collections.sort(input, new Comparator<Result>(){
			//by overriding the compare method we can make it compare the indexes of the list
			@Override
			public int compare(Result result1, Result result2) {
				if(result1.getIndex() > result2.getIndex()){
					return 1;
				} else {
					return -1;
				}
			}
		});

		return input;

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
		}  catch(NumberFormatException e){
			errorOccured = true;
			return 0;
		}

	}

	/**
	 * Populates the result list from the result file
	 */
	private void fillData(){

		int index = 0;
		int rock = 0;
		int paper = 0;
		int scissors = 0;


		try{
			File file = new File(FILE_NAME);
			Scanner fileScanner = new Scanner(file);

			while(fileScanner.hasNext()){
				Result newResult = new Result();
				try{



					index = Integer.parseInt(fileScanner.nextLine());
					rock = attemptFileReadAmount(fileScanner);
					paper = attemptFileReadAmount(fileScanner);
					scissors = attemptFileReadAmount(fileScanner);

					newResult.setIndex(index);
					newResult.setValues(rock, paper, scissors);

					results.add(newResult);

				}catch(NoSuchElementException e){
					errorOccured = true;
					newResult.setIndex(index);
					newResult.setValues(rock, paper, scissors); //any defaults are left if not there
					results.add(newResult); 

				}


			}	
			fileScanner.close();
			if(errorOccured){
				storeValues(); //if any bugs were found that set of values were removed
				//therefore they need to be stored in the text file again
			}

		}catch(FileNotFoundException e){

			//very likely that this may be players first turn therefore not treated as an issue
			System.out.println("File not found"); 

		}


	}


}
