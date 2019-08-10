package view.frames.driveframe.menubar;

import javax.swing.JMenuBar;

import view.frames.driveframe.menubar.drive.Drive;
import view.frames.driveframe.menubar.help.Help;
import view.frames.driveframe.menubar.transfer.Transfer;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	public Drive drive = new Drive();
	public Transfer transfer = new Transfer();
	public Help help = new Help();

	public MenuBar() {		

		this.add(drive);
		this.add(transfer);
		this.add(help);
		
		this.setVisible(true);
	}
}
