package view.driveframe;

import javax.swing.JFrame;

import view.driveframe.menubar.MenuBar;
import view.driveframe.panel.Panel;

@SuppressWarnings("serial")
public class DriveFrame extends JFrame {
	
	public MenuBar menuBar = new MenuBar();
	public Panel panel = new Panel();
	
	private final String NAME = "SafeVault";
	private final int WIDTH = 700;
	private final int HEIGHT = 700;
	
	public DriveFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		
		this.setTitle(NAME);
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(this.getLocation().x - WIDTH/2, this.getLocation().y - HEIGHT/2);
		
		this.setJMenuBar(menuBar);
		this.setContentPane(panel);
		
		this.setVisible(true);
	}

}
