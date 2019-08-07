package view.driveframe.menubar.drivemenu.removedrive.item;

import javax.swing.JMenuItem;

import engine.drives.drive.Drive;
import view.driveframe.menubar.drivemenu.removedrive.RemoveDrive;

@SuppressWarnings("serial")
public class Item extends JMenuItem{
	
	private RemoveDrive remove_drive;
	private Drive drive;
	
	public Item(RemoveDrive remove_drive, Drive drive) {
		this.remove_drive = remove_drive;
		this.drive = drive;
		
		this.setText(drive.name());
		
		this.addActionListener((event) -> remove());
	}
	
	private void remove() {
		remove_drive.drive_menu().menu_bar().drive_frame().view().engine().drives().remove(drive.name());
	}

}
