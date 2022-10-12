package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.*;

public class ChapTile extends CharacterTile{
	
	private ArrayList<KeyTile> keys;
	private boolean alive;

	public ChapTile() {
		super("chap");
		keys = new ArrayList<KeyTile>();
		alive = true;
	}
	
	public void addKey(KeyTile k) {
		keys.add(k);
	}
	
	public void removeKey(KeyTile k) {
		keys.remove(k);
	}
	
	public void removeKey(String k) {
		for (KeyTile key : keys) {
	    	if (key.getColor().equals(k)) keys.remove(key);
	    }
	}
	
	public boolean hasKey(KeyTile k) {
	    for (KeyTile key : keys) {
	    	if (key.getColor().equals(k.getColor())) return true;
	    }
	    return false;
	}
	
	public ArrayList<KeyTile> getKeys() {
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
