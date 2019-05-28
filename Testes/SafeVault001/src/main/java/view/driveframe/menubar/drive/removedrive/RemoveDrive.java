package view.driveframe.menubar.drive.removedrive;

import javax.swing.JMenu;

import engine.Engine;

@SuppressWarnings("serial")
public class RemoveDrive extends JMenu {
	
	public RemoveDrive() {
		this.setText("Remove Drive");
		
		Engine.update.addUpdateListener(new OnDriveUpdate(this));
	}

}
