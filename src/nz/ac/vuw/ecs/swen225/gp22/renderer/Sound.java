package nz.ac.vuw.ecs.swen225.gp22.renderer;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import java.io.*;
import javax.sound.sampled.*;

public interface Sound<T>{
	
		public void load(T t);
		
		public void play(T t);
		
}

class MobSound implements Sound<Tile>{
	
	Clip sound;


	@Override
	public void load(Tile t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(Tile t) {
		// TODO Auto-generated method stub
		if(t instanceof ChapTile) {
			if(t.isWalkable()) {
				// play walk sound
			} else{
				// play i cant go there maybe? or remove and not worry about it
				// depends if find a royalty free source
			}
		}
		
	}
	
}

class AmbientSound implements Sound<Tile>{
	

	@Override
	public void load(Tile t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(Tile t) {
		// TODO Auto-generated method stub
		if(t instanceof KeyTile) {
			// play key sound
		} else if(t instanceof LockedDoorTile) {
			if(!t.isWalkable()) {
				// play locked sound
			}
		}
		
	}
	
}
