package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.HashSet;
import java.util.Set;

public class ChapTile extends Tile{
	
	private Set<String> keys;
	private boolean alive;
	private Tile standingOn;

	ChapTile(int x, int y) {
		super(x, y);
		moveable = true;
		keys = new HashSet<String>();
		alive = true;
		standingOn = new EmptyTile(x, y);
	}
	
	public void addKey(String k) {
		keys.add(k);
	}
	
	public boolean hasKey(String k) {
	    return keys.contains(k);
	}
	
	public void setStandingOn(Tile t) {
		standingOn = t;
	}
	
	public Tile getStandingOn() {
		return standingOn;
	}
	
	
	@Override
	public void draw() {
		
		
	}

}
