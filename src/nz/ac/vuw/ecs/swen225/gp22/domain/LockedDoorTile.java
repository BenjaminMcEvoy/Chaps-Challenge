package nz.ac.vuw.ecs.swen225.gp22.domain;

public class LockedDoorTile extends Tile{
	
	private String color;

	public LockedDoorTile(String keyColor) {
		super();
		walkable = false;
		color = keyColor;
	}
	
	public String getColor() {
		return color;
	}

	
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "LockedDoor";
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "lockedDoorB";// + color;
	}
}
