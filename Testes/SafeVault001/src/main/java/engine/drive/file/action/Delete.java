package engine.drive.file.action;

import java.util.ArrayList;

import engine.Engine;
import engine.drive.file.File;

public class Delete {
	
	public static void deleteFile(File file) throws Exception {
		file.getDrive().getPlugin().deleteFile(file.getPath());
	}
	
	public static void deleteFolder(File folder) throws Exception {
		folder.getDrive().getPlugin().deleteFolder(folder.getPath());
	}
	
	public static void deleteFolderRecursive(File folder) throws Exception {
		ArrayList<File> files = Engine.listFolder(folder);
		
		for(File file : files)
			Engine.delete(file, true);
		
		folder.getDrive().getPlugin().deleteFolder(folder.getPath());
	}

}
