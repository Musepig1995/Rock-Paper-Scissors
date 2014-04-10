/**
 * Class is used as a container to hold the amount of times the user has used
 * a specific value, each Result is a node in the tree and each node is able to link to three 
 * more, the index of the linked is 3n + x where n is equal to the current node and x is the result
 * of the round.
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */
package com.harrison.assignment.Storage;
public class Result {

	
	/**
	 * Represents the index of this {@link Result} in the tree of Results.
	 * Starts off as -1 in case of an error in which case we can work with it.
	 */
	private int index = -1;
	
	/**
	 * Represents the amount of times that Rock has been used.
	 */
	private int rock = 0;
	
	/**
	 * Represents the amount of times that Paper has been used.
	 */
	private int paper = 0;
	
	/**
	 * Represents the amount of times that Scissors has been used.
	 */
	private int scissor = 0;


	/**
	 * Gets the index of this Result in the tree of Results,
	 * this is not like a normal array index in which it goes 0-n but instead
	 * can skip many different numbers if those Results have never existed.
	 * For example you may have one Result with an index of 1 and then the next is at 4.
	 * 
	 * @return the index of this Result in relation to the tree
	 */
	public int getIndex(){

		return index;

	}

	/**
	 * Gets the amount of times that the user has picked rock on this Result
	 * 
	 * @return the amount of times user has picked rock
	 */
	public int getRock(){

		return rock;

	}

	/**
	 * Gets the amount of times that the user has picked paper on this Result
	 * 
	 * @return the amount of times user has picked paper
	 */
	public int getPaper(){

		return paper;

	}

	/**
	 * Gets the amount of times that the user has picked scissors on this Result
	 * 
	 * @return the amount of times user has picked scissors
	 */
	public int getScissor(){

		return scissor;
	}

	/**
	 * Used to add an instance of this result being reached
	 * 
	 * @param value indicates result, 1 for rock, 2 for paper, 3 for scissors
	 */
	public void addVisit(int value){

		if(value == 1){
			this.rock++;
		} else if(value == 2){
			this.paper++;
		} else{
			this.scissor++;
		}

	}

	public void setIndex(int index){
		this.index = index;
	}
	/**
	 * Sets the amount of times the user has picked each type of choice
	 * 
	 * @param rock indicates the amount of times rock was picked
	 * @param paper indicates the amount of times paper was picked
	 * @param scissors indicates the amount of times scissors was picked
	 */
	public void setValues(int rock, int paper, int scissors){

		this.rock = rock;
		this.paper = paper;
		this.scissor = scissors;


	}




}
