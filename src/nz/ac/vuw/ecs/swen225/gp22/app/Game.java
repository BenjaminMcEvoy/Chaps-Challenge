package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.persistency.*;
import nz.ac.vuw.ecs.swen225.gp22.renderer.MazeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Main class for running game
 * @author CarloC
 *
 */
public class Game extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Runnable stop = ()->{};
	private MazeView mv;
	private JPanel panel;
	private Controller controller;

	/**
	 * Constructor for a new blank level
	 */
	public Game() {
		assert SwingUtilities.isEventDispatchThread();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		XMLLoader loader = new XMLLoader();
		loader.loadFile(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/1.xml"));
		mv = new MazeView(loader.getMaze());
		controller = new Controller(loader.getMaze());
		mv.addKeyListener(controller);
		gui();
		setVisible(true);
	}
	
	/**
	 * Constructor for loading a particular level - Not working atm?
	 * @param file specified level to be loaded
	 */
	
	public Game(File file) {
		assert SwingUtilities.isEventDispatchThread();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		XMLLoader loader = new XMLLoader();
		loader.loadFile(file);
		mv = new MazeView(loader.getMaze());
		gui();
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Creates window for the main GUI
	 */
	public void gui() {
		panel = new JPanel();
		setResizable(false);
		setSize(new Dimension(650, 442));
		setMinimumSize(new Dimension(650, 442));
		Timer timer = new Timer(34, unused -> {
            assert SwingUtilities.isEventDispatchThread();
            mv.repaint();
      });
      stop  = ()->{
    	  timer.stop();
    	  this.dispose();
    	  new Setup();
      };
      JButton stopbutton = new JButton("stop");
      stopbutton.addActionListener(e->stop.run());
      add(BorderLayout.NORTH, stopbutton);
      add(BorderLayout.CENTER, mv);
      setPreferredSize(new Dimension(300, 300));
      pack();
      mv.requestFocus();
      timer.start();
	}
	
}

