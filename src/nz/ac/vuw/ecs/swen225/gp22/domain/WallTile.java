package nz.ac.vuw.ecs.swen225.gp22.domain;



public class WallTile extends Tile {

	WallTile(int x, int y) {
		super(x, y);
		walkable = false;
	}

	@Override
	public String toString() {
		return "wall";
	}

	public void draw() {}
}
