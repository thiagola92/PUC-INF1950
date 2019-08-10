package view.driveframe.menubar.drivemenu.settingsdrive.item;

import javax.swing.JMenuItem;

import engine.drives.drive.Drive;
import view.driveframe.menubar.drivemenu.settingsdrive.SettingsDrive;
import view.settingsframe.SettingsFrame;

@SuppressWarnings("serial")
public class Item extends JMenuItem {
	
	private SettingsDrive settings_drive;
	private Drive drive;
	
	public Item(SettingsDrive settings_drive, Drive drive) {
		this.settings_drive = settings_drive;
		this.drive = drive;
		
		this.setText(drive.name());
		
		this.addActionListener((event) -> settings());
	}
	
	private void settings() {
		new SettingsFrame(settings_drive.drive_menu().menu_bar().drive_frame(), drive);
	}

}
