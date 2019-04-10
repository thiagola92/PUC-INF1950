package view.frame.menubar.driver.adddriver;

import javax.swing.JMenu;

import view.frame.menubar.driver.adddriver._default.Default;
import view.frame.menubar.driver.adddriver.googledrive.GoogleDrive;

@SuppressWarnings("serial")
public class AddDriver extends JMenu {
	
	public Default _default = new Default();
	public GoogleDrive googleDrive = new GoogleDrive();

	public AddDriver() {
		this.setText("Add Driver");

		this.add(_default);
		this.add(googleDrive);
	}
}
