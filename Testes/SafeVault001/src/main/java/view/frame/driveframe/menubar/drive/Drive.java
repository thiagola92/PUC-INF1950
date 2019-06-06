package view.frame.driveframe.menubar.drive;

import javax.swing.JMenu;

import view.frame.driveframe.menubar.drive.adddrive.AddDrive;
import view.frame.driveframe.menubar.drive.removedrive.RemoveDrive;

@SuppressWarnings("serial")
public class Drive extends JMenu {
	
	public AddDrive addDrive = new AddDrive();
	public RemoveDrive removeDrive = new RemoveDrive();
	
	public Drive() {
		this.setText("Drive");
		
		this.add(addDrive);
		this.add(removeDrive);
	}
}