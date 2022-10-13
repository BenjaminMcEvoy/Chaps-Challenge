package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.*;

import nz.ac.vuw.ecs.swen225.gp22.persistency.XMLLoader;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Animate;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Sound;

public class Maze {

	// fields
	public enum direction{
		UP,
		DOWN,
		LEFT,
		RIGHT,
		NULL
	}

	// private Tile[][] board = new Tile[10][10];
	private Tile[][] board;
	private ChapTile chap;
	private XMLLoader loader;
	private Sound sound;
	private int level;

	// private int totalTreasureCount;

	private final int width, height;
	
	private boolean hasWon = false;

	// private Set<Key> availableKeys = new HashSet<Key>();

	/*
	 * 
	 * Maze constructor will be the class in the domain which stores the states and
	 * locations
	 * 
	 * for each tile in the current level, will take some kind of persistency class
	 * as the parameter
	 * 
	 * which would contain the data for all the tiles, including their locations.
	 * 
	 * 
	 * 
	 */

	public Maze(int width, int height, int level) {

		this.width = width;
		this.height = height;
		this.level = level;
		this.sound = new Sound();

		assert width > 0 && height > 0;

		board = new Tile[width][height];
	}
	
	/*
	 * Secondary constructor for if the level is not specified in the parser
	 */
	
	public Maze(int width, int height) {

		this.width = width;
		this.height = height;
		this.level = 1;

		assert width > 0 && height > 0;

		board = new Tile[width][height];
	}

	/*
	 * 
	 * method to check if something movable can move to a specific tile and if so
	 * move it
	 * 
	 */

	public void moveTile(CharacterTile t, int x, int y) {

		Tile target = board[x][y];

		boolean collect = false;
		boolean isChap = t instanceof ChapTile;

		

		if (target instanceof WallTile) {

			throw new IllegalArgumentException("cannot move chap into a wall tile.");

		}

		else if (target instanceof KeyTile) {		
			if (isChap) {
				((ChapTile) t).addKey((KeyTile) target);
				sound.playKeyPickup();
				collect = true;
			}
		}

		else if (target instanceof LockedDoorTile) {
			LockedDoorTile door = (LockedDoorTile) target;
			if (isChap) {
				if (!((ChapTile) t).hasKey(new KeyTile(door.getColor()))) {
					sound.playLockedDoor();
					throw new IllegalArgumentException("cannot move chap into a locked door tile.");
					
				}
				else {
					sound.playOpenDoor();
					((ChapTile) t).removeKey(door.getColor());
					target = new EmptyTile();
				}
			}
			else {
				throw new IllegalStateException("cannot move enemy into a locked door tile.");
			}
		}

		else if (target instanceof TreasureTile) {
			
			if (isChap) {
				target = new EmptyTile();
				sound.playKeyPickup();
				collect = true;
				/*if (checkTreasures() < 1 ) {
					clearTileType(new ExitLockTile());
				}*/
			}

		} 
		else if (target instanceof ExitLockTile) {
			if (checkTreasures() > 0) {
				sound.playLockedDoor();
				throw new IllegalArgumentException("cannot move into a Exit lock Tile");
			}
			else {
				collect = true;
			}
			
			
		}
		else if (target instanceof InfoTile) {
			InfoTile info = (InfoTile) target;
			if (isChap) {
				
			}
		}

		sound.playMove();
		board[getTileX(t)][getTileY(t)] = t.getStandingOn();
		if (!collect) {
			t.setStandingOn(target);
		}
		else {
			t.setStandingOn(new EmptyTile());
		}
		
		
		setTile(t, x, y);

	}
	
	/*
	 * return count of tresures remaining instead of a boolean
	 */
	
	public int checkTreasures() {
		int count = 0;
		for (Tile t : this.getAllTiles()) {
			if (t instanceof TreasureTile)
				count++;
		}
		return count;

	}
	
	/*private void clearTileType(Tile t) {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				Tile current = board[x][y];
				if (current.getClass().equals(t.getClass())) {
					board[x][y] = new EmptyTile();
				}
			}
		}
	}*/

	public Tile getTileAt(int x, int y) {

		return board[x][y];

	}

	public void setTile(Tile t, int x, int y) {

		board[x][y] = t;

	}

	/*
	 * public Set<Key> availableKeys() {
	 * 
	 * return Collections.unmodifiableSet(availableKeys);
	 * 
	 * }
	 */

	public Tile[][] getBoard() {
		if (board == null) throw new IllegalStateException("board should not be null");
		return this.board;

	}

	public int getTileX(Tile t) {

		return findTile(t)[0];

	}

	public int getTileY(Tile t) {

		return findTile(t)[1];

	}

	private int[] findTile(Tile t) {

		for (int x = 0; x < board.length; x++) {

			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] != null) {
					if (board[x][y].equals(t)) {

						return new int[] { x, y };

					}
				}

			}

		}

		return null;

	}

	/*
	 * 
	 * Getter method to return a collection of all the tiles in this maze object
	 * 
	 */

	public Set<Tile> getAllTiles() {

		Set<Tile> tiles = new HashSet<Tile>();

		for (int x = 0; x < board.length; x++) {

			for (int y = 0; y < board[x].length; y++) {

				tiles.add(board[x][y]);

			}

		}

		return tiles;

	}

	/*
	 * return a list of chap and all enemies
	 */

	public List<Tile> getCharacters() {
		List<Tile> count = new ArrayList<Tile>();	
		for (Tile t : getAllTiles()) {
			if (t instanceof CharacterTile) count.add(t);
		}	
		return count;	
	}

	/*
	 * 
	 * toString() will display all tiles in the maze in their respective position
	 * 
	 * Chap = C
	 * EmptyTile = E
	 * Enemy = En
	 * ExitLock = El
	 * Info = I
	 * Key = K
	 * LockedDoor = D
	 * Treasure = T
	 * Wall = W
	 * 
	 */
	
	@Override
	public String toString() {

		String output = "";		

		for (int x = 0; x < board.length; x++) {
			output += "| ";
			for (int y = 0; y < board[x].length; y++) {

				output += board[x][y].toString() + " | ";

			}

			output += "\n";

		}

		return output;

	}

	
	public int[] getSize() {

		return new int[] {width, height};

	}

	
	public boolean equals(Maze other) {
		
		if (!isSameSize(other)) return false;

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
							
				if (!(board[x][y].getFileName().equals(other.getTileAt(x, y).getFileName()))) {
					return false;
				}
				
			}			
		}				

		return true;

	}

	private boolean isSameSize(Maze other) {
		return this.getSize()[0] == other.getSize()[0] 
			&& this.getSize()[1] == other.getSize()[1];
	}
	

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setChap(ChapTile c) {
		chap = c;
	}
	
	public ChapTile getChap() {
		for(Tile t: getAllTiles()) {
			if(t instanceof ChapTile) {return (ChapTile)t;}
		}
		return null;
	}
	
	public boolean hasWon() {
		return hasWon;
	}
	
	public InfoTile getInfo() {
		InfoTile output = null;
		for (Tile t : getAllTiles()) {
			if (t instanceof InfoTile) output = (InfoTile)t;
		}
		return output;
	}
	
	public void moveUp(CharacterTile t) {
		System.out.println(t);
		int x = getTileX(t);
		int y = getTileY(t);
		System.out.println("x="+x+"y="+y);
		Animate animate = new Animate(x,y,x,y-1, (ChapTile) t);
		animate.animation();
		moveTile(t, x, y-1);
	}
	
	public void moveLeft(CharacterTile t) {
		int x = getTileX(t);
		int y = getTileY(t);
		Animate animate = new Animate(x,y,x-1,y, (ChapTile) t);
		animate.animation();
		moveTile(t, x-1, y);			
	}
	public void moveDown(CharacterTile t) {
		int x = getTileX(t);
		int y = getTileY(t);
		Animate animate = new Animate(x,y,x,y+1, (ChapTile) t);
		animate.animation();
		moveTile(t, x, y+1);
	}
	public void moveRight(CharacterTile t) {
		int x = getTileX(t);
		int y = getTileY(t);
		Animate animate = new Animate(x,y,x+1,y, (ChapTile) t);
		animate.animation();
		moveTile(t, x+1, y);
	}


}
