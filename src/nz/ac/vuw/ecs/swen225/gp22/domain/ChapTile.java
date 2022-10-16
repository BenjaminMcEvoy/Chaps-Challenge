package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.*;

/**
 * @author roddicadam - 300580773
 * 
 * Tile sublcass for chap
 *
 */



public class ChapTile extends CharacterTile{
	
	private ArrayList<KeyTile> keys;
	//private boolean alive;
	private String file = "chap";
	
	private Stack<Maze.direction> previousMoves = new Stack<Maze.direction>();
	private Stack<Maze.direction> nextMoves = new Stack<Maze.direction>();

	/**
	 * 
	 */
	public ChapTile() {
		super("chap");
		keys = new ArrayList<KeyTile>();
		//alive = true;
	}
	
	/**
	 * @param k
	 */
	public void addKey(KeyTile k) {
		keys.add(k);
	}
	
	/**
	 * @param k
	 */
	public void removeKey(KeyTile k) {
		keys.remove(k);
	}
	
	/**
	 * @param k
	 */
	public void removeKey(String k) {
		KeyTile remove = null;
		for (KeyTile key : keys) {
	    	if (key.getColor().equals(k)) remove = key;
	    }
		keys.remove(remove);
	}
	
	/**
	 * @param k
	 * @return if has a specific key
	 */
	public boolean hasKey(KeyTile k) {
	    for (KeyTile key : keys) {
	    	if (key.getColor().equals(k.getColor())) return true;
	    }
	    return false;
	}
	
	/**
	 * @return keys
	 */
	public ArrayList<KeyTile> getKeys() {
		return keys;
	}
	
	/**
	 * @param d
	 */
	public void addPreviousMove(Maze.direction d) {
		previousMoves.push(d);
	}
	
	/**
	 * @param d
	 */
	public void addNextMove(Maze.direction d) {
		nextMoves.push(d);
	}
	
	/**
	 * @return previousMoves
	 */
	public Stack<Maze.direction> getPreviousMoves() {
		return previousMoves;
	}
	
	/**
	 * @return nextMoves
	 */
	public Stack<Maze.direction> getNextMoves() {
		return nextMoves;
	}

	/**
	 * 
	 */
	public void getFLeft() {
		file = "chap_left";
	}
	
	/**
	 * 
	 */
	public void getFRight() {
		file = "chap_right";
	}
	
	/**
	 * 
	 */
	public void getFUp() {
		file = "chap_up";
	}
	
	/**
	 * 
	 */
	public void getFDown() {
		file = "chap_down";
	}

	/**
	 * 
	 */
	public void getFIdle() {
		file = "chap";
	}
	
	
	@Override
	public String toString() {
		return "Chap";
	}

	@Override
	public String getFileName() {
		return file;
	}

	@Override
    public void move(Maze m) {
        Maze.direction d = nextMoves.pop();
        m.setCurrent(this);
        switch(d) {
            case UP:
                m.update(null, Maze.direction.UP);
                break;
            case LEFT:
                m.update(null, Maze.direction.LEFT);
                break;
            case DOWN:
                m.update(null, Maze.direction.DOWN);
                break;
            case RIGHT:
                m.update(null, Maze.direction.RIGHT);
                break;
            default:
                break;
        }
        
    }
	

}
