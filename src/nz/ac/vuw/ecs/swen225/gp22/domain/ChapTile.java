package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.*;

public class ChapTile extends CharacterTile{
	
	private ArrayList<String> keys;
	private boolean alive;

	public ChapTile() {
		super("chap");
		keys = new ArrayList<String>();
		alive = true;
	}
	
	public void addKey(String k) {
		keys.add(k);
	}
	
	public void removeKey(String k) {
		keys.remove(k);
	}
	
	public boolean hasKey(String k) {
	    return keys.contains(k);
	}
	
	public ArrayList<String> getKeys() {
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
