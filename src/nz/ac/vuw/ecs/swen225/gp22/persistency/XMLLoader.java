import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;

public class XMLLoader {
	
	ChapTile chap;
	Maze maze; //2D array of Map
	
	public static void main(String[] args) {
		
		public void loadFile(File file) {
			try {
				this.maze = new Maze(this);
				SAXBuilder sax = new SAXBuilder();
				Document doc = new sax.build(file);
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
					map.setTile(new WallTile(x, y), x, y);
				} else if(currTile.toString().equals("free")) {
					map.setTile(null, x, y);
				} else if (currTile.toString().equals("door")) {
					string colour = currTile.getChildText("colour");
					map.setTile(new LockedDoorTile(x, y, colour), x, y);
				} else if (currTile.toString().equals("key")) {
					string colour = currTile.getChildText("colour");
					map.setTile(new KeyTile(x, y, colour), x, y);
				}
			}
		}
	}
	
}
