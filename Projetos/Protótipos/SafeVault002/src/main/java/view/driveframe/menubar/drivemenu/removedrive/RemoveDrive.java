package view.driveframe.menubar.drivemenu.removedrive;

import javax.swing.JMenu;

import view.driveframe.menubar.drivemenu.DriveMenu;
import view.driveframe.menubar.drivemenu.removedrive.item.Item;

@SuppressWarnings("serial")
public class RemoveDrive extends JMenu {
	
	private DriveMenu drive_menu;
	
	public RemoveDrive(DriveMenu drive_menu) {
		this.drive_menu = drive_menu;
		
		this.setText("Remove Drive");
		
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
