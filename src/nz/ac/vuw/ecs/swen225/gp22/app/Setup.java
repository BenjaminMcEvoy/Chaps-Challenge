package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;

import nz.ac.vuw.ecs.swen225.gp22.recorder.Recorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Setup screen for Chap's Challenge. User can create a game, load a saved game, check instructions,
 * go back to main menu after reading the instructions, or quit.
 * 
 * @author Carlo Cigaral - 300572686
 *
 */
public class Setup extends JFrame implements MouseListener{
	
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
		setSize(new Dimension(650, 450));
		setMinimumSize(new Dimension(650, 450));
		
		panel = new JPanel();
		addMouseListener(this);
		try {
			BufferedImage image = ImageIO.read(new File("res/graphics/MenuScreen.png"));
			JLabel menuScreen = new JLabel(new ImageIcon(image));
			panel.add(menuScreen);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		add(panel);
		pack();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int mouseX = e.getX();
		int mouseY = e.getY();

		if(mouseX >= 305 && mouseX <= 375 && mouseY >= 276 && mouseY <= 305) {
			System.out.println("New Game");
			this.dispose();
			new Game(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/Levels/1.xml"));
		}
		
		else if (mouseX >= 305 && mouseX <= 370 && mouseY >= 322 && mouseY <= 346) {
			System.out.println("Load Game");
			this.dispose();
			//replace this with a file selector eventually
			JFileChooser chooser = new JFileChooser(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/SavedGame"));
			int j = chooser.showOpenDialog(null);
			if(j == JFileChooser.APPROVE_OPTION) {
				try {
					File f = chooser.getSelectedFile();
					this.dispose();
					new Game(Recorder.LoadSave(f));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		else if (mouseX >= 310 && mouseX <= 365 && mouseY >= 364 && mouseY <= 387) {
			System.out.println("Instructions");
			clearPanel.run();
			this.dispose();
			new Instructions();
		}

		else if (mouseX >= 310 && mouseX <= 365 && mouseY >= 410 && mouseY <= 432) {
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	
}
