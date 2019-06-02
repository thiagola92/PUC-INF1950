package engine.file.action;

import java.util.ArrayList;

import engine.Engine;
import engine.file.File;

public class Delete {
	
	public static void deleteFile(File file) throws Exception {
		file.getDrive().getPlugin().deleteFile(file.getPath());
	}
	
	public static void deleteFolder(File folder) throws Exception {
		ArrayList<File> files = Engine.listFolder(folder);
		
		for(File file : files)
			Engine.delete(file);
		
		folder.getDrive().getPlugin().deleteFolder(folder.getPath());
	}
	
	public static void deleteSafeFile(File file) throws Exception {
		
	}
	
	public static void deleteSafeFolder(File file) throws Exception {
		
	}

}
