package engine.file.action;

import java.nio.file.Path;
import java.nio.file.Paths;
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
		ArrayList<File> files = Index.getIndex(folder).readIndex();

		String folderPath = Vault.pathInsideVault(folder);
		
		for(int i = files.size() - 1; i >= 0; i--) {
			String filePath = Vault.pathInsideVault(files.get(i));
			String parentPath = java.io.File.separator;
			
			Path path = Paths.get(filePath).getParent();
			if(path != null)
				parentPath = path.toString();

			if(parentPath.equals(folderPath) == false)
				files.remove(i);
		}
		
		return files;
	}
}
