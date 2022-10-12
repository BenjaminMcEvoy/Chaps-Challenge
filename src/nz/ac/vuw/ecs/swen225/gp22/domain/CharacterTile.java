package nz.ac.vuw.ecs.swen225.gp22.domain;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Animate.direction;

import java.util.Stack;

public abstract class CharacterTile extends Tile{
	private Tile standingOn;
	private final String name;
	
	//records moves
	Stack<direction> prevMoves = new Stack<direction>();
	Stack<direction> nextMoves = new Stack<direction>();
	
	public CharacterTile(String name) {
		super();
		moveable = true;
		standingOn = new EmptyTile();
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
	
	public Stack<direction> getPrevMoves(){
		return prevMoves;
	}
	
	public Stack<direction> getNextMoves(){
		return nextMoves;
	}

	@Override
	public abstract void draw();

	@Override
	public abstract String toString();
	@Override
	public abstract String getFileName();

}
