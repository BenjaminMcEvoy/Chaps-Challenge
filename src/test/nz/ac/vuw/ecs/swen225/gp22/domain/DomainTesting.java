package test.nz.ac.vuw.ecs.swen225.gp22.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import nz.ac.vuw.ecs.swen225.gp22.domain.ChapTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.EmptyTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.ExitLockTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.ExitTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.InfoTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.KeyTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.LockedDoorTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp22.domain.TreasureTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.WallTile;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Animate.direction;

class DomainTesting {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/*
	@Test
	public void test() {
		
		
		
		fail("Not yet implemented");
	}
	*/
	@Test
	public void testEquals1() {
		
		Maze maze = new Maze(2, 2, 1);
		fillMaze(maze);
		
		
		
		if (!maze.equals(maze)) {
			fail("maze should equal itself");
		}
	}
	
	@Test
	public void testEquals2() {
		
		Maze maze1 = new Maze(2, 2);
		Maze maze2 = new Maze(2, 2);
		
		fillMaze(maze1);
		fillMaze(maze2);
		
		if (!maze1.equals(maze2)) {
			fail("maze should equal itself");
		}
		
	}
	
	@Test
	public void testChapMovement1() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		maze.setTile(chap, 0, 0);
		maze.setChap(chap);
		chap.setStandingOn(new EmptyTile());
		//maze.setTile(new TreasureTile(), 0, 1);
		maze.moveTile(chap, 0, 1);
		maze.moveTile(chap, 1, 1);
		
		System.out.println(maze.toString());
		if (!classesEqual(maze.getTileAt(0, 1),new EmptyTile())) {
			fail("Chap should be at location 1,1");
		}
		
	}
	
	@Test
	public void testChapMovement2() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		maze.setTile(chap, 0, 0);
		maze.setTile(new KeyTile("red"), 0, 1);
		maze.moveTile(chap, 0, 1);
		maze.moveTile(chap, 1, 1);
		//System.out.println(maze.toString());
		if (!classesEqual(maze.getTileAt(0, 1),new EmptyTile())) {
			fail("Chap should have picked up key");
		}
	}
	
	@Test
	public void testChapMovement3() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		maze.setTile(chap, 0, 0);
		maze.setTile(new TreasureTile(), 0, 1);
		maze.moveTile(chap, 0, 1);
		maze.moveTile(chap, 1, 1);
		//System.out.println(maze.toString());
		if (!(maze.getTileAt(0, 1).getClass().equals(new EmptyTile().getClass()))) {
			fail("Chap should have picked up treasure");
		}
	}
	
	
	
	@Test
	public void testChapMovement4() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		maze.setTile(chap, 0, 0);
		maze.setTile(new WallTile(), 1, 0);

		try {
			maze.moveTile(chap, 1, 0);
			System.out.println(maze.toString());
			fail("IllegalArgumentException should be thrown when chap moved into wall");
		} 
		catch (IllegalArgumentException excpectedException) {}
	}
	
	@Test
	public void testChapMovement5() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		maze.setTile(chap, 0, 0);
		maze.setTile(new LockedDoorTile("red"), 1, 0);

		try {
			maze.moveTile(chap, 1, 0);
			
			fail("IllegalStateException should be thrown when chap moved into ExitLockTile without key");
		} 
		catch (IllegalStateException excpectedException) {}
	}
	
	@Test
	public void testChapMovement6() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		KeyTile key = new KeyTile("red");
		maze.setTile(chap, 0, 0);
		maze.setTile(new LockedDoorTile("red"), 1, 2);
		maze.setTile(key, 1, 0);
		
		maze.moveTile(chap, 1, 0);
		maze.moveTile(chap, 1, 1);
		maze.moveTile(chap, 1, 2);
		
		
		if (!(maze.getBoard()[1][2].equals(chap))) {
			fail("chap should be standing where the locked door was previously");
		}
	}
	@Test
	public void testChapMovement7() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		
		maze.setTile(chap, 0, 0);
		maze.setTile(new ExitLockTile(), 2, 2);
		maze.setTile(new TreasureTile(), 1, 0);
		
		maze.moveTile(chap, 1, 0);
		maze.moveTile(chap, 1, 1);
		maze.moveTile(chap, 1, 2);
		maze.moveTile(chap, 2, 2);
		
		if (!(maze.getBoard()[2][2].equals(chap))) {
			fail("chap should be standing where the ExitLock was previously");
		}
		
	}
	
	@Test
	public void testChapMovement8() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		
		maze.setTile(chap, 0, 0);
		maze.setTile(new ExitTile(), 2, 2);
		
		maze.moveTile(chap, 1, 0);
		maze.moveTile(chap, 1, 1);
		maze.moveTile(chap, 1, 2);
		maze.moveTile(chap, 2, 2);
		
		if(!maze.hasWon()) {
			fail("game should be won");
		}
		
		
		
	}
	@Test
	public void testChapMovement9() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		InfoTile info = new InfoTile("test");
		maze.setTile(chap, 0, 0);
		maze.setTile(info, 2, 2);
		
		maze.moveTile(chap, 1, 0);
		maze.moveTile(chap, 1, 1);
		maze.moveTile(chap, 1, 2);
		maze.moveTile(chap, 2, 2);
		
		if(!info.isDiplaying()) {
			fail("InfoTile should be displaying its text");
		}		
	}
	
	@Test
	public void testChapMovement10() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		InfoTile info = new InfoTile("test");
		maze.setTile(chap, 0, 0);
		maze.setTile(info, 1, 1);
		
		maze.moveTile(chap, 1, 0);
		maze.moveTile(chap, 1, 1);
		maze.moveTile(chap, 1, 2);
		maze.moveTile(chap, 2, 2);
		
		if(info.isDiplaying()) {
			fail("InfoTile should no longer displaying its text");
		}
	}
	@Test
	public void testChapMovement11() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		maze.setChap(chap);
		maze.setTile(chap, 1, 1);
		maze.moveUp(chap);
		maze.moveDown(chap);
		maze.moveLeft(chap);
		maze.moveRight(chap);
	}
	
	
	
	/*@Test
	public void testEnemyMovement1() {
		Maze maze = new Maze(3, 3, 1);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		ArrayList<direction> directions = new ArrayList<direction>();
		directions.add(direction.RIGHT);
		directions.add(direction.DOWN);
		directions.add(direction.LEFT);
		directions.add(direction.UP)
		EnemyTile enemy = new EnemyTile("enemy1", directions);
		maze.setTile(enemy, 0, 0);
		maze.setTile(new KeyTile("red"), 0, 1);
		maze.moveTile(enemy, 0, 1);
		maze.moveTile(enemy, 1, 1);

		
		System.out.println(maze.toString());
		if (!(maze.getTileAt(1, 1).getClass().equals(new EnemyTile("", directions).getClass()))) {
			fail();
		}
		
	}
	*/
	
	
	/*
	 * Fills a maze with empty tiles so none of tiles are null
	 */
	
	
	private void fillMaze(Maze m) {
		for (int x = 0; x < m.getWidth(); x++) {
			for (int y = 0; y < m.getHeight(); y++) {
				m.setTile(new EmptyTile(), x, y);
			}
		}
	}
	
	private boolean classesEqual(Tile first, Tile second) {
		return first.getClass().equals(second.getClass());
	}

}
