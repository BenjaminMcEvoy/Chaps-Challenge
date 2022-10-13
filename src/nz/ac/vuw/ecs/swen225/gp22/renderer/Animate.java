package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

/** 
 * Responsible for the animation of entities/actors within
 * the game itself.
 * 
 * @author Benjamin McEvoy - 300579954
 * */
public class Animate{
	
	//Fields
	private int toX, toY, fromX, fromY;
	private ChapTile chap;
	private EnemyTile entity;
	private Tile ent;
	
	public Animate(int fromX, int fromY, int toX, int toY, Tile ent) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		if(ent instanceof ChapTile) {
			this.chap = (ChapTile) ent;
		} else if (ent instanceof EnemyTile) {
			this.entity = (EnemyTile) ent;
		}
		this.ent = ent;
	}


	public void animation(){
		if(toX == fromX-1){
			if(ent instanceof ChapTile) {
				chap.getFLeft();
			} else if (ent instanceof EnemyTile) {
				entity.getFLeft();
			}
		} else if(toX == fromX+1){
			if(ent instanceof ChapTile) {
				chap.getFRight();
			} else if (ent instanceof EnemyTile) {
				entity.getFRight();
			}
		} else if(toY == fromY-1){
			if(ent instanceof ChapTile) {
				chap.getFUp();
			} else if (ent instanceof EnemyTile) {
				return;
			}
		} else if(toY == fromY+1){
			if(ent instanceof ChapTile) {
				chap.getFDown();
			} else if (ent instanceof EnemyTile) {
				return;
			}
		} else{
			if(ent instanceof ChapTile) {
				chap.getFIdle();
			} else if (ent instanceof EnemyTile) {
				return;
			}
		}
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
