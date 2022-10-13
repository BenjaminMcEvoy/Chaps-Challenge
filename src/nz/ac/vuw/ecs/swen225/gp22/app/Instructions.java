package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Class to display instructions window - shows key binds
 * @author CarloC
 *
 */
public class Instructions extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton back;
	JPanel panel;
	
	Runnable clearPanel = ()->{
		panel.removeAll();
		remove(panel);
	};
	
	/**
	 * Constructor for instructions - Initializes swing configurations
	 */
	public Instructions() {
		assert SwingUtilities.isEventDispatchThread();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		instructions();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(back)) {
			clearPanel.run();
			this.dispose();
			new Setup();
		}		
	}

	/**
	 * Displays JLabels to show controls
	 */
	public void instructions() {
		setResizable(false);
		setSize(new Dimension(650, 450));
		setMinimumSize(new Dimension(650, 450));
		panel = new JPanel(new GridLayout(6, 2));
		panel.add(new JLabel("Up = Up arrow key"));
		
		panel.add(new JLabel("Up = Up arrow key"));
		panel.add(new JLabel("Down = Down arrow key"));
		panel.add(new JLabel("Left = Left arrow key"));
		panel.add(new JLabel("Right = Right arrow key"));
		panel.add(new JLabel("Pause = Space key"));
		panel.add(new JLabel("Unpause = Escape key"));
		panel.add(new JLabel("Exit without saving = CTRL + X"));
		panel.add(new JLabel("Exit and save = CTRL + S"));
		panel.add(new JLabel("Reload saved game = CTRL + R"));
		panel.add(new JLabel("Start new game at Lvl 1 = CTRL + 1"));
		panel.add(new JLabel("Start new game at Lvl 2 = CTRL + 2"));
		
		back = new JButton("Back");
		back.addActionListener(this);
		panel.add(back);
		
		add(BorderLayout.CENTER, panel);
		add(BorderLayout.SOUTH, back);
		add(panel);
		pack();
	}
	
	
	
}
