package view.driveframe.menubar.drivemenu.settingsdrive;

import javax.swing.JMenu;

import view.driveframe.menubar.drivemenu.DriveMenu;
import view.driveframe.menubar.drivemenu.settingsdrive.item.Item;

@SuppressWarnings("serial")
public class SettingsDrive extends JMenu {
	
	private DriveMenu drive_menu;
	
	public SettingsDrive(DriveMenu drive_menu) {
		this.drive_menu = drive_menu;
		
		this.setText("Settings Drive");
		
		drive_menu.menu_bar().drive_frame().view().engine().drives().subscribe((message) -> notification());
	}
	
	public DriveMenu drive_menu() {
		return drive_menu;
	}
	
	private void notification() {
		this.removeAll();
		
		drive_menu.menu_bar()
				.drive_frame()
				.view()
				.engine()
				.drives()
				.get()
				.forEach((drive) -> {
					this.add(new Item(this, drive));
				});
	}

}
