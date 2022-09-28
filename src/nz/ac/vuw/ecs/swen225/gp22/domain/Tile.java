package nz.ac.vuw.ecs.swen225.gp22.domain;


/**
 * The Domain interface for all tiles within the game
 *
 * */
public abstract class Tile{

	// Fields
	protected Maze maze;
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
	Tile(int x, int y){
		maze.setTile(this, x, y);
	}

	public void moveUp() {
		maze.moveTile(this, getX(),getY()-1);
	}

	public void moveDown() {
		maze.moveTile(this, getX(),getY()+1);
	}

	public void moveLeft() {
		maze.moveTile(this, getX()-1,getY());
	}

	public void moveRight() {
		maze.moveTile(this, getX()+1,getY());
	}
	
	
	//getters
	
	public Maze getMaze() {
		return maze;
	}
	
	public int getX() {
		return maze.getTileX(this);
	}
	
	public int getY() {
		return maze.getTileY(this);
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
		
	
	


}