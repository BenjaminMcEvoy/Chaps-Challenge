package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sound.sampled.*;

public class Sound {

	//Fields
	
	private String amb, move, lockedD, openD, keyPick, chapStung, win;
	

	/**
	 *  Sound Constructor
	 * 
	 * 	Fetches the current directory and iterates through the audio files in the resource
	 *  directory, and places the filename and s
	 */
	public Sound() {
	}

	/**
	 * Initialize function
	 * 
	 * Initializes fields and does a wipe of any
	 * remaining data when calling a new sound.
	 * 
	 */
	private void initialize() {
		amb = "res/audio/background3.wav";
		move = "res/audio/soundMove2.wav";
		lockedD = "res/audio/soundLocked.wav";
		openD = "res/audio/soundOpen.wav";
		keyPick = "res/audio/soundPickup.wav";
		chapStung = "res/audio/soundStung.wav";
		win = "res/audio/soundWin.wav";
	}
	
	
	/** Plays the ambient background sound
	 * 
	 *  Calls the playBackground function instead of play as it must be looped
	 * */
	public void playAmbient() {
		try {
			playBackground(amb);
		} catch (IOException | UnsupportedAudioFileException|LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/** Plays the locked door sound
	 * 
	 *  Calls the play function
	 * */
	public void playLockedDoor() {
			play(lockedD);
	}
	
	/** Plays the openDoor sound
	 * 
	 *  Calls the play function
	 * */
	public void playOpenDoor() {
			play(openD);
	}
	
	/** Plays the move sound
	 * 
	 *  Calls the play function
	 * */
	public void playMove() {
		play(move);
	}
	
	
	/** Plays the chap stung sound
	 * 
	 *  Calls the play function
	 * */
	public void playStung() {
		play(chapStung);
	}
	
	/** Plays the pickup sound
	 * 
	 *  Calls the play function
	 * */
	public void playKeyPickup() {
			play(keyPick);
	}
	
	/** Plays the move sound
	 * 
	 *  Calls the play function
	 * */
	public void playWin() {
		play(win);
	}

	/**
	 * Play function
	 * 
	 * Reponsible for playing the sound of a given file
	 * 
	 * @param soundName
	 */
	public void play(String soundName) {
		//loads the sound file and plays it
		try {
			File f = new File("./" + soundName);
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f.toURI().toURL());  
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 
	 * */
	public void playBackground(String soundName) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		//loads the sound file and plays it
		try {
			File f = new File("./" + soundName);
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f.toURI().toURL());  
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
            FloatControl volume= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-10.0f);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
}
