package engine.drive.file.action;

import engine.drive.file.File;

public class Create {

	public static void createFolder(File folder) throws Exception {
		folder.getDrive().getPlugin().createFolder(folder.getPath());
	}
}
