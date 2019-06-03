package engine.file.action;

import java.util.ArrayList;

import engine.Engine;
import engine.file.File;
import engine.file.RandomName;
import engine.file.Utility;
import engine.file.vault.index.Index;

public class Copy {
	
	public static void copyFile(File file, File toFolder) throws Exception {
		String filePath = Utility.concatPath(toFolder.getPath(), file.getName());
		toFolder.getDrive().getPlugin().createFile(filePath);

		byte[] fileBytes = file.getDrive().getPlugin().readFile(file.getPath());
		toFolder.getDrive().getPlugin().writeFile(filePath, fileBytes);
	}
	
	public static void copyFolder(File folder, File toFolder) throws Exception {
		ArrayList<File> files = List.listFolder(folder);
		
		File newFolder = Engine.createFolder(toFolder, folder.getName());
		
		for(File file : files)
			Engine.copy(file, newFolder);
	}
	
	public static void copyFileToSafeFolder(File file, File toFolder) throws Exception {
		String randomName = RandomName.createRandomName(toFolder);
		String filePath = Utility.concatPath(toFolder.getPath(), randomName);
		
		File newFile = new File(toFolder.getDrive(), filePath, "file");
		newFile.setName(file.getName());
		
		Index.addFile(newFile, toFolder);
	}
	
	public static void copyFolderToSafeFolder(File folder, File toFolder) throws Exception {
		ArrayList<File> files = Engine.listFolder(folder);
		
		File newFolder = Engine.createFolder(toFolder, folder.getName());
		
		for(File file : files)
			Engine.copy(file, newFolder);
	}
	
}
