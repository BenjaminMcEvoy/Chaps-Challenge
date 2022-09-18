package nz.ac.vuw.ecs.swen225.gp22.domain;


/**
 * The Domain interface for all tiles within the game
 *
 * */
public abstract class Tile{

	// Fields
	private Location location;
	protected boolean walkable = true;


	/**
	 * Construct a tile at given coordinates
	 *
	 * @param x - x coordinate
	 * @param y - y coordinate
	 *
	 * */
	Tile(int x, int y){
		this.location = new Location(this,x,y);
	}

	public void moveUp() {
		location.move(location.getRow(), location.getColumn()-1);
	}

	public void moveDown() {
		location.move(location.getRow(), location.getColumn()+1);
	}

	public void moveLeft() {
		location.move(location.getRow()-1, location.getColumn());
	}

	public void moveRight() {
		location.move(location.getRow()+1, location.getColumn());
	}
	
	//will call this method to draw the tile
	
	public abstract void draw();





}