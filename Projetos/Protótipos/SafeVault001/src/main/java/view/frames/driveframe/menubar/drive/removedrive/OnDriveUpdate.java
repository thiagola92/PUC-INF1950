package view.frames.driveframe.menubar.drive.removedrive;

import view.View;
import view.frames.driveframe.menubar.drive.removedrive.drive.Drive;
import view.update.UpdateListener;
import view.update.UpdateOptions;

public class OnDriveUpdate implements UpdateListener {
	
	private RemoveDrive removeDrive;
	
	public OnDriveUpdate(RemoveDrive removeDrive) {
		this.removeDrive = removeDrive;
	}

	@Override
	public void engineUpdated(UpdateOptions engineUpdate) {
		if(engineUpdate != UpdateOptions.DRIVE_ADDED)
			return;
		
		removeDrive.removeAll();
		View.driverList.getStream().forEach(drive -> {
			removeDrive.add(new Drive(drive.getName()));
		});
	}

}
