package view.frame.menubar.drive.adddrive;

import javax.swing.JMenu;

import view.frame.menubar.drive.adddrive._default.Default;
import view.frame.menubar.drive.adddrive.googledrive.GoogleDrive;

@SuppressWarnings("serial")
public class AddDrive extends JMenu {
	
	public Default _default = new Default();
	public GoogleDrive googleDrive = new GoogleDrive();

	public AddDrive() {
		this.setText("Add Drive");

		this.add(_default);
		this.add(googleDrive);
	}
}
