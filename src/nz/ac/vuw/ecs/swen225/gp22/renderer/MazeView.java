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

	// Get screen/board dimensions using a toolkit to fetch the screen size with static indents.
	private int indentBoard = currSDimension.height / 14;
	private int bWidth = (currSDimension.width - (INDENT_GAP + (indentBoard * 2))) / 2;
	private int sWidHei = 9;

	private int chapX, chapY, indentSize, imageSize;
	private Maze maze; 
	private Tile[][] chapView, mazeArray;
	private Set<Tile> tileSet;

	/*private Image chap, exitLock, exitTile, freeTile, infoTile, keyTileB, keyTileY, keyTileR, keyTileG,
	lockedDoorB, lockedDoorY, lockedDoorR, lockedDoorG, wallTile, treasureTile;*/
	
	public MazeView(Maze m){
		initialize();
		this.maze = m;
		this.tileSet = m.getAllTiles();
		initImage();
		//findChap();


		
		//width = m;
		//height = m.;
		
		/* Initialization of fields possibly
		 * set the parameters of the view
		 * possibly initialize the sound and animations too
		 * 
		 * */
	}
	
	/**
	 *  Initializes the indent and image size and
	 *  any other field or variables that may need
	 *  initialization.
	 * 
	 * */
	private void initialize() {
		//bWidth = 850;
		//bHeight = 850;
		indentSize = indentBoard;
		imageSize = bWidth/sWidHei;
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
	 * Draws the main character with coordinates recorded by
	 * the character itself
	 * 
	 * 
	 * */
	public void renderChap() {return;}
	
	/**
	 * Draws the mobs with coordinates recorded by
	 * the maze/board/mob itself
	 * 
	 * 
	 * */
	public void renderEnemies() {return;}
	
	/**
	 * Draws the tiles with coordinates recorded by
	 * the maze/board itself
	 * 
	 * 
	 * */
	public void renderTiles(Graphics graph) {
		//for(int x = 0; x < sHeight; x++) {
		//	for(int y = 0; y< sWidth; y++) {
		//		Tile tile = maze.getTileAt(x, y);
				//graph.drawImage();// fill here
				
				// maybe check instance of tile or use use a tile precheck
				// to see if it contains any entities or objects at the start
				
				// if there is che	ck all entities/containables
			//}
			
		//}
		
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
			
			Image test = ImageIO.read(new File(dir + "Chap.png"));
			
			mapImages.put("chap", ImageIO.read(new File(dir + "Chap.png")));
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

	/*public void drawImage(String fileName){
		Image fileImage = mapImages.get(fileName);

	}*/

	private void findChap() {
		for (Tile t: tileSet) {
			if (t instanceof ChapTile) {
				chapX = t.getX();
				chapY = t.getY();
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
	 *  Get the maze/board/pane width
	 * @return bWidth
	 * 
	 * */
	public int getbWidth() {
		return bWidth;
	}


	/**
	 * 
	 * 
	 * */
	
    /*findChap();
    chapView = [][]
    String dir = "res/graphics/";
for(int col = -4; col < 4; col++){
    for(int row = -4; row < 4; row++) {
        chapView[x][y] = mazeArray[chapx + col][chapy + row];
        y++;
    }
    x++;
}
    for(int col = 0; col < 9; col++){
        for(int row = 0; row < 9; row++) {
            graph2d.drawImage(dir + chapView[col][row].getFileName(), whatever, whatever, null);
        }*/
        
	
    /**
     *  Draws all tiles in a focus area
     *  @param Tile[][] board - Board array/camera view
     *  @param Graphics2D g - Graphics Pane
     * */    
    private void focusArea(Tile[][] board, Graphics2D g) {
    	for(int col = -4; col < 4; col++){
    	    for(int row = -4; row < 4; row++) {
    	        if(chapX + row >= 0 && chapY + col >=0 && chapX + row < board.length && chapY + col <board[0].length
    	        		&& board[chapX + row][chapY + col] != null) {
    	        	g.drawImage(mapImages.get(board[chapX+row][chapY+col].getFileName()), (row+4) * imageSize, (col+4)* imageSize, this);
    	        }
    	    }
    	}
    }
    
    /**
     *  Draws all tiles in a focus area
     *  @param int posX - Chap X position
     *  @param int posY - Chap Y position
     *  @param Tile[][] board - Board array/camera view
     *  @param Graphics2D g - Graphics pane
     * */  
    private void focusArea2(int posX, int posY, Tile[][] board, Graphics2D g) {
    	for(int col = -4; col < 4; col++){
    	    for(int row = -4; row < 4; row++) {
                if (posX > mazeArray.length || posY > mazeArray[0].length || posX < 0 || posY < 0) {
                    board[col][row] = null;
                } else {
                	board[col][row] = mazeArray[posX + col][posY + row];
                }
    	    }
    	}
        for(int col = 0; col < 9; col++){
            for(int row = 0; row < 9; row++) {
                g.drawImage(mapImages.get(board[col][row].getFileName()), (col+4) * imageSize, (col+4)*imageSize, null);
            }
        }
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	findChap();
    	Graphics2D graph2d = (Graphics2D) g;
    	Tile[][] cameraView = maze.getBoard();
    	//int cameraX = chapX;
    	//int cameraY = chapY;
    	focusArea(cameraView, graph2d);		
    	graph2d.drawImage(mapImages.get(cameraView[chapX][chapY].getFileName()), 4*imageSize, 4*imageSize, this);
    	
    	Tile prevChap = new ChapTile(chapX, chapY); // to do with animations if we get to it
    }
    
	/*public void paintComponent(Graphics g){
		System.out.println("m");
		mazeArray = maze.getBoard();
		findChap();
        super.paintComponent(g);
        Dimension s = getSize();
        Graphics2D graph2d = (Graphics2D) g;
       // graph2d.setBackground(Color.BLACK);
       // graph2d.fillRect(0, 0, 500, 500);
        System.out.println("paint");
        //int tX, tY;
        //Set<Tile> m = maze.getAllTiles();
        chapView = new Tile[sWidHei][sWidHei];
        initImage();
        for (int x = 0; x < sWidHei; x++) {
            for (int y = 0; y < sWidHei; y++) {
               // Tile tile2 = maze.getTileAt(x, y);
                int posX = chapX /2 + x * 32;
                int posY = chapY /2 + y * 32;
               // mazeArray = maze.getBoard();
                if (posX > mazeArray.length || posY > mazeArray[0].length || posX < 0 || posY < 0) {
                    chapView[x][y] = null;
                } else {
                	chapView[x][y] = maze.getTileAt(posX, posY);
                }
            }
        }
        
        for (int x = 0; x < sWidHei; x++) {
            for (int y = 0; y < sWidHei; y++) {
            	Tile curTile = chapView[x][y];
            	if(curTile == null) {
            		graph2d.setColor(Color.BLACK);
            		graph2d.fillRect(indentSize+x*imageSize, indentSize+y*imageSize, imageSize, imageSize);
            	}
            	else if(curTile instanceof EmptyTile){
            		graph2d.drawImage(mapImages.get("freeTile"), indentSize+x*imageSize, indentSize+y*imageSize, null);
            	}
            	else if(curTile instanceof InfoTile){
            		graph2d.drawImage(mapImages.get("infoTile"), indentSize+x*imageSize, indentSize+y*imageSize, null);
            	}
            	else if(curTile instanceof WallTile){
            		graph2d.drawImage(mapImages.get("wallTile"), indentSize+x*imageSize, indentSize+y*imageSize, null);
            	}
            	else if(curTile instanceof TreasureTile){
            		graph2d.drawImage(mapImages.get("treasureTile"), indentSize+x*imageSize, indentSize+y*imageSize, null);
            	}
            	else if(curTile instanceof LockedDoorTile){
            		graph2d.drawImage(mapImages.get("freeTile"), indentSize+x*imageSize, indentSize+y*imageSize, null);
            	}
            	else if(curTile instanceof ExitTile){
            		graph2d.drawImage(mapImages.get("exitTile"), indentSize+x*imageSize, indentSize+y*imageSize, null);
            	}
            	else if(curTile instanceof ExitLockTile){
            		graph2d.drawImage(mapImages.get("exitLock"), indentSize+x*imageSize, indentSize+y*imageSize, null);
            	}
            	else if(curTile instanceof ChapTile){
            		graph2d.drawImage(mapImages.get("Chap"), indentSize+x*imageSize, indentSize+y*imageSize, null);
            	}
            	else if(curTile instanceof EnemyTile){
            		graph2d.drawImage(mapImages.get("infoTile"), indentSize+x*imageSize, indentSize+y*imageSize, null);
            	}
            }
        }
	}*/
}
