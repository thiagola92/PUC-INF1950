package view.driveframe.menubar;

import javax.swing.JMenuBar;

import view.driveframe.DriveFrame;
import view.driveframe.menubar.drivemenu.DriveMenu;
import view.driveframe.menubar.vaultmenu.VaultMenu;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	private DriveFrame drive_frame;

	private DriveMenu drive_menu;
	private VaultMenu vault_menu;
	
	public MenuBar(DriveFrame drive_frame) {
		this.drive_frame = drive_frame;
		
		drive_menu = new DriveMenu(this);
		vault_menu = new VaultMenu(this);
		
		this.add(drive_menu);
		this.add(vault_menu);
		
		this.setVisible(true);
	}
	
	public DriveFrame drive_frame() {
		return drive_frame;
	}
}
