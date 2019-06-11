package engine.file.action;

import java.nio.file.Paths;
import java.util.ArrayList;

import engine.Engine;
import engine.file.File;
import engine.file.Utility;
import engine.file.vault.Vault;
import engine.file.vault.index.Index;

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
		File vault = Vault.getVault(file);
		String fileName = Paths.get(file.getPath()).getFileName().toString();
		String filePath = Utility.concatPath(vault.getPath(), fileName);
		
		file.getDrive().getPlugin().deleteFile(filePath);
		Index.getIndex(file).removeFile(file);
	}
	
	public static void deleteSafeFolder(File folder) throws Exception {
		ArrayList<File> files = Engine.listFolder(folder);
		
		for(File file : files)
			Engine.delete(file);

		Index.getIndex(folder).removeFile(folder);
	}

}
