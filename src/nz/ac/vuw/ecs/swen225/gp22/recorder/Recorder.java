package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;

public class Recorder {
	static int playbackSpeed = 10;
	static File file;
	
	public void SaveGame(Model m) {
		if(file == null) {System.out.println("Nothing to be saved");}
		try {
			BufferedReader oldFile = new BufferedReader(new FileReader(file));
			StringBuffer newFile = new StringBuffer();
			String line;
			while((line = oldFile.readLine()) != null) {
				if(line.contains("Character")) {
					//override any previously stored moves
					String moves = String.join(", ", getCharacter(m, line).getPrevMoves());
					
				}else {
					newFile.append(line+"\n");
				}
			}
			
		}catch(Exception e) {}
	}
	public void LoadGame() {
		
	}
	public void Next(Model m) {
		m.charaters.stream().forEach(i->i.nextMove());
	}
	public void Back(Model m) {
		m.charaters.stream().forEach(i->i.nextMove());
	}
	public void AutoPlay(Model m) {
		while(true) {
			wait(1/playbackSpeed);
			next(m);
			if(m.get(0).getNextMoves().isEmpty()){break;}
		}
	}
	
	public character getCharacter(Model m, String profile) {
		//String name = 
		m.getCharacters().stream().forEach(i->{if(i.getName().equals(name)) {return i;}});
	}
}
