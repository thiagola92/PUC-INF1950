package view.frames.loadingframe;

import javax.swing.JFrame;

import view.frames.loadingframe.panel.Panel;

@SuppressWarnings("serial")
public class LoadingFrame extends JFrame {
	
	public Panel panel = new Panel();
	
	private final int WIDTH = 250;
	private final int HEIGHT = 100;
	
	public LoadingFrame() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setTitle("LOADING");
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(this.getLocation().x - WIDTH/2, this.getLocation().y - HEIGHT/2);
		this.setContentPane(panel);
		
		this.setVisible(true);
	}

}
