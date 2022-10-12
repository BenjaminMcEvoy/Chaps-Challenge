package nz.ac.vuw.ecs.swen225.gp22.persistency;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class XMLLoader {
	
	ChapTile chap;
	Maze maze; //2D array of Map
	
	public Maze getMaze() {
		return this.maze;
	}
	
	
	public void loadFile(File file) {
		try {
			
			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(file);
			Element rootNode = doc.getRootElement();
			int x = Integer.parseInt(rootNode.getAttributeValue("x"));
			int y = Integer.parseInt(rootNode.getAttributeValue("y"));
			this.maze = new Maze(x, y);
			parseChap(rootNode);
			parseBoard(rootNode);
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	private void parseChap(Element e) {
		int x = Integer.parseInt(e.getChild("character").getChild("location").getAttributeValue("xpos"));
		int y = Integer.parseInt(e.getChild("character").getChild("location").getAttributeValue("ypos"));
		this.chap = new ChapTile(x, y);
		maze.setTile(this.chap, x, y);
		
	}
	
	private void parseBoard(Element e) {
		boolean parsing = true;
		List<Element> tiles = e.getChildren();
		for (Element f:tiles) {
			//String tile = f.getChild("tile").getAttributeValue("class");
			//List<Element> currTiles = f.getChildren();
			if(f.equals(e.getChild("character"))) {continue;}
			Element currTile = f.getChild("location");
			int x = Integer.valueOf(currTile.getAttributeValue("xpos"));
			int y = Integer.valueOf(currTile.getAttributeValue("ypos"));
			if (f.getAttributeValue("class").equals("wall")) {
				maze.setTile(new WallTile(x, y), x, y);
			} else if(f.getAttributeValue("class").equals("free")) {
				maze.setTile(new EmptyTile(x, y), x, y);
			} else if (f.getAttributeValue("class").equals("door")) {
				String colour = f.getAttributeValue("colour");
				maze.setTile(new LockedDoorTile(x, y, colour), x, y);
			} else if (f.getAttributeValue("class").equals("key")) {
				String colour = f.getAttributeValue("colour");
				maze.setTile(new KeyTile(x, y, colour), x, y);
			}
		}
	}	
	
	
}



