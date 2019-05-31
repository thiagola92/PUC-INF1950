package view.drivelist;

import java.util.ArrayList;
import java.util.stream.Stream;

import engine.file.drive.Drive;
import view.View;
import view.drivelist.exception.NameAlreadyUsedException;
import view.update.UpdateOptions;

// DriveList é classe da Engine ou View?
public class DriveList {
	
	private ArrayList<Drive> driveList = new ArrayList<Drive>();
	
	public DriveList() {
	}
	
	public Stream<Drive> getDrives() {
		return driveList.stream();
	}
	
	public boolean isNameUsed(String driveName) {
		return driveList
				.stream()
				.anyMatch((drive) -> drive.getName().equals(driveName));
	}
	
	public void addDrive(Drive drive) throws Exception {
		if(isNameUsed(drive.getName()))
			throw new NameAlreadyUsedException();
		
		driveList.add(drive);
		
		View.update.updateListeners(UpdateOptions.DRIVE_UPDATE);
	}
	
	public void removeDrive(String driveName) {
		driveList.removeIf((drive) -> drive.getName().equals(driveName));
		
		View.update.updateListeners(UpdateOptions.DRIVE_UPDATE);
	}
	
}
