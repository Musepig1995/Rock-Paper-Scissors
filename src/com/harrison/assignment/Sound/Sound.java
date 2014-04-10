/**
 * Class is used to play sound clips, it can only use .wav file formats
 * Main code basis for this is based on - https://www.youtube.com/watch?v=nUKya2DvYSo
 * Reference: baralaborn
 * 
 * @author Harrison South
 * ID: i7244619
 * Date: 05/04/2014
 * Task: Semester 2 Programming Assignment
 * 
 */

package com.harrison.assignment.Sound;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
	
	/**
	 * Used to control how the sound is used.
	 * 
	 * @see {@link Mixer}
	 */
	private Mixer mixer; 
	
	/**
	 * Used to represent the sound file being played.
	 * 
	 * @see {@link Clip}
	 */
	private Clip clip;
	
	/**
	 * Is the file path that plays the "ROCK, PAPER, SCISSORS" sound.
	 */
	public static final String CHOICE_MADE = "/res/Sound/choice.wav";
	
	/**
	 * Is the file path that plays the "You Win" sound.
	 */
	public static final String WIN = "/res/Sound/win.wav";
	
	/**
	 * Is the file path that plays the "You Drew" sound.
	 */
	public static final String DRAW = "/res/Sound/draw.wav";
	
	/**
	 * Is the file path that plays the "You Lost" sound.
	 */
	public static final String LOSE = "/res/Sound/lose.wav";


	/**
	 * Constructor creates the means to play the sounds using a Mixer
	 */
	public Sound() {


		Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();

		mixer = AudioSystem.getMixer(mixInfos[0]);

		DataLine.Info dataInfo = new DataLine.Info(Clip.class,null);

		try {
			clip = (Clip) mixer.getLine(dataInfo);
		} catch(LineUnavailableException e){
			e.printStackTrace();
		}

		



	}

	/**
	 * Plays the sound clip once only, using the mixer created in the constructor
	 * 
	 * @param path indicates the pathname of the audio file, there are a number of 
	 * predetermined ones which can be found accessing Sound statically, you can also provide
	 * your own .wav files by entering their path name
	 */
	public void play(String path){

		File fileCheck = new File("src" + path);
		if(!fileCheck.exists()){
			System.out.println("Sound File Not Found");
			return;
		}

		try {

			//accesses the sound file by creating an audio stream from the provided file path
			URL soundURL = Sound.class.getResource(path);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);

			clip.open(audioStream);

		} catch(LineUnavailableException e){
			e.printStackTrace();
		} catch(UnsupportedAudioFileException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}

		//by manipulating the clips controls we can adjust the volume of the sound clip
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(+6.0f); //increases the volume by 6 decibals, this is close to the maximum

		clip.setFramePosition(0); //used as a precaution when playing another clip
		clip.loop(0); //indicates that it will only play once
		clip.start();

	}
	
	public void attemptStop(){
			try{
				clip.stop();
			} catch(Exception e){
				
			}
			
		
	}

}
