package view;

import javax.swing.JMenuBar;

import view.menubar.Driver;
import view.menubar.Help;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	public MenuBar() {		

		this.add(new Driver());
		this.add(new Help());
		
		this.setVisible(true);
	}
}
