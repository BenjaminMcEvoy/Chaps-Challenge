package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.Observable;

public interface Observer {
	public void update(Observable a, Object o);
}
