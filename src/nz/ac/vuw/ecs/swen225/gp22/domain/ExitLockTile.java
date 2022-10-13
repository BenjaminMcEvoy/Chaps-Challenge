package nz.ac.vuw.ecs.swen225.gp22.domain;

public class ExitLockTile extends Tile{

	public ExitLockTile() {
		super();
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

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "exitLock";
	}

}
