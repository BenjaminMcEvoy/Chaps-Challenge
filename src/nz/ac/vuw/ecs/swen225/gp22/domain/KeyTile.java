package nz.ac.vuw.ecs.swen225.gp22.domain;

public class KeyTile extends Tile{
	
	private String color;

	public KeyTile(int x, int y, String keyColor) {
		super(x, y);
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
		return "Key";
	}

}
