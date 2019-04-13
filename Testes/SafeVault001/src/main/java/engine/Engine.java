package engine;

import java.util.ArrayList;

import engine.drive.Drive;
import engine.drive.DriveList;
import engine.drive.file.File;

public class Engine extends EngineUpdatable {
	
	public DriveList driverList = new DriveList(this);
	
	public Engine() {
	}
	
	public ArrayList<File> listFolder(Drive drive, File folder) throws Exception {
		ArrayList<File> driveFiles = new ArrayList<File>();
		
		drive.getPlugin().listFolder(folder.getPath()).forEach(file -> {
			String filePath;
			
			if(folder.getPath().isEmpty())
				filePath = file[0];
			else
				filePath = folder.getPath() + java.io.File.separator + file[0];
			
			driveFiles.add(new File(filePath, file[1]));
		});
		
		return driveFiles;
	}
	
	public void delete(Drive drive, File file) throws Exception {
		if(file.getType().equals("file"))
			drive.getPlugin().deleteFile(file.getPath());
	}
	
}
