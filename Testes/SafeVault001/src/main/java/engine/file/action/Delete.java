package engine.file.action;

import java.util.ArrayList;

import engine.Engine;
import engine.file.File;
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
		File index = Index.getIndex(vault, "index");
		ArrayList<File> files = Index.readIndex(index);
		
		for(File f : files) {			
			if(file.isEqualTo(f)) {
				files.remove(f);
				break;
			}
		}
		
		Index.writeIndex(files, index);
	}
	
	public static void deleteSafeFolder(File folder) throws Exception {
		ArrayList<File> files = List.listSafeFolder(folder);
		
		for(File file : files)
			Engine.delete(file);
		
		Engine.delete(folder);
	}

}
