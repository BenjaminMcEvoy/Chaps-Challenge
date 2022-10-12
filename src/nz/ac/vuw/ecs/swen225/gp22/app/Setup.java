package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Setup screen for Chap's Challenge. User can create a game, load a saved game, check instructions,
 * go back to main menu after reading the instructions, or quit.
 * 
 * @author Carlo Cigaral - 300572686
 *
 */
public class Setup extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton newGame;
	JButton loadGame;
	JButton instructions;
	JButton quit;
	JPanel panel;
	
	Runnable clearPanel = ()->{
		panel.removeAll();
		remove(panel);
	};
	
	public Setup() {
		assert SwingUtilities.isEventDispatchThread();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		mainMenu();
		setVisible(true);
	}
	
	public void mainMenu() {
		setResizable(false);
		setSize(new Dimension(650, 442));
		setMinimumSize(new Dimension(650, 442));
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel welcome = new JLabel("Welcome to Chap's Challenge!");
		welcome.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(welcome);
		
		newGame = new JButton("New Game");
		loadGame = new JButton("Load Game");
		instructions = new JButton("Instructions");
		quit = new JButton("Quit");
		
		setupButton(newGame);
		setupButton(loadGame);
		setupButton(instructions);
		setupButton(quit);
		
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		add(panel);
		pack();
		
	}
	
	public void setupButton(JButton button) {
		button.setMaximumSize(new Dimension(150, 50));
		button.setMinimumSize(new Dimension(100, 25));
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.addActionListener(this);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(button);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//user clicks on newGame
		if (e.getSource().equals(newGame)) {
			System.out.println("New Game");
			this.dispose();
			new Game();
			
		}
		
		//user clicks on loadGame
		else if (e.getSource().equals(loadGame)) {
			System.out.println("Load Game");
			this.dispose();
			//replace this with a file selector eventually
			new Game(new File("blankLevel.xml"));
		}
		
		//user clicks on instructions
		else if (e.getSource().equals(instructions)) {
			System.out.println("Instructions");
			clearPanel.run();
			this.dispose();
			new Instructions();
			
		}		
		
		//user clicks on quit
		else if(e.getSource().equals(quit)) {
			System.out.println("Quit");
			clearPanel.run();
			String[] confirm = {"Yes", "No"};
			panel = new JPanel();
			JLabel label = new JLabel("Would you like to quit?");
			panel.add(label);
			int choice = JOptionPane.showOptionDialog(null, panel, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, confirm, confirm[1]);
			if(choice == 0) {
				System.exit(0);
			} else {
				clearPanel.run();
				mainMenu();
			}
		}
	}	
}
