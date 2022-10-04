package nz.ac.vuw.ecs.swen225.gp22.domain;


/** 
 * The Domain interface for all tiles within the game
 * 
 * */
public abstract class Tile{
	
	// Fields
	private int x;
	private int y;
	private int lastX;
	private int lastY;
	
	/**
	 * Construct a tile at given coordinates
	 * 
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * 
	 * */
	Tile(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	
	
}