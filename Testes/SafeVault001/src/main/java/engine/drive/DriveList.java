package engine.drive;

import java.util.ArrayList;
import java.util.stream.Stream;

import engine.Engine;

public class DriveList {
	
	private Engine engine;
	
	private ArrayList<Drive> driveList = new ArrayList<Drive>();
	
	public DriveList(Engine engine) {
		this.engine = engine;
	}
	
	public Stream<Drive> getDrives() {
		return driveList.stream();
	}
	
	public boolean isNameUsed(String driveName) {
		return driveList
				.stream()
				.anyMatch((drive) -> drive.getName().equals(driveName));
	}
	
	public void addDrive(String driveName, String pluginName) throws Exception {
		if(isNameUsed(driveName))
			throw new Exception("Drive name in use");
		
		driveList.add(new Drive(driveName, pluginName));
		
		engine.updateEngineListeners();
	}
	
	public void removeDrive(String driveName) {
		driveList.removeIf((drive) -> drive.getName().equals(driveName));
		
		engine.updateEngineListeners();
	}
	
}
