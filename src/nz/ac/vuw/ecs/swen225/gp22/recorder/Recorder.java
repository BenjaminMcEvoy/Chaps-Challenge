package nz.ac.vuw.ecs.swen225.gp22.recorder;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Animate.direction;
import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.XMLLoader;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;

import org.jdom2.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.*;

import java.util.List;
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
			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(file);
			
			//create level
			Element root = doc.getRootElement();
			String level = root.getAttributeValue("level");
			XMLLoader levelLoader = new XMLLoader();
			levelLoader.loadFile(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/" + level));
			
			//update characters future movement
			Maze maze = levelLoader.getMaze();
			List<Element> elements = root.getChildren();
			
			//go through each element
			for(Element e: elements) {
				//find the right character
				String name = e.getAttributeValue("name");
				for(CharacterTile c: maze.getCharacters()) {
					if(c.getName().equals(name)){
						String[] moves = e.getChild("moves").getText().split(", ");
						//push moves on to characters next moves stack
						for(String str: moves) {
							switch(str) {
								case "up":
									c.getNextMoves().push(direction.UP);
									break;
								case "left":
									c.getNextMoves().push(direction.LEFT);
									break;
								case "down":
									c.getNextMoves().push(direction.DOWN);
									break;
								case "right":
									c.getNextMoves().push(direction.RIGHT);
									break;
							}
						}
					}
				}
			}
			return maze;
		}catch(Exception e){throw e;}
	}
	
	//play next move 
	public void Next(Maze m) {
		auto = false;
		m.getCharacters().stream().forEach(i->i.nextMove());
	}
	
	//auto play through all moves
	public void AutoPlay(Maze m) throws InterruptedException {
		auto = true;
		while(!m.getCharacters().get(0).getNextMoves().isEmpty() && auto) {
			m.getCharacters().stream().forEach(i->i.nextMove());
			wait(2000/playbackSpeed);
		}
	}
}
