package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

public class Controller implements KeyListener{
	static int up = KeyEvent.VK_UP;
	static int left = KeyEvent.VK_LEFT;
	static int down = KeyEvent.VK_DOWN;
	static int right = KeyEvent.VK_RIGHT;
	
	Maze maze;
	ChapTile chap;
	
	public Controller(Maze maze) {
		this.maze = maze;
		chap = maze.getChap();
		System.out.println(chap);
	}
	
	public void keyPressed(KeyEvent e) { 
		System.out.println(e);
		if(e.getExtendedKeyCode() == up) {
			System.out.println("u");
			maze.moveUp(chap);
		}
		else if(e.getExtendedKeyCode() == left) {
			System.out.println("l");
			maze.moveLeft(chap);
		}
		else if(e.getExtendedKeyCode() == down) {
			System.out.println("d");
			maze.moveDown(chap);
		}
		else if(e.getExtendedKeyCode() == right) {
			System.out.println("r");
			maze.moveRight(chap);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
