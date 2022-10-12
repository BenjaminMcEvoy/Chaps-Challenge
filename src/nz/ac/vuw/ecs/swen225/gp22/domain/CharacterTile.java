package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Animate;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import java.util.Stack;

public abstract class CharacterTile extends Tile{
	private Tile standingOn;
	private final String name;
	
	//records moves
	Stack<Maze.direction> prevMoves = new Stack<Maze.direction>();
	Stack<Maze.direction> nextMoves = new Stack<Maze.direction>();
	
	public CharacterTile(int x, int y, String name) {
		super(x, y);
		moveable = true;
		standingOn = new EmptyTile(x, y);
		this.name = name;
	}
	
	public void setStandingOn(Tile t) {
		standingOn = t;
	}
	
	public Tile getStandingOn() {
		return standingOn;
	}
	
	//perform the next saved move
	public void nextMove() {
		//perform move from nextMoves.peek()
		prevMoves.push(nextMoves.pop());
	}
	
	public void previousMove() {
		//perform move from prevMoves.peek()
		nextMoves.push(prevMoves.pop());
	}
	
	//getters
	public String getName() {
		return name;
	}
	
	public Stack<Maze.direction> getPrevMoves(){
		return prevMoves;
	}
	
	public Stack<Maze.direction> getNextMoves(){
		return nextMoves;
	}

	@Override
	public abstract void draw();

	@Override
	public abstract String toString();
	@Override
	public abstract String getFileName();

}
