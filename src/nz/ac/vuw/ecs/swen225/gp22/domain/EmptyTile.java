package nz.ac.vuw.ecs.swen225.gp22.domain;

public class EmptyTile extends Tile{

	public EmptyTile() {
		super();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "Empty";
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "freeTile";
	}

}
