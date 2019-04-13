package view.frame.menubar.drive;

import javax.swing.JMenu;

import view.frame.menubar.drive.adddrive.AddDrive;

@SuppressWarnings("serial")
public class Drive extends JMenu {
	
	public AddDrive addDriver = new AddDrive();
	
	public Drive() {
		this.setText("Drive");
		
		this.add(addDriver);
	}
}
