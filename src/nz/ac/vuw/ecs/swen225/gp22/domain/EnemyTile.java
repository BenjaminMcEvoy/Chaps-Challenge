package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.*;

public class EnemyTile extends CharacterTile{
	List<Maze.direction> pattern = new ArrayList<Maze.direction>();
	int patternLocation = 0;
	
	public EnemyTile(int x, int y, String name, List<Maze.direction> pattern) {
		super(x, y, name);
		this.pattern = pattern;
	}
	
	@Override
	public void nextMove() {
		patternLocation++;
		if(patternLocation==pattern.size()) {patternLocation=0;}
		super.nextMove();
	}
	
	@Override
	public void previousMove() {
		patternLocation--;
		if(patternLocation<0) {patternLocation=pattern.size()-1;}
		super.previousMove();
	}
	
	@Override
	public void draw() {
		
		
	}
	
	@Override
	public String toString() {
		return "Enemy";
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "";
	}

}
