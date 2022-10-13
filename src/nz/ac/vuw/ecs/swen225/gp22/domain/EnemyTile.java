package nz.ac.vuw.ecs.swen225.gp22.domain;


import java.util.*;

import nz.ac.vuw.ecs.swen225.gp22.domain.Maze.direction;

public class EnemyTile extends CharacterTile{
	private List<direction> pattern = new ArrayList<direction>();
	int patternLocation = 0;
	private String file = "enemy_left";
	
	public EnemyTile(String name, List<direction> pattern) {
		super(name);
		this.pattern = pattern;
	}
	
	public void move(Maze maze) {
		switch(pattern.get(patternLocation)){
			case UP:
				maze.moveUp(this);
				break;
			case DOWN:
				maze.moveDown(this);
				break;
			case LEFT:
				maze.moveLeft(this);
				break;
			case RIGHT:
				maze.moveRight(this);
				break;
			
			default:
				break;
		}
		patternLocation++;
		if(patternLocation==pattern.size()) patternLocation = 0;
	}
	
	public void getFLeft() {
		// TODO Auto-generated method stub
		file = "enemy_left";
	}

	public void getFRight() {
		// TODO Auto-generated method stub
		file = "enemy_right";
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
		return file;
	}

}
