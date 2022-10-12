package nz.ac.vuw.ecs.swen225.gp22.domain;


/**
 * The Domain interface for all tiles within the game
 *
 * */
public abstract class Tile{

	// Fields
	//private Location location;
	//private int x, y;
	protected boolean walkable = true;
	protected boolean moveable = false;


	/**
	 * Construct a tile at given coordinates
	 *
	 * @param x - x coordinate
	 * @param y - y coordinate
	 *
	 * */
	public Tile(){

	}

	public boolean isWalkable() {
		return walkable;
	}
	
	public boolean isMoveable() {
		return moveable;
	}
	
	//will call this method to draw the tile
	
	public abstract void draw();

	public abstract String toString();
		
	public abstract String getFileName();


}
