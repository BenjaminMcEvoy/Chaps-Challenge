package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.awt.*;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.imageio.spi.ImageReaderWriterSpi;
import javax.swing.*;
import java.io.*;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

/** 
 * Maze View class to update and render the maze whenever
 * the character moves or does an action, responsible for 
 * 
 * 
 * */
public class MazeView extends JComponent{
	
	// Fields
	private Map<String, Image> mapImages = new HashMap<String, Image>();
	
	private int vRange = 9; //range of vision
	private int chapX, chapY, indentSize;
	private int imageSize = 42;
	private int cameraSize = vRange * imageSize; // 9 * 42
	
	private Maze maze; 
	private Tile[][] chapView, mazeArray;
	private Set<Tile> tileSet;
	private Sound sound;

	
	/**
	 * Constructor of MazeView
	 * 
	 * Receives the maze in use, and initializes the board to draw and play the sound.
	 * 
	 * @param Maze m 
	 * */
	public MazeView(Maze m){
		initialize();
		this.maze = m;
		this.tileSet = m.getAllTiles();
		this.sound = new Sound();
		initImage();
		sound.playAmbient();

	}
	
	/**
	 *  Initializes the indent and image size and
	 *  any other field or variables that may need
	 *  initialization.
	 * 
	 * */
	private void initialize() {
		chapView = new Tile[vRange][vRange];
		indentSize = 168;
		setPreferredSize(new Dimension(cameraSize, cameraSize));
	}
	
	/** Updates the board
	 * */
	private void updateMaze() {
		mazeArray = maze.getBoard();
	}

	/** 
	 * Image initialisation
	 * 
	 * Assigns all image fields to the files from resource folder into the hashmap
	 * 
	 * TODO: Make an loop of the res folder and loop through to put in the map rather
	 * than manually setting it. 
	 * @return hash
	 */
	private void initImage(){
		try {
			String dir = "res/graphics/";

			mapImages.put("chap", ImageIO.read(new File(dir + "Chap.png")));
			mapImages.put("chap_left", ImageIO.read(new File(dir + "chap_left.png")));
			mapImages.put("chap_right", ImageIO.read(new File(dir + "chap_right.png")));
			mapImages.put("chap_up", ImageIO.read(new File(dir + "chap_up.png")));
			mapImages.put("chap_down", ImageIO.read(new File(dir + "chap_down.png")));
			mapImages.put("enemy_left", ImageIO.read(new File(dir + "enemy_left.png")));
			mapImages.put("enemy_right", ImageIO.read(new File(dir + "enemy_right.png")));

			mapImages.put("wallTile", ImageIO.read(new File(dir + "wallTile.png")));
			mapImages.put("treasureTile", ImageIO.read(new File(dir + "treasureTile.png")));
			mapImages.put("exitLock", ImageIO.read(new File(dir + "exitLock.png")));
			mapImages.put("exitTile", ImageIO.read(new File(dir + "exitTile.png")));
			mapImages.put("freeTile", ImageIO.read(new File(dir + "freeTile.png")));
			mapImages.put("infoTile", ImageIO.read(new File(dir + "infoTile.png")));
			
			mapImages.put("keyTile_red", ImageIO.read(new File(dir + "keyTile_red.png")));
			mapImages.put("keyTile_green", ImageIO.read(new File(dir + "keyTile_green.png")));
			mapImages.put("keyTile_blue", ImageIO.read(new File(dir + "keyTile_blue.png")));
			mapImages.put("keyTile_yellow", ImageIO.read(new File(dir + "keyTile_yellow.png")));

			mapImages.put("lockedDoor_red", ImageIO.read(new File(dir + "lockedDoor_red.png")));
			mapImages.put("lockedDoor_green", ImageIO.read(new File(dir + "lockedDoor_green.png")));
			mapImages.put("lockedDoor_blue", ImageIO.read(new File(dir + "lockedDoor_blue.png")));
			mapImages.put("lockedDoor_yellow", ImageIO.read(new File(dir + "lockedDoor_yellow.png")));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void findChap() {
		for (Tile t: tileSet) {
			if (t instanceof ChapTile) {
				chapX = maze.getTileX(t);
				chapY = maze.getTileY(t);
			}
		}
	}
	
	/**
	 *  Get the maze/board/pane
	 * @return maze
	 * 
	 * */
	public Maze getMaze() {
		return maze;
	}
   
	
    /**
     *  Draws all tiles in a focus area
     *  @param Tile[][] board - Board array/camera view
     *  @param Graphics2D g - Graphics Pane
     * */    
    private void focusArea(Tile[][] board, Graphics2D g) {
    	for(int col = -5; col <5; col++){
    	    for(int row = -5; row < 5; row++) {
    	        if(chapX + row >= 0 && chapY + col >=0 && chapX + row < board.length && chapY + col <board[0].length) {
    	        	if(board[chapX + row][chapY + col] != null) {
    	        	g.drawImage(mapImages.get(board[chapX+row][chapY+col].getFileName()), indentSize + row*imageSize, indentSize +col* imageSize, this);
    	        	}else {
    	        		g.drawImage(mapImages.get("freeTile"), indentSize + row*imageSize, indentSize +col* imageSize, this);
    	        	}
    	        }else {
    	        		g.drawImage(mapImages.get("freeTile"), indentSize + row*imageSize, indentSize +col* imageSize, this);
    	        	}
    	    }
    	}
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	findChap();
    	Graphics2D graph2d = (Graphics2D) g;
    	Tile[][] cView = maze.getBoard();
    	focusArea(cView, graph2d);
    }
}
