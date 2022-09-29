package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.Stack;

public class Recorder {
	static int playbackSpeed = 10;
	static File file;
	
	public void SaveGame(Model m) {
		if(file == null) {System.out.println("Nothing to be saved");}
		try {
			InputStream oldFile = new FileInputStream(file);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document newFile = db.parse(oldFile);
			
			NodeList characters = newFile.getElementsByTagName("character");
			for(int i=0; i<characters.getLength(); i++) {
				Node current = characters.item(i);
				String name = current.getAttributes().getNamedItem("name").getTextContent();
			}
			
		}catch(Exception e) {}
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
