package engine.drive.file.action;

import engine.drive.file.File;

public class Delete {
	
	public static void deleteFile(File file) throws Exception {
		file.getDrive().getPlugin().deleteFile(file.getPath());
	}
	
	public static void deleteFolder(File folder) throws Exception {
		folder.getDrive().getPlugin().deleteFolder(folder.getPath());
		
		// Delete each file from the folder
	}

}
