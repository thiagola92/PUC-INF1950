package view.driveframe.menubar.drive.removedrive;

import javax.swing.JMenu;

import view.View;

@SuppressWarnings("serial")
public class RemoveDrive extends JMenu {
	
	public RemoveDrive() {
		this.setText("Remove Drive");
		
		View.update.addUpdateListener(new OnDriveUpdate(this));
	}

}
