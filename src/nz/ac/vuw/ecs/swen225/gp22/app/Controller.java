package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.recorder.Recorder;

public class Controller implements KeyListener{
	
	private final Set<Integer> pressedKeys = new HashSet<>();
	
	static boolean paused = false;
	static int up = KeyEvent.VK_UP;
	static int left = KeyEvent.VK_LEFT;
	static int down = KeyEvent.VK_DOWN;
	static int right = KeyEvent.VK_RIGHT;
	static int ctr = KeyEvent.VK_CONTROL;
	static int one = KeyEvent.VK_1;
	static int two = KeyEvent.VK_2;
	static int x = KeyEvent.VK_X;
	static int s = KeyEvent.VK_S;
	static int r = KeyEvent.VK_R;
	static int space = KeyEvent.VK_SPACE;
	static int esc = KeyEvent.VK_ESCAPE;

	Maze maze;
	ChapTile chap;
	
	public ArrayList<Integer> getPressedKeys(){
		return new ArrayList<Integer>(pressedKeys);
	}


	public Controller(Maze maze) {
		this.maze = maze;
		chap = maze.getChap();
	}

	public void keyPressed(KeyEvent e) { 
		
		pressedKeys.add(e.getExtendedKeyCode());
		
		if(pressedKeys.contains(ctr)) {
			if(e.getExtendedKeyCode() == one) {
				System.out.println("Load level 1");
				new Game(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/1.xml"));
			} 
			else if(e.getExtendedKeyCode() == two) {
				System.out.println("Load level 2");
				new Game(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/2.xml"));
			} 
			else if(e.getExtendedKeyCode() == x) {
				System.out.println("Exit lose progress");
				System.exit(0);
			} 
			else if(e.getExtendedKeyCode() == s) {
				System.out.println("Exit save game");
			} 
			else if(e.getExtendedKeyCode() == r) {
				System.out.println("Resume saved game");
				JFileChooser chooser = new JFileChooser(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/SavedGame"));
				int j = chooser.showOpenDialog(null);
				if(j == JFileChooser.APPROVE_OPTION) {
					try {
						File f = chooser.getSelectedFile();
						new Game(Recorder.LoadSave(f));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			
		} else {
			if(!paused) {
				if(e.getExtendedKeyCode() == up) {
					System.out.println("u");
					maze.moveUp(chap);
					chap.addPreviousMove(Maze.direction.UP);
				}
				else if(e.getExtendedKeyCode() == left) {
					System.out.println("l");
					maze.moveLeft(chap);
					chap.addPreviousMove(Maze.direction.LEFT);
				}
				else if(e.getExtendedKeyCode() == down) {
					System.out.println("d");
					maze.moveDown(chap);
					chap.addPreviousMove(Maze.direction.DOWN);
				}
				else if(e.getExtendedKeyCode() == right) {
					System.out.println("r");
					maze.moveRight(chap);
					chap.addPreviousMove(Maze.direction.RIGHT);
				}
				else if (e.getExtendedKeyCode() == space) {
					System.out.println("space");
					paused = true;
				}
			}
			else if(e.getExtendedKeyCode() == esc && paused) {
				System.out.println("esc");
				paused = false;
			}
			

		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		pressedKeys.remove(e.getExtendedKeyCode());
	}
}
