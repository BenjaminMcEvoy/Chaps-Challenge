package nz.ac.vuw.ecs.swen225.gp22.fuzz;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Test;

import nz.ac.vuw.ecs.swen225.gp22.app.Game;

/*
 * @author Vedaanth Kannan 300482816
 */
public class FuzzVedaanth {
	static final Random r = new Random();
	private static Game game;
	private final List<Integer> direction = List.of(KeyEvent.VK_UP,
            KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN);
	 private final Map<Integer, Integer> directionsAndOpp =
	            Map.of(KeyEvent.VK_UP, KeyEvent.VK_DOWN,
	                    KeyEvent.VK_DOWN, KeyEvent.VK_UP,
	                    KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
	                    KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT);
	 
	 private List<Integer> movesGenerator(){// Move generator method that generates 10,000 random moves for patrick to do
		 int lastMove = -1;
		 List<Integer> actions = new ArrayList<>();
		 for (int i = 0; i < 10000; i++) {
			 while (true) {
	                int random = r.nextInt(direction.size());
	                int move = direction.get(random);
	                if (lastMove == -1 || move != directionsAndOpp.get(lastMove)) {
	                    actions.add(move);
	                    lastMove = move;
	                    break;
	                }
			 }
		 }
		 return actions;
	 }
	 private void test() {
	        try {
	            Robot robot = new Robot();
	            List<Integer> generatedMoves = movesGenerator();
	            for (int key : generatedMoves) {
	                SwingUtilities.invokeLater(new Runnable() {
	                    public void run() {
	                        robot.keyPress(key);
	                    }
	                });
	                try {
	                    Thread.sleep(10);
	                    SwingUtilities.invokeLater(new Runnable() {
	                        public void run() {
	                            robot.keyRelease(key);
	                        }
	                    });
	                } catch (InterruptedException e) {
	                }
	            }

	        } catch (AWTException e) {
	            e.printStackTrace();
	        }
	    }
		/**
	 * level 1 test method
	 */
	 public void level1Test() {
	        try {
	            SwingUtilities.invokeLater(() -> {game = new Game(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/1.xml"));});
	        } catch (Error e) {
	        }
	        test();
	    }
		/**
	 * level 2 test method
	 */
	 public void level2Test() {
		 try {
	            SwingUtilities.invokeLater(() -> {game = new Game(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/2.xml"));});
	        } catch (Error e) {
	        }
	        test();
	 }
	 
	  /**
	     * Actual fuzz test
	     */
	    @Test
	    public void timedTest() {
	        try {
	            assertTimeoutPreemptively(Duration.ofSeconds(60), () -> level1Test());//AssertTimeout didn't stop the test at 60 seconds so used AssertTimeoutPreemptively to force quit the test to preemptively abort the test
	            assertTimeoutPreemptively(Duration.ofSeconds(60), () -> level2Test());
	        } catch (Exception e) {

	        }
	    
	    }
}

