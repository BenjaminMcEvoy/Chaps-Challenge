package nz.ac.vuw.ecs.swen225.gp22.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Animate.direction;

class DomainTesting {

	/*
	@Test
	public void test() {
		
		
		
		fail("Not yet implemented");
	}
	*/
	@Test
	public void testEquals1() {
		
		Maze maze = new Maze(2, 2);
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
		Maze maze = new Maze(2, 2);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		maze.setTile(chap, 0, 0);
		maze.setChap(chap);
		chap.setStandingOn(new EmptyTile());
		maze.setTile(new TreasureTile(), 0, 1);
		maze.moveTile(chap, 0, 1);
		maze.moveTile(chap, 1, 1);
		
		System.out.println(maze.toString());
		if (!(maze.getTileAt(1, 1).getClass().equals(new ChapTile().getClass()))) {
			fail();
		}
		
	}
	
	@Test
	public void testEnemyMovement1() {
		Maze maze = new Maze(3, 3);
		fillMaze(maze);
		ChapTile chap = new ChapTile();
		ArrayList<direction> directions = new ArrayList<direction>();
		directions.add(direction.RIGHT);
		directions.add(direction.DOWN);
		directions.add(direction.LEFT);
		directions.add(direction.UP);
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

}
