package view.frame.driveframe.menubar.drive.removedrive;

import view.View;
import view.frame.driveframe.menubar.drive.removedrive.drive.Drive;
import view.update.UpdateListener;
import view.update.UpdateOptions;

public class OnDriveUpdate implements UpdateListener {
	
	private RemoveDrive removeDrive;
	
	public OnDriveUpdate(RemoveDrive removeDrive) {
		this.removeDrive = removeDrive;
	}

	@Override
	public void engineUpdated(UpdateOptions engineUpdate) {
		if(engineUpdate == UpdateOptions.FILE_UPDATE)
			return;
		
		removeDrive.removeAll();
		View.driverList.getDrives().forEach(drive -> {
			removeDrive.add(new Drive(drive.getName()));
		});
	}

}
