package view.frame.menubar.driver;

import javax.swing.JMenu;

import view.frame.menubar.driver.adddriver.AddDriver;

@SuppressWarnings("serial")
public class Driver extends JMenu {
	
	public AddDriver addDriver = new AddDriver();
	
	public Driver() {
		this.setText("Driver");
		
		this.add(addDriver);
	}
}
