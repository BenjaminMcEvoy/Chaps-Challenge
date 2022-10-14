package nz.ac.vuw.ecs.swen225.gp22.app;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.Maze;
import nz.ac.vuw.ecs.swen225.gp22.persistency.*;
import nz.ac.vuw.ecs.swen225.gp22.renderer.*;
import nz.ac.vuw.ecs.swen225.gp22.recorder.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Main class for running game
 * @author Carlo Cigaral - 300572686
 *
 */
public class Game extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Runnable stop = ()->{};
	private Runnable restart = ()->{};
	private Runnable nextLvl = ()->{};
	private Runnable clearScreen = ()->{};
	private MazeView mv;
	private JPanel container;
	private Controller controller;
	private Maze maze;
	private File level;
	private InventoryView iv;
	
	private Sound sound = new Sound();
	private Timer timer;
	
	private long duration;

	private boolean paused = false;
	
	public Runnable save = ()->{
		JFileChooser chooser = new JFileChooser(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/SavedGame"));
		int j = chooser.showSaveDialog(null);
		if(j == JFileChooser.APPROVE_OPTION) {
			try {
				File f = chooser.getSelectedFile();
				Recorder.SaveGame(maze, f);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}};


		public Game(Maze m) {
			
			sound.playAmbient();
			
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

			assert SwingUtilities.isEventDispatchThread();
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			maze = m;
			mv = new MazeView(maze);
			controller = new Controller(maze);
			mv.addKeyListener(controller);

			iv = new InventoryView(maze);
			level = new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/" + maze.getLevel() + ".xml");

			duration = 100000;

			
			gui();
			setVisible(true);
		}

		/**
		 * Constructor for loading a particular level - Not working atm?
		 * @param file specified level to be loaded
		 */

		public Game(File file) {
			
			sound.playAmbient();
			
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

			assert SwingUtilities.isEventDispatchThread();
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			XMLLoader loader = new XMLLoader();
			loader.loadFile(file);

			maze = loader.getMaze();
			mv = new MazeView(maze);

			controller = new Controller(maze);
			mv.addKeyListener(controller);

			iv = new InventoryView(maze);
			level = new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/" + maze.getLevel() + ".xml");

			duration = 100000;

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

			JMenuBar tools = new JMenuBar();

			container = new JPanel();
			JPanel camera = new JPanel();
			JPanel info = new JPanel();

			JPanel infoLabels = new JPanel();
			JPanel infoInv = new JPanel();

			infoLabels.setLayout(new GridLayout(5, 1));
			infoInv.setLayout(new GridLayout(2,1));

			info.setLayout(new GridLayout(2, 1));
			container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

			setResizable(false);
			setSize(new Dimension(650, 450));
			setMinimumSize(new Dimension(650, 450));		

			stop  = ()->{
				this.dispose();
				new Setup();
			};
			restart  = ()->{
				this.dispose();
				new Game(level);
			};

			nextLvl = ()->{
				this.dispose();
				String filename = "src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/" + (maze.getLevel()+1) + ".xml";
				new Game(new File(filename));
			};
			
			clearScreen = ()->{
				this.dispose();
			};

			JMenuItem stopbutton = new JMenuItem("stop");
			stopbutton.addActionListener(e->stop.run());

			JMenuItem saveButton = new JMenuItem("Save");
			saveButton.addActionListener(e->save.run());

			JMenuItem nextButton = new JMenuItem("Next");
			nextButton.addActionListener(e->Recorder.nextMove(maze));

			JMenuItem autoButton = new JMenuItem("Auto-Play");
			autoButton.addActionListener(e->Recorder.AutoPlay());

			JLabel cl = new JLabel("TIME");
			JLabel cd = new JLabel();

			JLabel pl = new JLabel("Patties Left");
			JLabel pc = new JLabel();		

			JLabel infoTxt = new JLabel();

			JLabel inv = new JLabel("Inventory");

			JSlider speedControl = new JSlider(1, 5, 1);
			speedControl.addChangeListener(e->Recorder.playbackSpeed=speedControl.getValue());
			speedControl.setFocusable(false);
			timer = new Timer(10, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if(!paused) {
						duration -= 10;
						
						if (duration <= 0) {
							duration = 100000;
							timer.stop();
	
							String[] confirm = {"Quit", "Restart"};
							JPanel panel = new JPanel();
							JLabel label = new JLabel("You Lose!");
							panel.add(label);
							int choice = JOptionPane.showOptionDialog(null, panel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, confirm, confirm[1]);
							if(choice == 0) {
								System.exit(0);
							} else {
								restart.run();
							}
	
						}
						if(maze.hasWon()) {
							timer.stop();
							nextLvl.run();
						}
						if(maze.getChap().getStandingOn() instanceof InfoTile) {
							infoTxt.setText(((InfoTile)maze.getChap().getStandingOn()).getText());
						} else {
							infoTxt.setText("");
						}
						
						if(controller.getPressedKeys().contains(KeyEvent.VK_CONTROL) && 
								(controller.getPressedKeys().contains(KeyEvent.VK_1) 
										|| controller.getPressedKeys().contains(KeyEvent.VK_2) 
										|| controller.getPressedKeys().contains(KeyEvent.VK_X) 
										|| controller.getPressedKeys().contains(KeyEvent.VK_R) ) ) {
							clearScreen.run();
						}
						
						if(controller.getPressedKeys().contains(KeyEvent.VK_SPACE)) {
							paused = true;
						}
						
						
						
					}
					if(Recorder.auto) {
						Recorder.Next(maze);
						try {
							TimeUnit.MILLISECONDS.sleep(1000/Recorder.playbackSpeed);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					if(controller.getPressedKeys().contains(KeyEvent.VK_ESCAPE)) {
						paused = false;
					}
					cd.setText(" " + (int)((duration)/1000));
					pc.setText(" " + maze.checkTreasures());
					mv.repaint();
					iv.repaint();
				}

			});
			if(!timer.isRunning()) {
				timer.start();
			}

			tools.add(stopbutton);
			tools.add(saveButton);
			tools.add(nextButton);
			tools.add(autoButton);
			tools.add(speedControl);

			add(tools);

			infoLabels.add(cl);
			infoLabels.add(cd);
			infoLabels.add(pl);
			infoLabels.add(pc);
			infoLabels.add(inv);
			infoInv.add(iv);
			infoInv.add(infoTxt);



			getContentPane().add(tools, BorderLayout.PAGE_START);
			camera.add(mv);	

			info.add(infoLabels);
			info.add(infoInv);

			container.add(camera);
			container.add(info);
			add(container);
			this.setPreferredSize(new Dimension(300,300));

			pack();
			mv.requestFocus();

		}



}

