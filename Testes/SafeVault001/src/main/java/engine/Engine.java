package engine;

import java.io.File;
import java.util.ArrayList;

import engine.driver.Drive;
import engine.driver.DriveFile;
import engine.driver.DriveList;

public class Engine extends EngineUpdatable {
	
	public DriveList driverList = new DriveList(this);
	
	public Engine() {
	}
	
	public ArrayList<DriveFile> listFolder(Drive drive, String path) throws Exception {
		ArrayList<DriveFile> driveFiles = new ArrayList<DriveFile>();
		
		drive.getPlugin().listFolder(path).forEach(file -> {
			String filePath;
			
			if(path.isEmpty())
				filePath = file[0];
			else
				filePath = path + File.separator + file[0];
			
			driveFiles.add(new DriveFile(filePath, file[1]));
		});
		
		return driveFiles;
	}
	
}
