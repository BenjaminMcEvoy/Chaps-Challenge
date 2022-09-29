package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import java.util.Stack;

public class Recorder {
	static int playbackSpeed = 10;
	static File file;
	
	public void SaveGame(Map m, String newFileName) {
		if(file == null) {System.out.println("Nothing to be saved");}
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
					Character chara;
					for(Character c: m.getCharacters()) {
						if(c.getName().equals(name)) {chara = c;}
					}
					String text = "";
					Stack<direction> moves = new Stack<direction>(chara.getPrevMoves());
					if(!moves.isEmpty()) {text += moves.pop().toString();}
					while(!moves.isEmpty()) {text += ", " + moves.pop().toString();}
					Node move = current.getLastChild();
					move.setTextContent(text);
				}
			}
			FileOutputStream outputFile = new FileOutputStream(newFileName+"xml");
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer(new StreamSource(new File(newFileName+".xslt")));
			t.setOutputProperty(OutputKeys.INDENT, "yes");
	        t.setOutputProperty(OutputKeys.STANDALONE, "no");
	        DOMSource source = new DOMSource(newFile);
	        StreamResult result = new StreamResult(outputFile);
	        t.transform(source, result);
	        
		}catch(Exception e) {e.printStackTrace();}
	}
	
	
	public void Next(Map m) {
		m.charaters.stream().forEach(i->i.nextMove());
	}
	public void Back(Map m) {
		m.charaters.stream().forEach(i->i.nextMove());
	}
	public void AutoPlay(Map m) {
		while(true) {
			wait(1/playbackSpeed);
			next(m);
			if(m.get(0).getNextMoves().isEmpty()){break;}
		}
	}
}
