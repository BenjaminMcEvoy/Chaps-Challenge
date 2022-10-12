package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.*;

import nz.ac.vuw.ecs.swen225.gp22.persistency.XMLLoader;

public class Maze {

	// fields

	// private Tile[][] board = new Tile[10][10];

	private Tile[][] board;
	// private Tile[][] board = new Tile[10][10];
	private ChapTile chap;

	private XMLLoader loader;

	private Set<Tile> entities = new HashSet<Tile>();

	private List<CharacterTile> characters = new ArrayList<CharacterTile>();

	// private int totalTreasureCount;

	private final int width, height;

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

	public Maze(int width, int height) {

		this.width = width;
		this.height = height;

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
				collect = true;
			}
		}

		else if (target instanceof LockedDoorTile) {
			LockedDoorTile door = (LockedDoorTile) target;
			if (isChap) {
				if (!((ChapTile) t).hasKey(new KeyTile(door.getColor()))) {
					throw new IllegalArgumentException("cannot move chap into a locked door tile.");
				}
				else {
					((ChapTile) t).removeKey(door.getColor());
				}
			}
		}

		else if (target instanceof TreasureTile) {
			
			if (isChap) {
				target = new EmptyTile();
				collect = true;
			}

		} 
		else if (target instanceof ExitLockTile) {
			ExitLockTile lock = (ExitLockTile) target;
			if (checkTreasures() && isChap) {
				target = new EmptyTile();
			}
			else {
				throw new IllegalArgumentException("cannot move chap into a exit locked tile.");
			}
		}
		else if (target instanceof InfoTile) {
			InfoTile info = (InfoTile) target;
			if (isChap) {
				
			}
		}

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
	
	private int checkTreasures() {
		int count = 0;
		for (Tile t : this.getAllTiles()) {
			if (t instanceof TreasureTile)
				count++;
		}
		return count;

	}

	public Tile getTileAt(int x, int y) {

		System.out.println(board[x][y].toString());
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
		// System.out.println(toString());
		return this.board == null ? new Tile[10][10] : this.board;

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

	public Set<Tile> getAllEntities() {

		return entities;

	}

	public List<CharacterTile> getCharacters() {

		return characters;

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
	
	public void setChap(ChapTile c) {
		chap = c;
	}
	
	public ChapTile getChap() {
		for(Tile t: getAllTiles()) {
			if(t instanceof ChapTile) {return (ChapTile)t;}
		}
		return null;
	}
	
	public void moveUp(CharacterTile t) {
		System.out.println(t);
		int x = getTileX(t);
		int y = getTileY(t);
		System.out.println("x="+x+"y="+y);
		moveTile(t, x, y-1);
	}
	public void moveLeft(CharacterTile t) {
		int x = getTileX(t);
		int y = getTileY(t);
		moveTile(t, x-1, y);			
	}
	public void moveDown(CharacterTile t) {
		int x = getTileX(t);
		int y = getTileY(t);
		moveTile(t, x, y+1);
	}
	public void moveRight(CharacterTile t) {
		int x = getTileX(t);
		int y = getTileY(t);
		moveTile(t, x+1, y);
	}

}
