package nz.ac.vuw.ecs.swen225.gp22.domain;




public abstract class CharacterTile extends Tile{
	private Tile standingOn;
	private final String name;
	
	
	public CharacterTile(String name) {
		super();
		moveable = true;
		standingOn = new EmptyTile();
		this.name = name;
	}
	
	public void setStandingOn(Tile t) {
		standingOn = t;
	}
	
	public Tile getStandingOn() {
		return standingOn;
	}
	

	
	//getters
	public String getName() {
		return name;
	}


	@Override
	public abstract void draw();

	@Override
	public abstract String toString();
	@Override
	public abstract String getFileName();

}
