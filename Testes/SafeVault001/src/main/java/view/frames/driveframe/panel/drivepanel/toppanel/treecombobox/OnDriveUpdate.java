package view.frames.driveframe.panel.drivepanel.toppanel.treecombobox;

import engine.file.drive.Drive;
import view.View;
import view.update.UpdateListener;
import view.update.UpdateOptions;

public class OnDriveUpdate implements UpdateListener {
	
	public TreeComboBox treeCombobox;
	
	private Drive driveSelected;
	
	public OnDriveUpdate(TreeComboBox treeCombobox) {
		this.treeCombobox = treeCombobox;
	}

	@Override
	public void engineUpdated(UpdateOptions engineUpdate) {
		if(engineUpdate == UpdateOptions.DRIVE_UPDATED)
			driveUpdated();
		else if(engineUpdate == UpdateOptions.DRIVE_ADDED)
			driveAdded();
		else if(engineUpdate == UpdateOptions.DRIVE_REMOVED)
			driveRemoved();
	}
	
	public void driveUpdated() {
		treeCombobox.driveSeleceted();
	}
	
	public void driveAdded() {
		Drive drive = View.driverList.getLastDrive();
		treeCombobox.addItem(drive);
	}
	
	public void driveRemoved() {
		driveSelected = (Drive)treeCombobox.getSelectedItem();
		
		treeCombobox.removeAllItems();
		
		if(View.driverList.getStream().anyMatch(drive -> driveSelected == drive))
			treeCombobox.addItem(driveSelected);
		
		View.driverList.getStream().forEach(drive -> {
			if(driveSelected == drive)
				return;
			
			treeCombobox.addItem(drive);
		});
	}

}
