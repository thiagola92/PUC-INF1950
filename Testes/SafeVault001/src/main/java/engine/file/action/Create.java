package engine.file.action;

import engine.file.File;

public class Create {

	public static void createFolder(File folder, String newFolderName) throws Exception {
		String newFolderPath = Utility.concatPath(folder.getPath(), newFolderName);
		
		folder.getDrive().getPlugin().createFolder(newFolderPath);
	}
}
