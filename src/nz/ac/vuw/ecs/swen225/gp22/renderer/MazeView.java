package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.awt.*;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.imageio.spi.ImageReaderWriterSpi;
import javax.swing.*;
import java.io.*;

import nz.ac.vuw.ecs.swen225.gp22.domain.ChapTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

/** 
 * Maze View class to update and render the maze whenever
 * the character moves or does an action, responsible for 
 * 
 * 
 * */
public class MazeView extends JPanel implements ActionListener{
	
	// Fields
	private static final int indentWINDOW = 100;
	private static final int indentGAP = 40;
	private Map<String, Image> mapImages = new HashMap<String, Image>();

	private Dimension currSDimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - indentWINDOW,
	 Toolkit.getDefaultToolkit().getScreenSize().height - indentWINDOW);

	private int indentVIEW = currSDimension.height / 14;
	private int bWidth = (currSDimension.width - (indentGAP + (indentVIEW * 2))) / 2;
	private int sWidHei = 9;

	private int bHeight, tSize, tView, chapX, chapY;
	private Maze maze;
	private Tile[][] chapView, mazeArray;
	private Set<Tile> tileSet;

	private Image chap, exitLock, exitTile, freeTile, infoTile, keyTileB, keyTileY, keyTileR, keyTileG,
	lockedDoorB, lockedDoorY, lockedDoorR, lockedDoorG, wallTile, treasureTile;
	
	MazeView(Maze m){
		init();
		this.maze = m;
		this.tileSet = m.getAllTiles();
		initImage();
		findChap();


		
		//width = m;
		//height = m.;
		
		/* Initialization of fields possibly
		 * set the parameters of the view
		 * possibly initialize the sound and animations too
		 * 
		 * */
	}
	
	private void init() {
		bWidth = 850;
		bHeight = 850;
		tSize = 50;
		tView = 5;
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
			mapImages.put("wallTile", ImageIO.read(new File(dir + "wallTile.png")));
			mapImages.put("treasureTile", ImageIO.read(new File(dir + "treasureTile.png")));
			mapImages.put("exitLock", ImageIO.read(new File(dir + "exitLock.png")));
			mapImages.put("exitTile", ImageIO.read(new File(dir + "exitTile.png")));
			mapImages.put("freeTile", ImageIO.read(new File(dir + "freeTile.png")));
			mapImages.put("infoTile", ImageIO.read(new File(dir + "infoTile.png")));

			mapImages.put("keyTileR", ImageIO.read(new File(dir + "keyTileR.png")));
			mapImages.put("keyTileG", ImageIO.read(new File(dir + "keyTileG.png")));
			mapImages.put("keyTileB", ImageIO.read(new File(dir + "keyTileB.png")));
			mapImages.put("keyTileY", ImageIO.read(new File(dir + "keyTileY.png")));

			mapImages.put("lockedDoorR", ImageIO.read(new File(dir + "lockedDoorR.png")));
			mapImages.put("lockedDoorG", ImageIO.read(new File(dir + "lockedDoorG.png")));
			mapImages.put("lockedDoorB", ImageIO.read(new File(dir + "lockedDoorB.png")));
			mapImages.put("lockedDoorY", ImageIO.read(new File(dir + "lockedDoorY.png")));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void drawImage(String fileName){
		Image fileImage = mapImages.get(fileName);

	}

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
	 *  Get the maze/board/pane height
	 * @return bHeight
	 * 
	 * */
	public int getbHeight() {
		return bHeight;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D graph2d = (Graphics2D) g;
		graph2d.setBackground(Color.BLACK);
		graph2d.fillRect(0, 0, getWidth(), getHeight());

		int tX, tY;
		Set<Tile> m = maze.getAllTiles();
		chapView = new Tile[sWidHei][sWidHei];
		for (int x = 0; x < sWidHei; x++) {
			for (int y = 0; y < sWidHei; y++) {


				Tile tile2 = maze.getTileAt(x, y);
				int posX = chapX /2 + x;
				int posY = chapY /2 + y;
				if (posX > mazeArray.length || posY > mazeArray[0].length || posX < 0 || posY < 0) {
					chapView[x][y] = null;
				}

				//graph2d.drawImage(tile2, arg1, arg2, arg3, null);
			}
		}

		for (Tile til : m) {
			//Image img = mapImages.get(til.getClass());
			System.out.println(til.getClass());
			//g.drawImage(img, til.getX(), til.getY(), null);
			tX = til.getX();
			tY = til.getY();	
		}
		
	}
		
}
