spackage nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sound.sampled.*;

public class Sound {

	//Fields
	private Maze maze;

	private Map<String, Clip> mapSounds = new HashMap<>();
	private Set<Tile> tiles = new HashSet<>();

	/**
	 *  Sound Constructor
	 * 
	 * 	Fetches the current directory and iterates through the audio files in the resource
	 *  directory, and places the filename and s
	 */
	public Sound(Maze m) {
		initialize();
		this.maze = m;

		// Gets current working directory and iterates through the directory of audio files
		File[] fileArray =  new File(System.getProperty("user.dir")+ "/res/audio").listFiles();
		if (fileArray != null) {
			for (File file : fileArray) {
				try {
					AudioInputStream audio = AudioSystem.getAudioInputStream(file);
					Clip clip = AudioSystem.getClip();
					clip.open(audio);
					String soundName = file.getName().substring(0 , file.getName().length()-4);
					mapSounds.put(soundName, clip);
				} catch ( LineUnavailableException| UnsupportedAudioFileException| IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Initialize function
	 * 
	 * Initializes fields and does a wipe of any
	 * remaining data when calling a new sound.
	 * 
	 */
	private void initialize() {
		mapSounds.clear();
		tiles.clear();
	}

	/**
	 * Play function
	 * 
	 * Reponsible for playing the sound of a given file
	 * 
	 * @param soundName
	 */
	public void play(String soundName) {
		Clip clip = mapSounds.get(soundName);
		clip.setFramePosition(0);
		clip.start();
	}

	
}
