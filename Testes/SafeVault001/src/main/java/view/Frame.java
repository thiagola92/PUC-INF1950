package view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	private final String NAME = "Transfer";
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	
	public Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		
		this.setTitle(NAME);
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(this.getLocation().x - WIDTH/2, this.getLocation().y - HEIGHT/2);
		
		this.setJMenuBar(new MenuBar());
		this.setContentPane(new Panel());
		
		this.setVisible(true);
	}

}
