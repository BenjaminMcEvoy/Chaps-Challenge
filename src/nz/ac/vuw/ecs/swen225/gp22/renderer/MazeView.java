package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.swing.JPanel;

/** 
 * Maze View class to update and render the maze whenever
 * the character moves or does an action, responsible for 
 * 
 * 
 * */
@SuppressWarnings("serial")
public class MazeView extends JPanel{
	
	MazeView(){
		
		/* Initialization of fields possibly
		 * set the parameters of the view
		 * possibly initialize the sound and animations too
		 * 
		 * */
	}
	
	/**
	 * Draws the main character with coordinates recorded by
	 * the character itself
	 * 
	 * 
	 * */
	public void renderChap() {return;}
	
	/**
	 * Draws the mobs with coordinates recorded by
	 * the maze/board/mob itself
	 * 
	 * 
	 * */
	public void renderEnemies() {return;}
	
	/**
	 * Draws the tiles with coordinates recorded by
	 * the maze/board itself
	 * 
	 * 
	 * */
	public void renderTiles() {return;}
	
	
	
	
	/**
	 *  Get the maze/board/pane
	 * 
	 * */
	public void getMaze() {
		return;
	}
		
}