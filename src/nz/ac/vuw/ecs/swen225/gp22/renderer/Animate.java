package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;




public class Animate{
	
	//Fields
	private int toX, toY, fromX, fromY;
	private Maze.direction dir;
	
	public Animate(int fromX, int fromY, int toX, int toY, Maze.direction dir) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.dir = dir;
	}
	
	/** Gets the Direction
	 * 
	 * */
	public Maze.direction getDirection() {
		return this.dir;
	}
	
	/** Gets the fromX field
	 * 
	 * */
	public int getFromX() {
		return this.fromX;
	}
	
	/** Gets the fromY field
	 * 
	 * */
	public int getFromY() {
		return this.fromY;
	}
	
	/** Gets the toX field
	 * 
	 * */
	public int getToX() {
		return this.toX;
	}
	
	/** Gets the toY field
	 * 
	 * */
	public int getToY() {
		return this.toY;
	}
	
	/** Sets the fromX field
	 * 
	 * @param int fromX
	 * */
	public void setFromX(int fromX) {
		this.fromX = fromX;
	}

	/** Sets the fromY field
	 * 
	 * @param int fromY
	 * */
	public void setFromY(int fromY) {
		this.fromY = fromY;
	}

	/** Sets the toX field
	 * 
	 * @param int toX
	 * */
	public void setToX(int toX) {
		this.toX = toX;
	}

	/** Sets the toY field
	 * 
	 * @param int toY
	 * */
	public void setToY(int toY) {
		this.toY = toY;
	 }

	
}
