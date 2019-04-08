package view.menubar;

import javax.swing.JMenu;

import view.menubar.driver.AddDriver;

@SuppressWarnings("serial")
public class Driver extends JMenu {
	
	public Driver() {
		this.setText("Driver");
		
		this.add(new AddDriver());
	}
}
