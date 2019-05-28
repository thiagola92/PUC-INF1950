package view.driveframe.menubar;

import javax.swing.JMenuBar;

import view.driveframe.menubar.drive.Drive;
import view.driveframe.menubar.help.Help;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	public Drive drive = new Drive();
	public Help help = new Help();

	public MenuBar() {		

		this.add(drive);
		this.add(help);
		
		this.setVisible(true);
	}
}
