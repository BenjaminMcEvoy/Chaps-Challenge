package nz.ac.vuw.ecs.swen225.gp22.domain;


import java.util.*;

import nz.ac.vuw.ecs.swen225.gp22.domain.Maze.direction;

public class EnemyTile extends CharacterTile{
	List<direction> pattern = new ArrayList<direction>();
	int patternLocation = 0;
	
	public EnemyTile(String name, List<direction> pattern) {
		super(name);
		this.pattern = pattern;
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
