package view.driveframe.menubar.drivemenu;

import javax.swing.JMenu;

import view.driveframe.menubar.MenuBar;
import view.driveframe.menubar.drivemenu.adddrive.AddDrive;
import view.driveframe.menubar.drivemenu.removedrive.RemoveDrive;
import view.driveframe.menubar.drivemenu.settingsdrive.SettingsDrive;

@SuppressWarnings("serial")
public class DriveMenu extends JMenu {
	
	private MenuBar menu_bar;
	
	private AddDrive add_drive;
	private RemoveDrive remove_drive;
	private SettingsDrive settings_drive;
	
	public DriveMenu(MenuBar menu_bar) {
		this.menu_bar = menu_bar;
		
		add_drive = new AddDrive(this);
		remove_drive = new RemoveDrive(this);
		settings_drive = new SettingsDrive(this);
		
		this.setText("Drive");
		
		this.add(add_drive);
		this.add(remove_drive);
		this.add(settings_drive);
	}
	
	public MenuBar menu_bar() {
		return menu_bar;
	}

}
