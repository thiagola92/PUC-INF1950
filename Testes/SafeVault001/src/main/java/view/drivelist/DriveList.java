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
	
	public Stream<Drive> getStream() {
		return driveList.stream();
	}
	
	public Drive getLastDrive() {
		if(driveList.size() == 0)
			return null;
		
		return driveList.get(driveList.size() - 1);
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
		
		View.update.updateListeners(UpdateOptions.DRIVE_ADDED);
	}
	
	public void removeDrive(String driveName) {
		driveList.removeIf((drive) -> drive.getName().equals(driveName));
		
		View.update.updateListeners(UpdateOptions.DRIVE_REMOVED);
	}
	
}
