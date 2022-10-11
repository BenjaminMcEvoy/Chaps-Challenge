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
public class MazeView extends JPanel{
	
	// Fields
	private static final int INDENT_WINDOW = 100;
	private static final int INDENT_GAP = 40;
	private Map<String, Image> mapImages = new HashMap<String, Image>();

	// Fetches the current screen dimension
	private Dimension currSDimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - INDENT_WINDOW,
	 Toolkit.getDefaultToolkit().getScreenSize().height - INDENT_WINDOW);
	private Dimension dim;

	// Get screen/board dimensions using a toolkit to fetch the screen size with static indents.
	private int indentBoard = currSDimension.height / 14;
	private int bWidth = (currSDimension.width - (INDENT_GAP + (indentBoard * 2))) / 2;
	private int vRange = 9; //range of vision

	private int chapX, chapY, indentSize;
	private int imageSize = 42;
	private Maze maze; 
	private Tile[][] chapView, mazeArray;
	private Set<Tile> tileSet;
	
	public MazeView(Maze m){
		initialize();
		this.maze = m;
		this.tileSet = m.getAllTiles();
		initImage();
	}
	
	/**
	 *  Initializes the indent and image size and
	 *  any other field or variables that may need
	 *  initialization.
	 * 
	 * */
	private void initialize() {
		chapView = new Tile[vRange][vRange];
		indentSize = indentBoard;
		
	}
	
	
	/**
	 * 
	 * 
	 * 
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
			mapImages.put("chapLeft", ImageIO.read(new File(dir + "chap_left.png")));
			mapImages.put("chapRight", ImageIO.read(new File(dir + "chap_right.png")));
			mapImages.put("wallTile", ImageIO.read(new File(dir + "wallTile.png")));
			mapImages.put("treasureTile", ImageIO.read(new File(dir + "treasureTile.png")));
			mapImages.put("exitLock", ImageIO.read(new File(dir + "exitLock.png")));
			mapImages.put("exitTile", ImageIO.read(new File(dir + "exitTile.png")));
			mapImages.put("freeTile", ImageIO.read(new File(dir + "freeTile.png")));
			mapImages.put("infoTile", ImageIO.read(new File(dir + "infoTile.png")));
			
			mapImages.put("keyTileR", ImageIO.read(new File(dir + "keyTile_red.png")));
			mapImages.put("keyTileG", ImageIO.read(new File(dir + "keyTile_green.png")));
			mapImages.put("keyTileB", ImageIO.read(new File(dir + "keyTile_blue.png")));
			mapImages.put("keyTileY", ImageIO.read(new File(dir + "keyTile_yellow.png")));

			mapImages.put("lockedDoorR", ImageIO.read(new File(dir + "lockedDoor_red.png")));
			mapImages.put("lockedDoorG", ImageIO.read(new File(dir + "lockedDoor_green.png")));
			mapImages.put("lockedDoorB", ImageIO.read(new File(dir + "lockedDoor_blue.png")));
			mapImages.put("lockedDoorY", ImageIO.read(new File(dir + "lockedDoor_yellow.png")));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void findChap() {
		for (Tile t: tileSet) {
			if (t instanceof ChapTile) {
				chapX = t.getX(maze);
				chapY = t.getY(maze);
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
    	for(int col = -4; col < 4; col++){
    	    for(int row = -4; row < 4; row++) {
    	        if(chapX + row >= 0 && chapY + col >=0 && chapX + row < board.length && chapY + col <board[0].length) {
    	        	if(board[chapX + row][chapY + col] != null) {
    	        	g.drawImage(mapImages.get(board[chapX+row][chapY+col].getFileName()), indentSize + row*imageSize, indentSize +col* imageSize, this);

    	        }}
    	    }
    	}
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	findChap();
    	Graphics2D graph2d = (Graphics2D) g;
    	Tile[][] cView = maze.getBoard();
    	chapView = cView;
    	focusArea(cView, graph2d);
    	
    	Tile prevChap = new ChapTile(chapX, chapY); // to do with animations if we get to it
    }
}
