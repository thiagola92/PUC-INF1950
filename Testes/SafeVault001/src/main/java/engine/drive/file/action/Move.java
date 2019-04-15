package engine.drive.file.action;

import java.util.ArrayList;

import engine.Engine;
import engine.drive.file.File;

public class Move {
	
	public static void moveFile(File file, File toFolder) throws Exception {
		String filePath = Utility.concatPath(toFolder.getPath(), file.getName());
		toFolder.getDrive().getPlugin().createFile(filePath);

		byte[] fileBytes = file.getDrive().getPlugin().readFile(file.getPath());
		toFolder.getDrive().getPlugin().writeFile(filePath, fileBytes);
		
		Delete.deleteFile(file);
	}
	
	public static void moveFolder(File folder, File toFolder) throws Exception {
		String folderPath = Utility.concatPath(toFolder.getPath(), folder.getName());
		toFolder.getDrive().getPlugin().createFolder(folderPath);
		
		File newFolder = new File(toFolder.getDrive(), folderPath, "folder");
		ArrayList<File> files = List.listFolder(folder);
		
		for(File file : files)
			Engine.move(file, newFolder);
		
		Delete.deleteFolder(folder);
	}
	
}
