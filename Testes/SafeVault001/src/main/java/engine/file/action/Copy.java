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
		
		String folderPath = Utility.concatPath(toFolder.getPath(), folder.getName());
		toFolder.getDrive().getPlugin().createFolder(folderPath);
		
		File newFolder = new File(toFolder.getDrive(), folderPath, "folder");
		
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
		
		String randomName = RandomName.createRandomName(toFolder);
		String folderPath = Utility.concatPath(toFolder.getPath(), randomName);
		
		File newFolder = new File(toFolder.getDrive(), folderPath, "folder");
		newFolder.setName(folder.getName());
		
		Index.addFile(newFolder, toFolder);
		
		for(File file : files)
			Engine.copy(file, newFolder);
	}
	
}
