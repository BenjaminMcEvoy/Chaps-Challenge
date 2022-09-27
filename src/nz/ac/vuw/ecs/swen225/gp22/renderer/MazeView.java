package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.*;

import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

/** 
 * Maze View class to update and render the maze whenever
 * the character moves or does an action, responsible for 
 * 
 * 
 * */
public class MazeView extends JPanel implements ActionListener{
	
	// Fields
	private int bWidth, bHeight, width, height, tSize, tView;
	private Maze maze;
	private Tile[][] board;
	
	MazeView(Maze m){
		init();
		maze = m;
		//width = m;
		//height = m.;
		
		/* Initialization of fields possibly
		 * set the parameters of the view
		 * possibly initialize the sound and animations too
		 * 
		 * */
	}
	
	private void init() {
		bWidth = 850;
		bHeight = 850;
		tSize = 50;
		tView = 5;
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
	public void renderTiles(Graphics graph) {
		for(int x = 0; x < height; x++) {
			for(int y = 0; y< width; y++) {
				Tile tile = maze.getTileAt(x, y);
				//graph.drawImage();// fill here
				
				// maybe check instance of tile or use use a tile precheck
				// to see if it contains any entities or objects at the start
				
				// if there is check all entities/containables
			}
			
		}
		
		}
	
	
	
	
	/**
	 *  Get the maze/board/pane
	 * @return maze
	 * 
	 * */
	public Maze getMaze() {
		return maze;
	}

	/**
	 *  Get the maze/board/pane width
	 * @return bWidth
	 * 
	 * */
	public int getbWidth() {
		return bWidth;
	}

	/**
	 *  Get the maze/board/pane height
	 * @return bHeight
	 * 
	 * */
	public int getbHeight() {
		return bHeight;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}