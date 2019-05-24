package engine.file.action;

import java.util.ArrayList;

import engine.file.File;
import engine.file.Utility;

public class List {
	
	public static ArrayList<File> listFolder(File folder) throws Exception {
		ArrayList<File> files = new ArrayList<File>();
		
		folder.getDrive().getPlugin().listFolder(folder.getPath()).forEach(file -> {
			String filePath = Utility.concatPath(folder.getPath(), file[0]);
			
			files.add(new File(folder.getDrive(), filePath, file[1]));
		});
		
		return files;
	}
}
