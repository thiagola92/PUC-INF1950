package view.driveframe.menubar.drivemenu.adddrive;

import javax.swing.JMenu;

import view.driveframe.menubar.drivemenu.DriveMenu;
import view.driveframe.menubar.drivemenu.adddrive._local.Local;
import view.driveframe.menubar.drivemenu.adddrive.googledrive.GoogleDrive;

@SuppressWarnings("serial")
public class AddDrive extends JMenu {
	
	private DriveMenu drive_menu;
	
	private Local _default;
	private GoogleDrive google_drive;
	
	public AddDrive(DriveMenu drive_menu) {
		this.drive_menu = drive_menu;
		
		_default = new Local();
		google_drive = new GoogleDrive();
		
		this.setText("Add Drive");
		
		this.add(_default);
		this.add(google_drive);
	}
	
	public DriveMenu drive_menu() {
		return drive_menu;
	}

}
