package view.frame.driveframe.menubar;

import javax.swing.JMenuBar;

import view.frame.driveframe.menubar.drive.Drive;
import view.frame.driveframe.menubar.help.Help;

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
