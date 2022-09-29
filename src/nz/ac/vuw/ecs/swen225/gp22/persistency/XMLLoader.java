package nz.ac.vuw.ecs.swen225.gp22.persistency;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import java.io.File;
import java.util.List;

public class XMLLoader {
	
	ChapTile chap;
	Maze maze; //2D array of Map
	
	public Maze getMaze() {
		return this.maze;
	}
	
	
	public void loadFile(File file) {
		try {
			this.maze = new Maze(this);
			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(file);
			Element rootNode = doc.getRootElement();
			parseChap(rootNode);
			parseBoard(rootNode);
		}
	}
	
	private void parseChap(Element e) {
		int x = Integer.parseInt(e.getChild("player").getChild("location").getChildText("xpos"));
		int y = Integer.parseInt(e.getChild("player").getChild("location").getChildText("ypos"));
		this.chap = new ChapTile(x, y);
		map.setTile(this.chap, x, y);
		
	}
	
	private void parseBoard(Element e) {
		boolean parsing = true;
		List<Element> tiles = e.getChildren();
		for (Element f:tiles) {
			Element currTile = f.getChild("tile").getChildText("class");
			int x = currTile.getChildText("xpos");
			int y = currTile.getChildText("ypos");
			if (currTile.toString().equals("wall")) {
				maze.setTile(new WallTile(x, y), x, y);
			} else if(currTile.toString().equals("free")) {
				maze.setTile(null, x, y);
			} else if (currTile.toString().equals("door")) {
				String colour = currTile.getChildText("colour");
				maze.setTile(new LockedDoorTile(x, y, colour), x, y);
			} else if (currTile.toString().equals("key")) {
				String colour = currTile.getChildText("colour");
				maze.setTile(new KeyTile(x, y, colour), x, y);
			}
		}
	}	
	
	
}

