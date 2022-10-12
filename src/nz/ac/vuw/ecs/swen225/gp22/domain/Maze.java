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
	
	public enum direction{
		UP,
		DOWN,
		LEFT,
		RIGHT
	}

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

	public void moveTile(Tile t, int x, int y, direction d) {
		if (!(t instanceof CharacterTile)) {return;}
		Tile target = board[x][y];
		boolean collect = false;

		if (target != null) {
			if (target instanceof WallTile) {
				throw new IllegalArgumentException("cannot move chap into a wall tile.");
			}
			else if (target instanceof KeyTile) {
				//chap.addKey(((KeyTile) target).getColor());
				collect = true;
			}
			else if (target instanceof LockedDoorTile) {
				LockedDoorTile door = (LockedDoorTile) target;
				//if (!chap.hasKey(door.getColor())) {
				//	throw new IllegalArgumentException("cannot move chap into a locked door tile.");
				//}
			}
			else if (target instanceof TreasureTile) {
				// TreasureTile treasure = (TreasureTile) target;
				// target = null;
				collect = true;
			} else if (target instanceof ExitLockTile) {
				ExitLockTile lock = (ExitLockTile) target;
				if (checkTreasures()) {
					target = null;
				}
				else {
					throw new IllegalArgumentException("cannot move chap into a exit locked tile.");
				}
			}
		}
		//board[getTileX(chap)][getTileY(chap)] = chap.getStandingOn();
		//if (!collect)
			//chap.setStandingOn(target);
		if(t instanceof ChapTile) {
			if(d == direction.UP) {
				((ChapTile) t).getFUp();
			} else if(d == direction.LEFT) {
				((ChapTile) t).getFLeft();
			} else if(d == direction.DOWN) {
				((ChapTile) t).getFDown();
			} else if(d == direction.RIGHT) {
				((ChapTile) t).getFRight();
			}
		}
	
		board[getTileX(t)][getTileY(t)] = board[x][y];
		board[x][y] = t;
	}
	private boolean checkTreasures() {
		for (Tile t : this.getAllTiles()) {
			if (t instanceof TreasureTile)
				return false;
		}
		return true;
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
		System.out.println(toString());
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

	@Override

	public String toString() {
		String output = "";
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] != null) {
					output += board[x][y].toString() + " ";
				} else {
					output += 'n';
				}
			}
			output += "\n";
		}
		return output;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
	
	public ChapTile getChap() {
		for(Tile t: getAllTiles()) {
			if(t instanceof ChapTile) {return (ChapTile)t;}
		}return null;
	}
	//move
	public void moveUp(CharacterTile t) {
		System.out.println(t);
		int x = getTileX(t);
		int y = getTileY(t);
		System.out.println("x="+x+"y="+y);
		moveTile(t, x, y-1, direction.UP);
	}
	public void moveLeft(CharacterTile t) {
		int x = getTileX(t);
		int y = getTileY(t);
		moveTile(t, x-1, y, direction.LEFT);			
	}
	public void moveDown(CharacterTile t) {
		int x = getTileX(t);
		int y = getTileY(t);
		moveTile(t, x, y+1, direction.DOWN);
	}
	public void moveRight(CharacterTile t) {
		int x = getTileX(t);
		int y = getTileY(t);
		moveTile(t, x+1, y, direction.RIGHT);
	}

}
