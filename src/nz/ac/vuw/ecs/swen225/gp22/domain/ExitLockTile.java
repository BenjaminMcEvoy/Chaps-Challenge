package nz.ac.vuw.ecs.swen225.gp22.domain;

public class ExitLockTile extends Tile{

	ExitLockTile(int x, int y) {
		super(x, y);
		walkable = false;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "ExitLock";
	}

}
