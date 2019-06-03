package engine.file.action;

import engine.file.File;
import engine.file.RandomName;
import engine.file.Utility;
import engine.file.vault.index.Index;

public class Create {

	public static void createFolder(File folder, String newFolderName) throws Exception {
		String newFolderPath = Utility.concatPath(folder.getPath(), newFolderName);
		
		folder.getDrive().getPlugin().createFolder(newFolderPath);
	}

	public static void createSafeFolder(File folder, String newFolderName) throws Exception {
		String randomName = RandomName.createRandomName(folder);
		String newFolderPath = Utility.concatPath(folder.getPath(), randomName);
		
		File newFolder = new File(folder.getDrive(), newFolderPath, "folder");
		newFolder.setName(newFolderName);
		
		Index.addFile(newFolder, folder);
	}
}
