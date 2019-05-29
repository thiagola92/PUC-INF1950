package engine.file.action;

import java.util.ArrayList;

import engine.file.File;
import engine.file.Utility;
import engine.file.vault.Vault;
import engine.file.vault.index.Index;

public class List {
	
	public static ArrayList<File> listFolder(File folder) throws Exception {
		ArrayList<File> files = new ArrayList<File>();
		
		folder.getDrive().getPlugin().listFolder(folder.getPath()).forEach(file -> {
			String filePath = Utility.concatPath(folder.getPath(), file[0]);
			
			files.add(new File(folder.getDrive(), filePath, file[1]));
		});
		
		return files;
	}
	
	public static ArrayList<File> listSafeFolder(File folder) throws Exception {
		File vault = Vault.getVault(folder);
		File index = Index.getMainIndex(vault);
		
		if(index == null)
			return new ArrayList<File>();
		
		return Index.readIndex(index);
	}
}
