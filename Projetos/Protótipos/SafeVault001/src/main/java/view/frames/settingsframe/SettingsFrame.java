package view.frames.settingsframe;

import javax.swing.JFrame;

import engine.file.drive.Drive;
import view.frames.settingsframe.panel.Panel;

@SuppressWarnings("serial")
public class SettingsFrame extends JFrame {
	
	private final String NAME = "Settings";
	private final int WIDTH = 700;
	private final int HEIGHT = 400;

	public SettingsFrame(Drive drive) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setTitle(NAME);
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(this.getLocation().x - WIDTH/2, this.getLocation().y - HEIGHT/2);
		
		this.setContentPane(new Panel(this, drive));
		this.addWindowListener(new OnChange());
		
		this.setVisible(true);
	}
}
