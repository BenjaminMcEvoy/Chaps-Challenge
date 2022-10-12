package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.*;

public class ChapTile extends CharacterTile{
	
	private Set<String> keys;
	private boolean alive;

	public ChapTile() {
		super("chap");
		keys = new HashSet<String>();
		alive = true;
	}
	
	public void addKey(String k) {
		keys.add(k);
	}
	
	public boolean hasKey(String k) {
	    return keys.contains(k);
	}
	
	public Set<String> getKeys() {
		return keys;
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
		return "chap";
	}
	

}
