package SnakeGame;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		JFrame frame = new JFrame();
		
		GamePanel gamePanel = new GamePanel();
		
		frame.add(gamePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake");
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		gamePanel.start();
	}

}
