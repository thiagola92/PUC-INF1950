package view.settingsframe;

import javax.swing.JFrame;

import engine.drives.drive.Drive;
import view.driveframe.DriveFrame;
import view.settingsframe.panel.Panel;

@SuppressWarnings("serial")
public class SettingsFrame extends JFrame {
	
	private DriveFrame drive_frame;
	private Drive drive;
	private Panel panel;
	
	private String NAME = "Settings: ";
	private int WIDTH = 700;
	private int HEIGHT = 400;
	
	public SettingsFrame(DriveFrame drive_frame, Drive drive) {
		this.drive_frame = drive_frame;
		this.drive = drive;
		
		panel = new Panel(this, drive);
		NAME += drive.name();
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setTitle(NAME);
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(this.getLocation().x - WIDTH/2, this.getLocation().y - HEIGHT/2);
		
		this.setContentPane(panel);
		this.addWindowListener(new OnChange(this));
		
		this.setVisible(true);
	}
	
	public DriveFrame drive_frame() {
		return drive_frame;
	}
	
	public Drive drive() {
		return drive;
	}
	
	public Panel panel() {
		return panel;
	}

}
