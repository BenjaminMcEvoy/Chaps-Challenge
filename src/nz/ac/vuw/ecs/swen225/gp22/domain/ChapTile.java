package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.*;

public class ChapTile extends CharacterTile{
	
	private Set<String> keys;
	private boolean alive;
	private String file = "chap";

	public ChapTile(int x, int y) {
		super(x, y, "chap");
		keys = new HashSet<String>();
		alive = true;
	}
	
	public void addKey(String k) {
		keys.add(k);
	}
	
	public boolean hasKey(String k) {
	    return keys.contains(k);
	}
	
	public void getFLeft() {
		file = "chap_left";
	}
	
	public void getFRight() {
		file = "chap_right";
	}
	
	public void getFUp() {
		file = "chap";
	}
	
	public void getFDown() {
		file = "chap";
	}
		
	@Override
	public void draw() {
		
		
	}
	
	@Override
	public String toString() {
		return "Chap";
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return file;
	}

}
