package engine.file.action;

import java.util.ArrayList;

import engine.Engine;
import engine.file.File;
import engine.file.Utility;

public class Move {
	
	public static void moveFile(File file, File toFolder) throws Exception {
		Engine.copy(file, toFolder);
		Engine.delete(file);
	}
	
	public static void moveFolder(File folder, File toFolder) throws Exception {
		ArrayList<File> files = List.listFolder(folder);
		
		String folderPath = Utility.concatPath(toFolder.getPath(), folder.getName());
		toFolder.getDrive().getPlugin().createFolder(folderPath);
		
		File newFolder = new File(toFolder.getDrive(), folderPath, "folder");
		
		for(File file : files)
			Engine.move(file, newFolder);
		
		Engine.delete(folder);
	}
	
	public static void moveFileToSafeFolder(File file, File toFolder) throws Exception {
		Engine.copy(file, toFolder);
		Engine.delete(file);
	}
	
	public static void moveFolderToSafeFolder(File folder, File toFolder) throws Exception {
//		Engine.copy(folder, toFolder);
//		Engine.delete(folder);
	}
	
}
