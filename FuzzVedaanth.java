package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import FuzzTest.PathCell;

/*
 * @author Vedaanth Kannan 300482816
 */
public class FuzzVedaanth {
	public static GUI gui;
	public static Path maze[][];
	
	public void testLvl1() {
		gui = new GUI(FileChooser.openLevelFile("Level1.xml"));
		int length = gui.getMazePlaceholder().getBoardPlaceholder().length;
		maze = new Path[length][length];
		for (int x = 3; x < 12; x++) {
			for (int y = 3; y < 12; y++) {
				Tile tile = gui.getMazePlaceholder().getTilePlaceholder(x,y);
				maze[x][y] = new Path(tile.getLocationPlaceholder());
			}
		}
		Location chap = gui.getMazePlaceholder().getChapPlaceholder().getLocationPlaceholder();
		generateGraph(maze[chap.getRowPlaceholder()][chap.getColPlaceholder()]);
		reset();
		app.setIsTesting();
	}
}

