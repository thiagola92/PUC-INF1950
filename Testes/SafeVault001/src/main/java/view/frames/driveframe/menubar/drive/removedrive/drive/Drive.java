package view.frames.driveframe.menubar.drive.removedrive.drive;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Drive extends JMenuItem {
	
	public Drive(String driveName) {
		this.setText(driveName);
		
		this.addActionListener(new OnClick(this));
	}

}
