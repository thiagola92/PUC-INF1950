package view.driveframe.menubar.drive.removedrive;

import engine.Engine;
import engine.update.UpdateListener;
import engine.update.UpdateOptions;
import view.driveframe.menubar.drive.removedrive.drive.Drive;

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
		Engine.driverList.getDrives().forEach(drive -> {
			removeDrive.add(new Drive(drive.getName()));
		});
	}

}
