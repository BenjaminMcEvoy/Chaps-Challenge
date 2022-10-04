package nz.ac.vuw.ecs.swen225.gp22.domain;

public class InfoTile extends Tile {
	
	private String text;
	private boolean displayText;

	InfoTile(int x, int y, String t) {
		super(x, y);
		text = t;
		
	}

	public String getText() {
		return text;
	}
	
	public void changeDisplay(boolean b) {
		displayText = b;
	}
	
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		if (displayText) {
			
		}
		else {
			
		}
	}
	
	@Override
	public String toString() {
		return "Info";
	}

}
