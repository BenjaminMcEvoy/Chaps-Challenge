package nz.ac.vuw.ecs.swen225.gp22.domain;



public class WallTile extends Tile {

	public WallTile(int x, int y) {
		super(x, y);
		walkable = false;
	}

	

	public void draw() {}
	
	@Override
	public String toString() {
		return "wall";
	}



	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "wallTile";
	}
}
