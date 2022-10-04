package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
	
	public void setupLabel(JButton button) {
		button.setMaximumSize(new Dimension(150, 50));
		button.setMinimumSize(new Dimension(100, 25));
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.addActionListener(this);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(button);
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
		panel.add(back);
		
		add(BorderLayout.CENTER, panel);
		add(BorderLayout.SOUTH, back);
		add(panel);
		pack();
	}
	
	
	
}
