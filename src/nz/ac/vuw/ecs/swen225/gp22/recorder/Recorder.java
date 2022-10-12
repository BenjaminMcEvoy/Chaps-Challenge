package nz.ac.vuw.ecs.swen225.gp22.recorder;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Animate.direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XMLLoader;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import java.util.Stack;

public class Recorder {
	static boolean auto = false;
	static int playbackSpeed = 10;
	
	public void SaveGame(Maze maze, String newFileName) {
		if(maze == null) {System.out.println("Nothing to be saved");}
		try {
			InputStream oldFile = new FileInputStream(file);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document newFile = db.parse(oldFile);
			
			NodeList characters = newFile.getElementsByTagName("character");
			for(int i=0; i<characters.getLength(); i++) {
				Node current = characters.item(i);
				if (current.getNodeType() == Node.ELEMENT_NODE) {
					String name = current.getAttributes().getNamedItem("name").getTextContent();
					CharacterTile chara = null;
					for(CharacterTile c: maze.getCharacters()) {
						if(c.getName().equals(name)) {chara = c;}
					}
					String text = "";
					Stack<direction> moves = (Stack<direction>) chara.getPrevMoves().clone();
					if(!moves.isEmpty()) {text += moves.pop().toString();}
					while(!moves.isEmpty()) {text += ", " + moves.pop().toString();}
					Node move = current.getLastChild();
					move.setTextContent(text);
				}
			}
			
			//store new save file
			FileOutputStream outputFile = new FileOutputStream(newFileName+".xml");
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer(new StreamSource(new File(newFileName+".xslt")));
			t.setOutputProperty(OutputKeys.INDENT, "yes");
	        t.setOutputProperty(OutputKeys.STANDALONE, "no");
	        DOMSource source = new DOMSource(newFile);
	        StreamResult result = new StreamResult(outputFile);
	        t.transform(source, result);
	        
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public Maze LoadSave(File file) throws Exception {
		try {
			// load XML file document
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			
			//create level
			Element root = doc.getDocumentElement();
			String level = root.getAttribute("level");
			XMLLoader levelLoader = new XMLLoader();
			levelLoader.loadFile(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/" + level));
			
			//update characters possible movement
			Maze maze = levelLoader.getMaze();
			NodeList nodes = root.getChildNodes();
			root.
			for(int i=0; i<nodes.getLength(); i++) {
				String name = nodes.item(i).
				maze.getAllEntities().stream().forEach(n->{if(n.getFileName().equals(name)){addMove(n, moves);}};
			}
			return maze;
		}catch(Exception e){throw e;}
	}
	
	public void Next(Maze m) {
		auto = false;
		m.getCharacters().stream().forEach(i->i.nextMove());
	}
	public void Back(Maze m) {
		auto = false;
		m.getCharacters().stream().forEach(i->i.previousMove());
	}
	public void AutoPlay(Maze m) throws InterruptedException {
		auto = true;
		while(!m.getCharacters().get(0).getNextMoves().isEmpty() && auto) {
			m.getCharacters().stream().forEach(i->i.nextMove());
			wait(2000/playbackSpeed);
		}
	}
}
