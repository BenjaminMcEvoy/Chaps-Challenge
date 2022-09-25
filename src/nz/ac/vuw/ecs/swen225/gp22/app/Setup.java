package App;

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
	
	JButton newGame;
	JButton loadGame;
	JButton instructions;
	JButton quit;
	JButton back;
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
		setSize(new Dimension(300, 300));
		setMinimumSize(new Dimension(200, 210));
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
			// TO DO :
			//		- Create new game from level selected using xml files
		}
		//user clicks on loadGame
		else if (e.getSource().equals(loadGame)) {
			System.out.println("Load Game");
			// TO DO :
			//		- Load either a new game at level 1/2 or load a saved game using xml files
		}
		//user clicks on instructions
		else if (e.getSource().equals(instructions)) {
			System.out.println("Instructions");
			clearPanel.run();
			instructions();
		}
		//user clicks on back
		else if(e.getSource().equals(back)) {
			System.out.println("Back");
			remove(back);
			clearPanel.run();
			mainMenu();
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
	
	public void instructions() {
		this.setMinimumSize(new Dimension(410, 210));
		var up = new JLabel("Up = Up arrow key");
		var down = new JLabel("Down = Down arrow key");
		var left = new JLabel("Left = Left arrow key");
		var right = new JLabel("Right = Right arrow key");
		var pause = new JLabel("Pause = Space key");
		var unpause = new JLabel("Unpause = Escape key");
		var exit = new JLabel("Exit without saving = CTRL + X");
		var exsv = new JLabel("Exit and save = CTRL + S");
		var reload = new JLabel("Reload saved game = CTRL + R");
		var gameOne = new JLabel("Start new game at Lvl 1 = CTRL + 1");
		var gameTwo = new JLabel("Start new game at Lvl 2 = CTRL + 2");
		panel = new JPanel(new GridLayout(6, 2));
		back = new JButton("Back");
		back.addActionListener(this);

		panel.add(up);
		panel.add(down);
		panel.add(left);
		panel.add(right);
		panel.add(pause);
		panel.add(unpause);
		panel.add(exit);
		panel.add(exsv);
		panel.add(reload);
		panel.add(gameOne);
		panel.add(gameTwo);
		add(back);
		
		add(BorderLayout.CENTER, panel);
		add(BorderLayout.SOUTH, back);
		add(panel);
		pack();
	}
	
}
