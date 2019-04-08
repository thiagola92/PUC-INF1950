package view.menubar.driver;

import javax.swing.JMenu;

import view.menubar.driver.adddriver.Default;
import view.menubar.driver.adddriver.GoogleDrive;

@SuppressWarnings("serial")
public class AddDriver extends JMenu {

	public AddDriver() {
		this.setText("Add Driver");

		this.add(new Default());
		this.add(new GoogleDrive());
	}
}
