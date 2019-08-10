package view.driveframe;

import javax.swing.JFrame;

import view.View;
import view.driveframe.menubar.MenuBar;
import view.driveframe.panel.Panel;

@SuppressWarnings("serial")
public class DriveFrame extends JFrame {
	
	private View view;
	
	private MenuBar menu_bar;
	private Panel panel;
	
	private final String NAME = "Safe Vault";
	private final int WIDTH = 700;
	private final int HEIGHT = 700;
	
	public DriveFrame(View view) {
		this.view = view;
		
		menu_bar = new MenuBar(this);
		panel = new Panel(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		
		this.setTitle(NAME);
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(this.getLocation().x - WIDTH/2, this.getLocation().y - HEIGHT/2);
		
		this.setJMenuBar(menu_bar);
		this.setContentPane(panel);
		
		this.setVisible(true);
	}
	
	public View view() {
		return view;
	}

}
