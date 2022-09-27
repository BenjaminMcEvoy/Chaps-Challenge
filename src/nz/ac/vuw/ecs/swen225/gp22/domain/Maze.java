package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.HashSet;
import java.util.Set;

public class Maze {
	
	//fields
	private Tile[][] board = new Tile[10][10];
	private ChapTile chap;
	//private Set<Key> availableKeys = new HashSet<Key>();
	
	/*
	 * Maze constructor will be the class in the domain which stores the states and locations
	 * for each tile in the current level, will take some kind of persistency class as the parameter
	 * which would contain the data for all the tiles, including their locations.
	 * 
	 */
	
	Maze(/*Persistency p*/) {
		/*
		chap = p.getChap(); 
		 
		 
		  
		 */
	}
	
	/*
	 * method to check if something movable can move to a specific tile and if so move it
	 */
	
	public void moveTile(Tile t, int x, int y) {
		
		Tile target = board[x][y];
		//boolean allowMove = 
		
		if (target != null) {
			if (target instanceof WallTile) {
				return;
			}
			else if (target instanceof KeyTile) {
				
				chap.addKey(((KeyTile) target).getColor());
			}
			else if (target instanceof LockedDoorTile) {
				LockedDoorTile door = (LockedDoorTile) target;
				if (!chap.hasKey(door.getColor())) {
					return;
				}
			}
			
			//all Tile subclasses will added to later
		}
			
		board[t.getX()][t.getY()] = null;
		board[x][y] = t;
		
	}
	
	
	
	
	
	public Tile getTileAt(int x, int y) {
		return board[x][y];
	}
	
	public void setTile(Tile t, int x, int y) {
		board[x][y] = t;
	}
	
	/*public Set<Key> availableKeys() {
		return Collections.unmodifiableSet(availableKeys);
	}*/
	
	
	
	
	
	public int getTileX(Tile t) {
		return findTile(t)[0];
	}
	
	public int getTileY(Tile t) {
		return findTile(t)[1];
	}
	
	private int[] findTile(Tile t) {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y].equals(t)) {
					return new int[]{x, y};
				}
			}
		}
		return null;
	}
	
	
	
	/*
	 * Getter method to return a collection of all the tiles in this maze object
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

}
